package com.router;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by dadong on 2018/4/9.
 */

public class ActivityRouter {

    //    APP模块
    public static final String APP_MAIN = "/app/main";

    public static void gotoAppMainActivity(Context context) {
        ARouter.getInstance().build(APP_MAIN).navigation(context);
    }

    //    Member模块
    public static final String MEMBER_LOGIN = "/member/login";

    public static void gotoMemberLoginActivity(Context context) {
        ARouter.getInstance().build(MEMBER_LOGIN).navigation(context);
    }

    //  Speech模块
    public static final String SPEECH_TEST = "/speech/test";

    public static void gotoTestSpeech(Context context) {
        ARouter.getInstance().build(SPEECH_TEST).navigation(context);
    }
}
