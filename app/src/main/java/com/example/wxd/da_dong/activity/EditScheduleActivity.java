package com.example.wxd.da_dong.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wxd.da_dong.R;
import com.example.wxd.da_dong.databinding.AppEditScheduleLayoutBinding;
import com.router.ActivityRouter;
import com.uikit.DrawableFactory;
import com.uikit.Toolbar;
import com.uikit.drawable.BlueBtnDrawable;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/5/9.
 */

@Route(path = ActivityRouter.APP_EDIT_SCHEDULE)
public class EditScheduleActivity extends AutoLayoutActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        AppEditScheduleLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.app_edit_schedule_layout);
        init(mBinding);
    }

    private void init(AppEditScheduleLayoutBinding mBinding) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.app_wxd_000));
        toolbar.setTitle(getString(R.string.app_wxd_003));

        DrawableFactory.get(BlueBtnDrawable.class).setBackground(mBinding.btnSubmit);
        mBinding.btnSubmit.setOnClickListener(v->{
            finish();
        });
    }
}
