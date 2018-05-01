package com.uikit;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadonglib.R;
import com.zhy.autolayout.AutoRelativeLayout;



public class Toolbar extends AutoRelativeLayout {

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setTitle(@StringRes int resId) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(resId);
    }


    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }


    public void showBack(OnClickListener onClickListener) {
        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setVisibility(onClickListener == null ? GONE : VISIBLE);
        btnBack.setOnClickListener(onClickListener);
    }

/*    public void showBack(OnClickListener onClickListener, String text) {
        TextView btnBack = (TextView) findViewById(R.id.btn_back);
        btnBack.setVisibility(onClickListener == null ? GONE : VISIBLE);
        btnBack.setOnClickListener(onClickListener);
        btnBack.setText(text);
    }*/

    public void showLeftTitle(OnClickListener onClickListener, String title) {
        TextView tvLeft = (TextView) findViewById(R.id.tv_left);
        tvLeft.setText(title);
        tvLeft.setVisibility(onClickListener == null ? GONE : VISIBLE);
        tvLeft.setOnClickListener(onClickListener);
    }

    public void showSearch(OnClickListener onClickListener) {
        ImageView ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivSearch.setVisibility(onClickListener == null ? GONE : VISIBLE);
        ivSearch.setOnClickListener(onClickListener);
    }

    public void showRightTitle(OnClickListener onClickListener, String title) {
        TextView btnRight = (TextView) findViewById(R.id.tv_right_title);
        btnRight.setText(Html.fromHtml(title));
        btnRight.setVisibility(onClickListener == null ? GONE : VISIBLE);
        btnRight.setOnClickListener(onClickListener);
    }

}
