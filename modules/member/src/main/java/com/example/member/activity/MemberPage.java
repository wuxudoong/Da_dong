package com.example.member.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.member.R;
import com.example.member.databinding.MemberMainViewBinding;
import com.zhy.autolayout.AutoConstraintLayout;

/**
 * Created by dadong on 2018/4/21.
 */

public class MemberPage extends AutoConstraintLayout {
    public MemberPage(Context context) {
        super(context);
    }

    public MemberPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemberPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static MemberPage getInstance(Context context) {
        MemberMainViewBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.member_main_view,
                null,
                false);

        MemberPage root = (MemberPage) mBinding.getRoot();
        root.init();
        return root;
    }

    private void init() {

    }
}
