package com.example.wxd.da_dong.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wxd.da_dong.MenuGenerator;
import com.example.wxd.da_dong.R;
import com.example.wxd.da_dong.databinding.AppMainLayoutBinding;
import com.router.ActivityRouter;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

/**
 * Created by wxd on 18-3-11.
 */


@Route(path = ActivityRouter.APP_MAIN)
public class MainActivity extends AutoLayoutActivity {

    AppMainLayoutBinding mBinding;
    private TabLayout mTabLayout;
    private List<View> views;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.app_main_layout);
        views = MenuGenerator.getViews(this);
        initView();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tbl_app_menu);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                onTabItemSelected(tab.getPosition());
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.iv_tab_img);
                    TextView text = (TextView) view.findViewById(R.id.tv_tab_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(MenuGenerator.mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(R.color.C2));
                    } else {// 未选中状态
                        icon.setImageResource(MenuGenerator.mTabRes[i]);
                        text.setTextColor(getResources().getColor(R.color.C3));
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 提供自定义的布局添加Tab
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(MenuGenerator.getTabView(this, i)));
        }

    }

    private void onTabItemSelected(int position) {
        View view = null;
        viewsGone(position);
        switch (position) {
            case 0:
                view = views.get(0);
                break;
            case 1:
                view = views.get(1);
                break;
            case 2:
                view = views.get(2);
                break;
        }
        if (view != null) {
            mBinding.flModuleContainer.removeView(view);
            mBinding.flModuleContainer.addView(view);
        }
    }

    private void viewsGone(int position) {
        for (int i = 0; i < 3; ++i) {
            if (i == position) {
                views.get(i).setVisibility(View.VISIBLE);
                continue;
            } else {
                views.get(i).setVisibility(View.GONE);
            }
        }
    }

}
