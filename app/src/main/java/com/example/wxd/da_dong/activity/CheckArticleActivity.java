package com.example.wxd.da_dong.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wxd.da_dong.R;
import com.example.wxd.da_dong.databinding.AppCheckArticleLayoutBinding;
import com.example.wxd.da_dong.view.meizu.ArticleAdapter;
import com.router.ActivityRouter;
import com.uikit.Toolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dadong on 2018/4/27.
 */

@Route(path = ActivityRouter.APP_CHECK_ARTICLE)
public class CheckArticleActivity extends AutoLayoutActivity {

    @Autowired
    int position;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        AppCheckArticleLayoutBinding mBinding = DataBindingUtil.setContentView(this, R.layout.app_check_article_layout);
        ARouter.getInstance().inject(this);
        init(mBinding);
    }

    private void init(AppCheckArticleLayoutBinding mBinding) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.showLeftTitle(v -> {
            finish();
        }, getString(R.string.app_wxd_000));
        ArticleAdapter dataAdapter = new ArticleAdapter(this);
        mBinding.tvTitle.setText(dataAdapter.getItem(position).getTitle());
        mBinding.tvContent.setText(dataAdapter.getItem(position).getContent());
    }

}
