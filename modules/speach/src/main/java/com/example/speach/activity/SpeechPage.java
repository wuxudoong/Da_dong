package com.example.speach.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.speach.R;
import com.example.speach.databinding.SpeechMainLayoutBinding;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by dadong on 2018/4/18.
 */

public class SpeechPage extends AutoLinearLayout {


    public SpeechPage(Context context) {
        super(context);
    }

    public SpeechPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeechPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static SpeechPage getInstance(Context context){
        SpeechMainLayoutBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.speech_main_layout,
                null,
                false);
        SpeechPage root = (SpeechPage) mBinding.getRoot();
        root.init();
        return root;
    }

    private void init(){

    }
}
