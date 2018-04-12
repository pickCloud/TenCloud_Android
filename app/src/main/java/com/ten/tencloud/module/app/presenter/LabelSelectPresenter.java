package com.ten.tencloud.module.app.presenter;

import com.ten.tencloud.base.presenter.BasePresenter;
import com.ten.tencloud.bean.LabelBean;
import com.ten.tencloud.model.JesException;
import com.ten.tencloud.model.subscribe.JesSubscribe;
import com.ten.tencloud.module.app.contract.LabelSelectContract;
import com.ten.tencloud.module.app.model.AppModel;

import java.util.TreeSet;

/**
 * Create by chenxh@10.com on 2018/4/11.
 */
public class LabelSelectPresenter extends BasePresenter<LabelSelectContract.View> implements LabelSelectContract.Presenter<LabelSelectContract.View> {

    @Override
    public void newLabel(String name, int type) {
        mSubscriptions.add(AppModel.getInstance().newLabel(name, type)
                .subscribe(new JesSubscribe<Object>(mView) {
                    @Override
                    public void _onSuccess(Object o) {
                        mView.labelAddResult(true);
                    }

                    @Override
                    public void _onError(JesException e) {
                        super._onError(e);
                        mView.labelAddResult(false);
                    }
                }));
    }

    @Override
    public void getLabelList(int type) {
        mSubscriptions.add(AppModel.getInstance().getLabelList(type)
                .subscribe(new JesSubscribe<TreeSet<LabelBean>>(mView) {
                    @Override
                    public void _onSuccess(TreeSet<LabelBean> labelBeans) {
                        if (labelBeans == null || labelBeans.size() == 0) {
                            mView.showEmpty();
                            return;
                        }
                        mView.showLabelList(labelBeans);
                    }

                }));
    }
}
