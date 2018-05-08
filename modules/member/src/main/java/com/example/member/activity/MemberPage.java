package com.example.member.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.member.R;
import com.example.member.databinding.MemberMainViewBinding;
import com.example.member.viewModel.MemberMainViewModel;
import com.router.ActivityRouter;
import com.zhy.autolayout.AutoConstraintLayout;

/**
 * Created by dadong on 2018/4/21.
 */

public class MemberPage extends AutoConstraintLayout {

    MemberMainViewModel viewModel = new MemberMainViewModel();
    private String portraitUrl = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4015140342,3033919786&fm=27&gp=0.jpg";

    public MemberPage(Context context) {
        super(context);
    }

    public MemberPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemberPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static MemberPage getInstance(Context context) {
        MemberMainViewBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.member_main_view,
                null,
                false);
        MemberPage root = (MemberPage) mBinding.getRoot();
        root.init(mBinding);
        return root;
    }

    private void init(MemberMainViewBinding mBinding) {
        mBinding.setViewModel(viewModel);
        String phone = "17826875948";
        viewModel.portraitUrl.set(portraitUrl);
        mBinding.tvMemberName.setText("吴大东");
        mBinding.vSchedule.setOnClickListener(v -> {

        });

        mBinding.vContact.setOnClickListener(v -> {
            call(phone);
        });

        mBinding.vSetting.setOnClickListener(v -> {
            ActivityRouter.gotoMemberSystermSetting(getContext());
        });

        mBinding.tvEditInfo.setOnClickListener(v->{
            ActivityRouter.gotoMemberInformationActivity(getContext());
        });
    }


    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        getContext().startActivity(intent);
    }

}
