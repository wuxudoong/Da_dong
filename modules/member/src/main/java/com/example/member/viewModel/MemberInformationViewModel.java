package com.example.member.viewModel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by dadong on 2018/5/4.
 */

public class MemberInformationViewModel {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> portraitUrl = new ObservableField<>();
    public ObservableBoolean isMale = new ObservableBoolean(true);
    public ObservableField<String> grade = new ObservableField<>();
    public ObservableField<String> city = new ObservableField<>();
    public ObservableField<String> sign = new ObservableField<>();
}
