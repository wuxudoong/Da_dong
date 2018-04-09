package com.uikit;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;


public class PhoneEditText extends AppCompatEditText {

    private final int PHONE_LEN=11;
    private int pressFlag = 0;
    private long pressTime = 0;

    public PhoneEditText(Context context) {
        super(context, null);
        bindEvent();
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        bindEvent();
    }

    public PhoneEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        bindEvent();
    }

    private void bindEvent() {
        // 344 format
        this.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();

                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();

                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = PhoneEditText.this.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    if (buffer.length() > PHONE_LEN) {
                        buffer.delete(PHONE_LEN, buffer.length());
                        location = PHONE_LEN + 2;
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if (index == 3) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        } else if (index > 5 && index == 8) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    PhoneEditText.this.setText(str);
                    Editable etable = PhoneEditText.this.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    public String getRealText() {
        String res = super.getText().toString().trim().replace(" ", "");
        return res;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if(pressFlag == 0) {
                pressTime = System.currentTimeMillis();
            }

            pressFlag++;

            if(System.currentTimeMillis() - pressTime > 250) {
                pressFlag = 0;
            }

            if (pressFlag > 1) {
                this.setText("");
                pressFlag = 0;
            }
        } else {
            pressFlag = 0;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        pressFlag = 0;
        return super.onKeyUp(keyCode, event);
    }
}
