package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberLoginLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.DrawableFactory;
import com.uikit.drawable.BlueBtnDrawable;
import com.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/3/24.
 */
@Route(path = ActivityRouter.MEMBER_LOGIN)
public class MemberLoginActivity extends AutoLayoutActivity {

    MemberLoginLayoutBinding mBinding;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.member_login_layout);
        init();
    }

    private void init() {
        DrawableFactory.get(BlueBtnDrawable.class).setBackground(mBinding.memberBtnLogin);
        mBinding.memberBtnLogin.setOnClickListener(v -> {
            if (mBinding.memberPetPhone.getRealText().equals(SPUtils.getSpUtils(this, "DaDong").getString("phoneNumber", ""))) {
                if (mBinding.memberEtPwd.getText().toString().equals(SPUtils.getSpUtils(this, "DaDong").getString("passWord", ""))) {
                    ActivityRouter.gotoAppMainActivity(this);
                }
            }
        });
    }
}
