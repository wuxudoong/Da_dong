package com.example.vedio.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.example.vedio.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;

/**
 * Created by dadong on 2018/6/25.
 */
public class ImageLoadActivity extends AutoLayoutActivity {

    ImageView imageView;

    /**
     * 方式一 imageView
     *
     * @return imageView
     */
    private ImageView imageViewLoad() {
        Bitmap bitmap = BitmapFactory.decodeFile(Environment
                .getExternalStorageDirectory()
                .getPath()
                + File.separator
                + "11.jpg");
        imageView.setImageBitmap(bitmap);
        return imageView;
    }

    public ImageLoadActivity(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * 方式二 surfaceView
     *
     * @return imageView
     */
    private ImageView surfaceViewLoad() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (holder == null) {
                    return;
                }
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                Bitmap bitmap = BitmapFactory.decodeFile(Environment
                        .getExternalStorageDirectory()
                        .getPath()
                        + File.separator
                        + "11.jpg");
                Canvas canvas = holder.lockCanvas();
                canvas.drawBitmap(bitmap, 0, 0, paint);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        return imageView;
    }

    class CustomView extends View {

        Paint paint = new Paint();
        Bitmap bitmap;

        public CustomView(Context context) {
            super(context);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            bitmap = BitmapFactory.decodeFile(Environment
                    .getExternalStorageDirectory().getPath()
                    + File.separator
                    + "11.jpg");

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, paint);
            }
        }

    }

}
