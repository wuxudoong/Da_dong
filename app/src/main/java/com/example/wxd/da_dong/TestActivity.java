package com.example.wxd.da_dong;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wxd.da_dong.databinding.ActivityMainBinding;
import com.router.ActivityRouter;

/**
 * Created by wxd on 18-3-11.
 */


@Route(path = ActivityRouter.APP_MAIN)
public class TestActivity extends Activity {

    ActivityMainBinding mBinding;
    @Override
    public void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(v -> ActivityRouter.gotoMemberLoginActivity(this));
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
