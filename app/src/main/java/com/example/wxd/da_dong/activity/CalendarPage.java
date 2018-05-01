package com.example.wxd.da_dong.activity;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.BaseRecyclerAdapter;
import com.example.wxd.da_dong.R;
import com.example.wxd.da_dong.databinding.AppCalendarViewBinding;
import com.example.wxd.da_dong.view.meizu.Article;
import com.example.wxd.da_dong.view.meizu.ArticleAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.recyclerview.GroupItemDecoration;
import com.recyclerview.GroupRecyclerView;
import com.router.ActivityRouter;
import com.uikit.dialog.MessageDialog;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dadong on 2018/4/21.
 */

public class CalendarPage extends AutoLinearLayout implements
        CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    GroupRecyclerView mRecyclerView;
    MessageDialog messageDialog;

    public CalendarPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static CalendarPage getInstance(Activity activity) {
        AppCalendarViewBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(activity),
                R.layout.app_calendar_view,
                null,
                false);

        CalendarPage root = (CalendarPage) mBinding.getRoot();
        root.init(mBinding, activity);
        root.initdata();
        return root;
    }

    private void init(AppCalendarViewBinding mBinding, Activity activity) {
        mTextMonthDay = (TextView) findViewById(R.id.tv_month_day);
        mTextYear = (TextView) findViewById(R.id.tv_year);
        mTextLunar = (TextView) findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        mCalendarView.setOnDateLongClickListener(new CalendarView.OnDateLongClickListener() {
            @Override
            public void onDateLongClick(Calendar calendar) {
                messageDialog = new MessageDialog();
                messageDialog.setOnConfirmClickListener(new MessageDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirm(MessageDialog dialog) {
                        dialog.dismiss();
                    }
                });
                if (calendar.getScheme() == null || calendar.getScheme().isEmpty()) {
                    messageDialog.setMessage("宝贝今天没有任何安排哦～～");
                } else {
                    // TODO: 2018/5/1 从数据库获取，或者本地
                    messageDialog.setMessage("宝贝今天要学习哦～～");
                }
                messageDialog.show(activity.getFragmentManager());

            }
        });

    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void initdata() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "诗", "今天给宝贝听唐诗三百首"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "故事", "今天给宝贝听故事"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "童话", "今天给宝贝听童话故事"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "诗", "今天给宝贝听唐诗三百首"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "数", "今天让宝贝做口算训练"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "数", "今天让宝贝做口算训练"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "童话", "今天给宝贝听童话故事"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "故事", "今天给宝贝听故事"));
        schemes.add(getSchemeCalendar(year, month, 27, 0xFF13acf0, "诗", "今天给宝贝听唐诗三百首"));
        mCalendarView.setSchemeDate(schemes);

        mRecyclerView = (GroupRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        ArticleAdapter myAdapter = new ArticleAdapter(getContext());
        myAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                ActivityRouter.gotoAppCheckArticle(position, getContext());
            }
        });
        mRecyclerView.setAdapter(myAdapter);

        mRecyclerView.notifyDataSetChanged();
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text, String other) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        Calendar.Scheme scheme = new Calendar.Scheme();
        scheme.setScheme(text);
        scheme.setOther(other);
        calendar.addScheme(scheme);
        //  calendar.setScheme();
        return calendar;
    }
}
