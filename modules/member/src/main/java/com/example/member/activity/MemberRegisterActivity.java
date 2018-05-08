package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberRegisterLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.DrawableFactory;
import com.uikit.drawable.BlueBtnDrawable;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/4.
 */

@Route(path = ActivityRouter.MEMBER_REGISTER)
public class MemberRegisterActivity extends AutoLayoutActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        MemberRegisterLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_register_layout);
        init(mBinding);
    }

    private void init(MemberRegisterLayoutBinding mBinding) {
        DrawableFactory.get(BlueBtnDrawable.class).setBackground(mBinding.btnAffirm);
        mBinding.btnAffirm.setOnClickListener(v -> ActivityRouter.gotoAppMainActivity(this));

    }
}
