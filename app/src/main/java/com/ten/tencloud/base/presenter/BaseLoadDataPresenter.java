package com.ten.tencloud.base.presenter;

import com.ten.tencloud.base.view.ILoadDataView;

/**
 * 辅助presenter类，实现一些通用的方法（涉及加载数据）
 * Created by lxq on 2017/11/20.
 */

public abstract class BaseLoadDataPresenter<V extends ILoadDataView> extends BasePresenter<V> implements ILoadDataPresenter<V> {

    @Override
    public void showLoading() {
        mView.showLoading();
    }

    @Override
    public void hideLoading() {
        mView.hideLoading();
    }

    @Override
    public void showRetry() {
        mView.showRetry();
    }

    @Override
    public void hideRetry() {
        mView.hideRetry();
    }

}
