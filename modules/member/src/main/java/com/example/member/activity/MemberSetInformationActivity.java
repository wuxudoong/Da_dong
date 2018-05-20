package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.DaDongApplication;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.member.R;
import com.example.member.databinding.MemberSetInformationLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.Toolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/13.
 */
@Route(path = ActivityRouter.MEMBER_SETTING_INFORMATION)
public class MemberSetInformationActivity extends AutoLayoutActivity {

    @Autowired
    String information;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        MemberSetInformationLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_set_information_layout);
        ARouter.getInstance().inject(this);
        init(mBinding);
    }

    private void init(MemberSetInformationLayoutBinding mBinding) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        switch (information){
            case "city":
                toolbar.setTitle(getString(R.string.member_wxd_025));
                break;
            case "name":
                toolbar.setTitle(getString(R.string.member_wxd_023));
                break;
            case "sign":
                toolbar.setTitle(getString(R.string.member_wxd_026));
                break;
            case "grade":
                toolbar.setTitle(getString(R.string.member_wxd_024));
                break;
        }
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.member_wxd_030));
        toolbar.showRightTitle(v -> {
            DaDongApplication.getSpUtils().putString(information, mBinding.etInformation.getText().toString());
            finish();
        }, getString(R.string.member_wxd_031));
        mBinding.ivClear.setOnClickListener(v -> {
            mBinding.etInformation.setText(null);
        });

    }
}
