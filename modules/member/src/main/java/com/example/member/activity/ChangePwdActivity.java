package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberChangePwdLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.Toolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/3.
 */

@Route(path = ActivityRouter.MEMBER_CHANGE_PWD)
public class ChangePwdActivity extends AutoLayoutActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        MemberChangePwdLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_change_pwd_layout);
        init(mBinding);
    }

    private void init(MemberChangePwdLayoutBinding mBinding) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.member_wxd_019));
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.member_wxd_013));
        mBinding.btnAffirm.setOnClickListener(v -> {
            finish();
        });
    }
}
