package com.databinding.adapter.textview;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.text.Html;
import android.widget.TextView;



public class ViewBindingAdapter {
    @BindingAdapter({"html"})
    public static void textViewWithHtml(TextView view, String html) {
        if (html == null) {
            view.setText("");
            return;
        }
        view.setText(Html.fromHtml(html));
    }

    @BindingAdapter({"html"})
    public static void textViewWithHtml(TextView view, int res) {
        if (res == 0) {
            view.setText("");
            return;
        }
        view.setText(Html.fromHtml(view.getResources().getString(res)));
    }

    @BindingAdapter({"text"})
    public static void text(TextView view, String text) {
        if (text == null || "null".equals(text)) {
            view.setText("-");
        } else {
            view.setText(text);
        }
    }

    @BindingAdapter({"bg"})
    public static void text(TextView view, @DrawableRes int res) {
        view.setBackground(view.getResources().getDrawable(res));
    }

  /*  *//**
     * 解析text中的表情
     * @param view
     * @param text
     *//*
    @BindingAdapter({"spantext"})
    public static void spantext(TextView view, String text) {
        MoonUtil.identifyFaceExpression(view.getContext(), view, text, ImageSpan.ALIGN_BOTTOM);
    }*/

    /**
     * 监听输入框输入的字符数目，并以『当前输入字符数』/最大字符数的形式显示
     *
     * @param textView
     * @param editText
     * @param maxNum
     */
    @BindingAdapter({"edit_text", "max_num"})
    public static void setEditNum(TextView textView, String editText, int maxNum) {
        if (editText != null && editText.length() > 0) {
            int strNum = Math.max(0, Math.min(editText.length(), maxNum));
            textView.setText(strNum + "/" + maxNum);
        } else {
            textView.setText("0/" + maxNum);
        }
    }

}
