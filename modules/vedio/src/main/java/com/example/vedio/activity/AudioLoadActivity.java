package com.example.vedio.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vedio.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

import static com.example.vedio.activity.GlobalConfig.AUDIO_FORMAT;
import static com.example.vedio.activity.GlobalConfig.CHANNEL_CONFIG;
import static com.example.vedio.activity.GlobalConfig.SAMPLE_RATE_INHZ;

/**
 * Created by dadong on 2018/6/25.
 */
public class AudioLoadActivity extends AutoLayoutActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST = 1001;
    private static final String TAG = "jqd";

    private Button mBtnControl;
    private Button mBtnPlay;

    //运行时权限的申请
    private String[] permissions = new String[]{
            Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //被用户拒绝的权限列表
    private List<String> mPermissionList = new ArrayList<>();
    private boolean isRecording;
    private AudioRecord audioRecord;
    private Button mBtnConvert;
    private AudioTrack audioTrack;
    private byte[] audioData;
    private FileInputStream fileInputStream;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.audio_load_layout);
        mBtnControl = (Button) findViewById(R.id.btn_control);
        mBtnControl.setOnClickListener(this);
        mBtnConvert = (Button) findViewById(R.id.btn_convert);
        mBtnConvert.setOnClickListener(this);
        mBtnPlay = (Button) findViewById(R.id.btn_play);
        mBtnPlay.setOnClickListener(this);
        checkPermissions();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_control) {
            Button button = (Button) v;
            if (button.getText().toString().equals(getString(R.string.start_record))) {
                button.setText(getString(R.string.stop_record));
                startRecord();
            } else {
                button.setText(getString(R.string.start_record));
                stopRecord();
            }
        }
        if (i == R.id.btn_convert) {
            PcmToWavUtil pcmToWavUtil = new PcmToWavUtil(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
            File pcmFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");
            File wavFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.wav");
            if (!wavFile.mkdirs()) {
                Log.e(TAG, "WavFile Directory not created");
            }
            if (wavFile.exists()) {
                wavFile.delete();
            }
            pcmToWavUtil.pcmToWav(pcmFile.getAbsolutePath(), wavFile.getAbsolutePath());

            Log.d(TAG, "创建成功" + "shishi");
        }
        if (i == R.id.btn_play) {
            Button button = (Button) v;
            String string = button.getText().toString();
            if (string.equals(getString(R.string.start_play))) {
                button.setText(getString(R.string.stop_play));
                playInModeStream();
            } else {
                button.setText(getString(R.string.start_play));
                stopPlay();
            }

        }
    }

    /**
     * 运行时权限的申请
     *
     * @param requestCode
     * @param permissions
     * @param grantResult
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            for (int i = 0; i < grantResult.length; ++i) {
                if (grantResult[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, permissions[i] + "权限被用户禁止");
                }
            }
        }
    }

    public void startRecord() {
        final int minBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_INHZ,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                minBufferSize);
        final byte data[] = new byte[minBufferSize];
        final File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not found");
        }
        if (file.exists()) {
            file.delete();
        }
        audioRecord.startRecording();
        isRecording = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (null != os) {
                    while (isRecording) {
                        int read = audioRecord.read(data, 0, minBufferSize);
                        if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                            try {
                                os.write(data);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Log.i(TAG, "run: close file output stream");
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stopRecord() {
        isRecording = false;
        //释放资源
        if (null != audioRecord) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permissions.length; ++i) {
                if (ContextCompat.checkSelfPermission(this, permissions[i])
                        != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);
                }
            }
            if (!mPermissionList.isEmpty()) {
                String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST);
            }
        }
    }

    /**
     * 播放，采用stream模式
     */
    private void playInModeStream() {
        /**
         * pcm音频的采样率 SAMPLE_RATE_INHZ
         * pcm音频声道 ChannelConfig
         * pcm音频格式 AUDIO_FORMAT
         */
        int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
        final int minBufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE_INHZ, channelConfig, AUDIO_FORMAT);
        audioTrack = new AudioTrack(
                new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build(),
                new AudioFormat.Builder()
                        .setEncoding(AUDIO_FORMAT)
                        .setChannelMask(channelConfig)
                        .build(),
                minBufferSize,
                AudioTrack.MODE_STREAM,
                AudioManager.AUDIO_SESSION_ID_GENERATE
        );
        audioTrack.play();
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");
        try {
            fileInputStream = new FileInputStream(file);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] tempBuffer = new byte[minBufferSize];
                        while (fileInputStream.available() > 0) {

                            int readCount = fileInputStream.read(tempBuffer);
                            if (readCount == AudioTrack.ERROR_BAD_VALUE ||
                                    readCount == AudioTrack.ERROR_INVALID_OPERATION) {
                                continue;
                            }
                            if (readCount != 0 && readCount != -1) {
                                audioTrack.write(tempBuffer, 0, readCount);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放，使用static模式
     */
    private void playINModeStatic() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    InputStream in = getResources().openRawResource(R.raw.ding);
                    try {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        for (int b; (b = in.read()) != -1; ) {
                            out.write(b);
                        }
                        Log.d(TAG, "Got the Data");
                        audioData = out.toByteArray();
                    } finally {
                        in.close();
                    }
                } catch (IOException e) {
                    Log.wtf(TAG, "Failed to read", e);
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                Log.i(TAG, "Creating track...audioData.length = " + audioData.length);

                audioTrack = new AudioTrack(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build(),
                        new AudioFormat.Builder()
                                .setSampleRate(22500)
                                .setEncoding(AudioFormat.ENCODING_PCM_8BIT)
                                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                                .build(),
                        audioData.length,
                        AudioTrack.MODE_STATIC,
                        AudioManager.AUDIO_SESSION_ID_GENERATE);

            }
        }.execute();

    }

    /**
     * 停止播放
     */
    private void stopPlay() {
        if (audioTrack != null) {
            Log.d(TAG, "Stopping");
            audioTrack.stop();
            Log.d(TAG, "releasing");
            audioTrack.release();
            Log.d(TAG, "Nulling");
        }
    }
}
