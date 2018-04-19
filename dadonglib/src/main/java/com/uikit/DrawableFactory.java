package com.uikit;

import android.view.View;

/**
 * Created by ck on 2017/7/6.
 */

public abstract class DrawableFactory {


    public  static DrawableFactory get(Class c) {
        try {
            return (DrawableFactory) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void setBackground(View view);
}
