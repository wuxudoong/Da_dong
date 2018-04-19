package com.example.speach;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.speach.databinding.SpeachMainLayoutBinding;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/4/18.
 */

public class SpeachMainActivity extends AutoLayoutActivity {

    SpeachMainLayoutBinding mBinding;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.speach_main_layout);

    }
}
