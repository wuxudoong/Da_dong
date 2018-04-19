package com.example.member.viewModel;

import android.databinding.ObservableField;

import com.databinding.adapter.editText.ViewBindingAdapter;
import com.databinding.command.ReplyCommand;

import io.reactivex.functions.Consumer;


/**
 * Created by dadong on 2018/3/24.
 */

public class MemberLoginViewModel {

    public ObservableField<Boolean> isNext = new ObservableField<>();

    public ReplyCommand<ViewBindingAdapter.TextChangeDataWrapper> phoneTextChanged =
            new ReplyCommand<ViewBindingAdapter.TextChangeDataWrapper>
                    (new Consumer<ViewBindingAdapter.TextChangeDataWrapper>() {
                        @Override
                        public void accept(ViewBindingAdapter.TextChangeDataWrapper textChangeDataWrapper) throws Exception {

                        }
                    });
}
