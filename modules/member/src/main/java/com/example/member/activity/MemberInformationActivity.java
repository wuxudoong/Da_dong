package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

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

    @Override
    public void onCreate(Bundle saveInstancestate) {
        super.onCreate(saveInstancestate);
        MemberInformationLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.member_information_layout);
        init(mBinding);
    }

    private void init(MemberInformationLayoutBinding mBinding) {
        MemberInformationViewModel viewModel = new MemberInformationViewModel();
        mBinding.setViewModel(viewModel);
        initData(viewModel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.member_wxd_013));
        toolbar.setTitle(getString(R.string.member_wxd_028));
    }

    private void initData(MemberInformationViewModel viewModel) {
        String portraitUrl = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4015140342,3033919786&fm=27&gp=0.jpg";
        String city = "浙江";
        String grade = "5岁";
        String sign = "宝宝天天开心";
        String name = "吴大东";
        viewModel.name.set(name);
        viewModel.portraitUrl.set(portraitUrl);
        viewModel.city.set(city);
        viewModel.grade.set(grade);
        viewModel.sign.set(sign);
        viewModel.isMale.set(true);

    }
}