package com.databinding.adapter.editText;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.databinding.command.ReplyCommand;


public class ViewBindingAdapter {

    @BindingAdapter(value = {"beforeTextChangedCommand", "onTextChangedCommand", "afterTextChangedCommand"}, requireAll = false)
    public static void edittextCommand(final EditText editText, final ReplyCommand<TextChangeDataWrapper> beforeTextChangedCommand, final ReplyCommand<TextChangeDataWrapper> onTextChangedCommand, final ReplyCommand<Editable> afterTextChangedCommand) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (beforeTextChangedCommand != null) {
                    beforeTextChangedCommand.execute(new TextChangeDataWrapper(editText, s, start, count, after));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChangedCommand != null) {
                    onTextChangedCommand.execute(new TextChangeDataWrapper(editText, s, start, before, count));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedCommand != null) {
                    afterTextChangedCommand.execute(s);
                }
            }
        });
    }


    public static class TextChangeDataWrapper {
        public CharSequence s;
        public int start;
        public int before;
        public int count;
        public EditText editText;

        public TextChangeDataWrapper(EditText editText, CharSequence s, int start, int before, int count) {
            this.editText = editText;
            this.s = s;
            this.start = start;
            this.before = before;
            this.count = count;
        }
    }


    @BindingAdapter(value = {"focusUpdateCommand"})
    public static void focusUpdate(final EditText editText, final ReplyCommand<Boolean> focusUpdateCommand) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (focusUpdateCommand != null) {
                    focusUpdateCommand.execute(hasFocus);
                }
            }
        });
    }


}
