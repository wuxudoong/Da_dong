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
    public static final String APP_EDIT_SCHEDULE = "/app/editSchedule";

    public static void gotoAppEditScheduleActivity(Context context){
        ARouter.getInstance().build(APP_EDIT_SCHEDULE).navigation(context);
    }

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
    public static final String MEMBER_INFORMATION = "/member/information";
    public static final String MEMBER_REGISTER = "/member/register";
    public static final String MEMBER_SETTING_INFORMATION = "/member/settinginformation";

    public static void gotoMemberSettingInformationActivity(String information, Context context){
        ARouter.getInstance().build(MEMBER_SETTING_INFORMATION).withString("information", information).navigation(context);
    }

    public static void gotoMemberRegisterActivity(Context context){
        ARouter.getInstance().build(MEMBER_REGISTER).navigation(context);
    }

    public static void  gotoMemberInformationActivity(Context context){
        ARouter.getInstance().build(MEMBER_INFORMATION).navigation(context);
    }

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
