package com.uikit.dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.dadonglib.R;
import com.example.dadonglib.databinding.DialogRemindLayoutBinding;
import com.uikit.DrawableFactory;
import com.uikit.drawable.WhiteDialogDrawable;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by dadong on 2018/4/30.
 */

public class MessageDialog extends DialogFragment {
    DialogRemindLayoutBinding mBinding;
    OnConfirmClickListener onConfirmClickListener;
    OnEditListener onEditListener;
    String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_remind_layout, null, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        DrawableFactory.get(WhiteDialogDrawable.class).setBackground(mBinding.llMessageDialog);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        mBinding.tvAffirm.setOnClickListener(v -> {
            if (onConfirmClickListener != null) {
                onConfirmClickListener.onConfirm(this);
            }
        });
        mBinding.tvMessage.setText(message);

        mBinding.tvEdit.setOnClickListener(v->{
            if(onEditListener !=  null){
                onEditListener.onEdit(this);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = AutoUtils.getPercentWidthSize(600);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawable(null);
        window.setAttributes(lp);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void show(FragmentManager manager) {
        manager.executePendingTransactions();
        if (!isAdded()) {
            show(manager, MessageDialog.class.getName());
        }
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public void setOnEditListener(OnEditListener onEditListener){
        this.onEditListener = onEditListener;
    }

    public void setMessage(String message){
        this.message = message;
    }

   /* public static class Builder {
        private OnConfirmClickListener onConfirmClickListener;
        public Builder setOnConfirmClickListener (OnConfirmClickListener onConfirmClickListener){
            this.onConfirmClickListener = onConfirmClickListener;
            return this;
        }

        public MessageDialog build(){
            MessageDialog messageDialog = new MessageDialog();
            messageDialog.setOnConfirmClickListener(onConfirmClickListener);
            return messageDialog;
        }

    }*/

    public interface OnConfirmClickListener {
        void onConfirm(MessageDialog dialog);
    }

    public interface OnEditListener{
        void onEdit(MessageDialog dialog);
    }
}

