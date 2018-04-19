package com.uikit.drawable;

import android.view.View;

import com.example.dadonglib.R;
import com.uikit.DrawableFactory;
import com.uikit.ShadowDrawable;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by dadong on 2018/4/12.
 */

public class GrayBtnDrawable extends DrawableFactory {
    @Override
    public void setBackground(View view) {
        ShadowDrawable shadowDrawable =  new ShadowDrawable(view.getResources().getColor(R.color.C4),
                AutoUtils.getPercentWidthSize(3),
                view.getResources().getColor(R.color.C4),
                0);
        view.setBackground(shadowDrawable);
    }
}
