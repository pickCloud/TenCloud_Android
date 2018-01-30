package com.ten.tencloud.module.main.presenter;

import com.ten.tencloud.TenApp;
import com.ten.tencloud.base.presenter.BasePresenter;
import com.ten.tencloud.bean.PermissionTemplate2Bean;
import com.ten.tencloud.bean.User;
import com.ten.tencloud.model.AppBaseCache;
import com.ten.tencloud.model.subscribe.JesSubscribe;
import com.ten.tencloud.module.main.contract.MainContract;
import com.ten.tencloud.module.main.model.MsgModel;
import com.ten.tencloud.module.user.model.UserModel;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by lxq on 2018/1/2.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {


    @Override
    public void getMsgCount() {
        mSubscriptions.add(Observable.interval(0, 30, TimeUnit.SECONDS)
                .flatMap(new Func1<Long, Observable<Map<String, Integer>>>() {
                    @Override
                    public Observable<Map<String, Integer>> call(Long aLong) {
                        return MsgModel.getInstance().getMsgCountByStatus(0);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new JesSubscribe<Map<String, Integer>>(mView) {
                    @Override
                    public void _onSuccess(Map<String, Integer> stringIntegerMap) {
                        mView.showMsgCount(stringIntegerMap.get("num") + "");
                        Integer permission_changed = stringIntegerMap.get("permission_changed");
                        if (permission_changed != 0) {
                            mView.updatePermission();
                        }
                    }

                    @Override
                    public void onStart() {
                    }
                }));
    }

    @Override
    public void getPermission(final int cid) {
        AppBaseCache.getInstance().setUserPermission("");//清空
        User userInfo = AppBaseCache.getInstance().getUserInfo();
        if (userInfo == null) {
            TenApp.getInstance().jumpLoginActivity();
            return;
        }
        int uid = (int) userInfo.getId();
        mSubscriptions.add(UserModel.getInstance().getUserPermission(cid, uid)
                .subscribe(new JesSubscribe<PermissionTemplate2Bean>(mView) {
                    @Override
                    public void _onSuccess(PermissionTemplate2Bean o) {
                        String s = TenApp.getInstance().getGsonInstance().toJson(o);
                        AppBaseCache.getInstance().setUserPermission(s);
                        mView.updatePermissionSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getPermission(cid);
                    }
                }));
    }
}
