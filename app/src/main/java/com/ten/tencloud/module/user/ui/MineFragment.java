package com.ten.tencloud.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ten.tencloud.R;
import com.ten.tencloud.base.view.BaseFragment;
import com.ten.tencloud.bean.User;
import com.ten.tencloud.module.user.contract.UserHomeContract;
import com.ten.tencloud.module.user.presenter.UserHomePresenter;
import com.ten.tencloud.utils.Utils;
import com.ten.tencloud.utils.glide.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * "我的"模块
 * Created by lxq on 2017/11/23.
 */

public class MineFragment extends BaseFragment implements UserHomeContract.View {

    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_certification)
    ImageView mIvCertification;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;

    private UserHomePresenter mUserHomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.fragment_mine_home);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUserHomePresenter = new UserHomePresenter();
        mUserHomePresenter.attachView(this);
        mUserHomePresenter.getUserInfo();
    }

    @OnClick({R.id.ll_user, R.id.btn_switch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_user:

                break;
            case R.id.btn_switch:

                break;
        }
    }

    @Override
    public void showUserInfo(User user) {
        mTvUserName.setText(user.getName());
        mTvPhone.setText(Utils.hide4Phone(user.getMobile()));
        GlideUtils.getInstance().loadCircleImage(mActivity, mIvAvatar, user.getImage_url(), R.mipmap.icon_userphoto);
    }
}
