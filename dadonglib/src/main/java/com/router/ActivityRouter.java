package com.router;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by dadong on 2018/4/9.
 */

public class ActivityRouter {

    //    APP模块
    public static final String APP_MAIN = "/app/main";
    public static final String APP_CHECK_ARTICLE = "/app/checkArticle";

    public static void gotoAppMainActivity(Context context) {
        ARouter.getInstance().build(APP_MAIN).navigation(context);
    }

    public static void gotoAppCheckArticle(int position, Context context){
        ARouter.getInstance().build(APP_CHECK_ARTICLE).withInt("position", position).navigation(context);
    }

    //    Member模块
    public static final String MEMBER_LOGIN = "/member/login";
    public static final String MEMBER_SYSTEM_SETTING = "/member/systemsetting";
    public static final String MEMBER_CHANGE_PWD = "/member/changepwd";

    public static void gotoMemberSystermSetting(Context context){
        ARouter.getInstance().build(MEMBER_SYSTEM_SETTING).navigation(context);
    }

    public static void gotoMemberLoginActivity(Context context) {
        ARouter.getInstance().build(MEMBER_LOGIN).navigation(context);
    }

    public static void gotoMemberChangePwd(Context context){
     ARouter.getInstance().build(MEMBER_CHANGE_PWD).navigation(context);
    }


    //  Speech模块
    public static final String SPEECH_TEST = "/speech/test";

    public static void gotoTestSpeech(Context context) {
        ARouter.getInstance().build(SPEECH_TEST).navigation(context);
    }
}
