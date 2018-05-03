package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberSettingLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.Toolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/2.
 */
@Route(path = ActivityRouter.MEMBER_SYSTEM_SETTING)
public class SystemSettingActivity extends AutoLayoutActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        MemberSettingLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_setting_layout);
        init(mBinding);
    }

    private void init(MemberSettingLayoutBinding mBinding) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.member_wxd_012));
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.member_wxd_013));

        mBinding.vChangePwd.setOnClickListener(v -> {

        });

        mBinding.vClear.setOnClickListener(v -> {
            mBinding.tvClear.setText("0m");
        });
    }
}
