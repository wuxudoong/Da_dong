package com.example.speach.activity;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import com.adapter.BaseRecyclerAdapter;
import com.example.speach.Article;
import com.example.speach.R;
import com.example.speach.ResultBean;
import com.example.speach.adapter.SpeechDataAdapter;
import com.example.speach.databinding.SpeechMainLayoutBinding;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.recyclerview.GroupItemDecoration;
import com.uikit.DrawableFactory;
import com.uikit.MyLog;
import com.uikit.drawable.BlueRoundDrawable;
import com.zhy.autolayout.AutoConstraintLayout;

import java.util.List;

/**
 * Created by dadong on 2018/4/18.
 */

public class SpeechPage extends AutoConstraintLayout {

    private Gson mGson;
    private Activity mActivity;
    private static final String mNewsText = "昔人已乘黄鹤去，此地空余黄鹤楼。 黄鹤一去不复返，白云千载空悠悠。 晴川历历汉阳树，芳草萋萋鹦鹉洲。 日暮乡关何处是，烟波江上使人愁。";

    public SpeechPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static SpeechPage getInstance(Activity activity) {
        SpeechMainLayoutBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(activity),
                R.layout.speech_main_layout,
                null,
                false);
        SpeechPage root = (SpeechPage) mBinding.getRoot();
        root.init(mBinding, activity);
        return root;
    }

    private void init(SpeechMainLayoutBinding mBinding, Activity activity) {

        mGson = new Gson();
        mActivity = activity;
        MyLog.d(mActivity.getLocalClassName() + "shishi");
        Log.d(mActivity.getLocalClassName(), "shishi11");
        mBinding.tvSpeakChinese.setOnClickListener(v -> {
           onRecognise();
        });
        DrawableFactory.get(BlueRoundDrawable.class).setBackground(mBinding.tvSpeakChinese);
     /*   mBinding.btnCreateChinese.setOnClickListener(v -> {
            onSynthesize();
        });*/

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        SpeechDataAdapter myAdapter = new SpeechDataAdapter(getContext());
        myAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                onSynthesize(myAdapter.getItem(position).getContent());
            }
        });
        mBinding.recyclerView.setAdapter(myAdapter);

        mBinding.recyclerView.notifyDataSetChanged();

    }

    public void onRecognise() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(mActivity, null);
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(mRecognizerDialogListener);
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {

        /**
         *
         * @param recognizerResult 语音识别结果
         * @param b true表示是标点符号
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            // Toast.makeText(MainActivity.this, recognizerResult.getResultString(), Toast.LENGTH_LONG).show();
            if (b) {
                return;
            }
            ResultBean resultBean = mGson.fromJson(recognizerResult.getResultString(), ResultBean.class);
            List<ResultBean.WsBean> ws = resultBean.getWs();
            String w = "";
            for (int i = 0; i < ws.size(); i++) {
                List<ResultBean.WsBean.CwBean> cw = ws.get(i).getCw();
                for (int j = 0; j < cw.size(); j++) {
                    w += cw.get(j).getW();
                }
            }
            onSynthesize(mNewsText);
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    };

    public void onSynthesize(String text) {
        //1.创建 SpeechSynthesizer 对象, 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(mActivity, null);
        //2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking(text, null);
    }
}
