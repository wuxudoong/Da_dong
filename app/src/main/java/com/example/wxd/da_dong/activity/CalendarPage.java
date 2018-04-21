package com.example.wxd.da_dong.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.wxd.da_dong.R;
import com.example.wxd.da_dong.databinding.AppCalendarViewBinding;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dadong on 2018/4/21.
 */

public class CalendarPage extends AutoLinearLayout implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {


    public CalendarPage(Context context) {
        super(context);
    }

    public CalendarPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static CalendarPage getInstance(Context context) {
        AppCalendarViewBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.app_calendar_view,
                null,
                false);
        CalendarPage root = (CalendarPage) mBinding.getRoot();

        root.init(mBinding);
        return root;
    }

    private void init(AppCalendarViewBinding mBinding) {
        WeekView weekView = findViewById(R.id.weekView);

        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        weekView.setEmptyViewLongPressListener(this);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
}
