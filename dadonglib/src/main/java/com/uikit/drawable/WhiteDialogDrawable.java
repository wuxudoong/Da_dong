package com.uikit.drawable;

import android.view.View;

import com.example.dadonglib.R;
import com.uikit.DrawableFactory;
import com.uikit.ShadowDrawable;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by dadong on 2018/5/1.
 */

public class WhiteDialogDrawable extends DrawableFactory {
    @Override
    public void setBackground(View view) {
        ShadowDrawable shadowDrawable = new ShadowDrawable(view.getResources().getColor(R.color.B1),
                AutoUtils.getPercentWidthSize(24),
                view.getResources().getColor(R.color.B1),
                0);
        view.setBackground(shadowDrawable);
    }
}
