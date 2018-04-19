package com.example.member;

import android.databinding.BindingAdapter;
import android.view.View;

import com.uikit.DrawableFactory;
import com.uikit.drawable.BlueBtnDrawable;
import com.uikit.drawable.GrayBtnDrawable;

/**
 * Created by dadong on 2018/4/12.
 */

public class MemberBindingAdapter {

    @BindingAdapter("btnBackground")
    public static void setBtnBackGroud(View view, boolean isNext) {
        if (isNext) {
            DrawableFactory.get(BlueBtnDrawable.class).setBackground(view);
        } else {
            DrawableFactory.get(GrayBtnDrawable.class).setBackground(view);
        }
    }

}
