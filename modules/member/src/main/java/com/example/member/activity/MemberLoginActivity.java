package com.example.member.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.member.R;
import com.example.member.databinding.MemberLoginLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.DrawableFactory;
import com.uikit.drawable.BlueBtnDrawable;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/3/24.
 */
@Route(path = ActivityRouter.MEMBER_LOGIN)
public class MemberLoginActivity extends AutoLayoutActivity {

    MemberLoginLayoutBinding mBinding;
    Button loginBtn;

    @Override
    public void  onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.member_login_layout);
        init();
    }

    private  void init(){
        loginBtn = findViewById(R.id.member_btn_login);
        DrawableFactory.get(BlueBtnDrawable.class).setBackground(loginBtn);
        loginBtn.setOnClickListener(v->ActivityRouter.gotoAppMainActivity(this));
    }
}
