package com;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.utils.SPUtils;

/**
 * Created by dadong on 2018/4/10.
 */

public class DaDongApplication extends Application {


    public static Context context;

    public static SPUtils spUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56f22e12");
        spUtils = SPUtils.getSpUtils(context, "DaDong");
        spUtils.putString("phoneNumber", "17826875948");
        spUtils.putString("passWord", "wxdwxd123");
        spUtils.putBoolean("gender", true);
        spUtils.putString("city", "浙江");
        spUtils.putString("sign", "守得云开见月明");
        spUtils.putString("portrait", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4015140342,3033919786&fm=27&gp=0.jpg");
        spUtils.putString("name", "吴大东");
        spUtils.putString("grade", "5岁");
    }

    public static Context getContext() {
        return context;
    }

    public static SPUtils getSpUtils(){
        return spUtils;
    }

}
