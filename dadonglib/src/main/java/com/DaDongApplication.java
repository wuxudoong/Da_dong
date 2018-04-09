package com;

import com.base.BaseApplication;

import me.chunsheng.logboy.BuildConfig;

/**
 * Created by dadong on 2018/4/10.
 */

public class DaDongApplication extends BaseApplication {
    @Override
    protected boolean debug() {
        return BuildConfig.DEBUG;
    }
}
