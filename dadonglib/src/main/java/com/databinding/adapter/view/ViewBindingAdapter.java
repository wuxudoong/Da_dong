package com.databinding.adapter.view;

import android.databinding.BindingAdapter;
import android.view.View;


public class ViewBindingAdapter {
    @BindingAdapter({"onSelect"})
    public static void onSelect(View view, boolean selected) {
        view.setSelected(selected);
    }
}
