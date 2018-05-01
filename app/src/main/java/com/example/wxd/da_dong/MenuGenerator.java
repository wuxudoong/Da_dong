package com.example.wxd.da_dong;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.member.activity.MemberPage;
import com.example.speach.activity.SpeechPage;
import com.example.wxd.da_dong.activity.CalendarPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dadong on 2018/4/20.
 */

public class MenuGenerator {
    public static final int[] mTabResPressed = new int[]{R.drawable.icon_app_calendar_selected, R.drawable.icon_app_speech_selected, R.drawable.icon_app_member_selected};

    public static final int[] mTabRes = new int[]{R.drawable.icon_app_calendar_selector, R.drawable.icon_app_speech_selector, R.drawable.icon_app_member_selector};
    public static final String[] mTabTitle = new String[]{"日程", "语音", "我的"};

    public static List<View> getViews(Context context) {
        List<View> views = new ArrayList<>();
        views.add(CalendarPage.getInstance((Activity) context));
        views.add(SpeechPage.getInstance((Activity) context));
        views.add(MemberPage.getInstance(context));
        return views;
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_menu_view, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.iv_tab_img);
        tabIcon.setImageResource(MenuGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tv_tab_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }

}
