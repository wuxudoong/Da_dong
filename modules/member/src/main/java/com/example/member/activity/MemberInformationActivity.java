package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.DaDongApplication;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberInformationLayoutBinding;
import com.example.member.viewModel.MemberInformationViewModel;
import com.router.ActivityRouter;
import com.uikit.Toolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/4.
 */

@Route(path = ActivityRouter.MEMBER_INFORMATION)
public class MemberInformationActivity extends AutoLayoutActivity {

    MemberInformationViewModel viewModel = new MemberInformationViewModel();

    @Override

    public void onCreate(Bundle saveInstancestate) {
        super.onCreate(saveInstancestate);
        MemberInformationLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_information_layout);
        init(mBinding);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void init(MemberInformationLayoutBinding mBinding) {
        mBinding.setViewModel(viewModel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.member_wxd_013));
        toolbar.setTitle(getString(R.string.member_wxd_028));
        mBinding.rlName.setOnClickListener(v -> {
            ActivityRouter.gotoMemberSettingInformationActivity("name", this);
        });
        mBinding.rlCity.setOnClickListener(v->{
            ActivityRouter.gotoMemberSettingInformationActivity("city", this);
        });
        mBinding.rlGrade.setOnClickListener(v->{
            ActivityRouter.gotoMemberSettingInformationActivity("grade", this);
        });
        mBinding.rlSign.setOnClickListener(v->{
            ActivityRouter.gotoMemberSettingInformationActivity("sign", this);
        });
    }

    private void initData() {
        String portraitUrl = DaDongApplication.spUtils.getString("portrait", "");
        String city = DaDongApplication.spUtils.getString("city", "");
        String grade = DaDongApplication.spUtils.getString("grade", "");
        String sign = DaDongApplication.spUtils.getString("sign", "");
        String name = DaDongApplication.spUtils.getString("name", "");
        boolean isMale = DaDongApplication.spUtils.getBoolean("male", true);
        viewModel.name.set(name);
        viewModel.portraitUrl.set(portraitUrl);
        viewModel.city.set(city);
        viewModel.grade.set(grade);
        viewModel.sign.set(sign);
        viewModel.isMale.set(isMale);

    }
}