package com.databinding.adapter.imageview;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uikit.MyLog;
import com.uikit.StringUtil;
import com.zhy.autolayout.utils.AutoUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class ViewBindingAdapter {
    @BindingAdapter({"url"})
    public static void url(ImageView view, String url) {
        MyLog.d("Glide loadImage ,url:");
        if (url == null) {
            return;
        }
        Glide.with(view.getContext()).load(url)
                .fitCenter()
                .into(view);
    }


    @BindingAdapter({"url", "default"})
    public static void url(ImageView view, String url, Drawable resourceId) {
        MyLog.d("Glide loadImage ,url:");
        if (url == null) {
            view.setBackground(resourceId);
            return;
        }
        Glide.with(view.getContext()).load(url)
                .fitCenter()
                .into(view);
    }

    @BindingAdapter(value = {"url", "radius"})
    public static void url(ImageView view, String url, float radius) {
        if (StringUtil.isEmpty(url)) {
            return;
        }
        float r = Math.min(view.getLayoutParams().width, view.getLayoutParams().height) / 2;
        Glide.with(view.getContext()).load(url)
                .bitmapTransform(new RoundedCornersTransformation(view.getContext(), AutoUtils.getPercentHeightSize((int) radius), 0))
                .into(view);
    }

    /**
     * @param view
     * @param defaultRes 默认头像
     * @param radius     圆角半径
     * @param url        图片url
     */
    @BindingAdapter(value = {"defaultRes", "radius", "url"})
    public static void url(ImageView view, int defaultRes, float radius, String url) {
        float r = Math.min(view.getLayoutParams().width, view.getLayoutParams().height) / 2;
        Glide.with(view.getContext()).load(url)
                .placeholder(defaultRes)
                .error(defaultRes)
                .bitmapTransform(new RoundedCornersTransformation(view.getContext(), AutoUtils.getPercentHeightSize((int) radius), 0))
                .into(view);
    }

    @BindingAdapter({"url"})
    public static void url(ImageView view, Drawable resourceId) {
        if (resourceId == null) {
            return;
        }
        view.setBackground(resourceId);
    }

    @BindingAdapter({"url"})
    public static void url(ImageView view, int resourceId) {
        if (resourceId == 0) {
            return;
        }
        view.setBackground(view.getResources().getDrawable(resourceId));
    }


   /* @BindingAdapter({"gif"})
    public static void gif(ImageView view, int resourceId) {
        Glide.with(view.getContext()).load(resourceId)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    *//**
     * 需要用有动画的资源作为占位符加载时
     *
     * @param view
     * @param defaultRes 占位符，加载成功前显示
     * @param errorRes   失败显示图片
     * @param radius
     * @param url
     *//*
    @BindingAdapter(value = {"defaultRes", "errorRes", "radius", "url"})
    public static void loadingLoadUrl(final ImageView view, Drawable defaultRes, Drawable errorRes, float radius, String url) {
        view.setBackground(view.getResources().getDrawable(R.drawable.bg_black_loadurl));
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        final ObjectAnimator anim = ObjectAnimator.ofInt(view, "ImageLevel", 0, 10000);
        anim.setDuration(1500);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();
        Glide.with(view.getContext()).load(url)
                .placeholder(defaultRes)
                .error(errorRes)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        anim.cancel();
                        view.setScaleType(ImageView.ScaleType.FIT_XY);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        view.setScaleType(ImageView.ScaleType.FIT_XY);
                        anim.cancel();
                        return false;
                    }
                })
                .dontAnimate()
                .bitmapTransform(new RoundedCornersTransformation(view.getContext(), AutoUtils.getPercentWidthSize((int) radius), 0))
                .into(view);
    }
*/
}
