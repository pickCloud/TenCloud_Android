package com.ten.tencloud.module.user.model;

import com.ten.tencloud.TenApp;
import com.ten.tencloud.bean.CompanyBean;
import com.ten.tencloud.bean.User;
import com.ten.tencloud.model.HttpResultFunc;
import com.ten.tencloud.utils.RetrofitUtils;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxq on 2017/12/14.
 */

public class UserModel {
    private static UserModel INSTANCE;

    private UserModel() {
    }

    public static synchronized UserModel getInstance() {
        if (INSTANCE == null) {
            synchronized (UserModel.class) {
                INSTANCE = new UserModel();
            }
        }
        return INSTANCE;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Observable<User> getUserInfo() {
        return TenApp.getRetrofitClient().getTenUserApi()
                .getUserInfo()
                .map(new HttpResultFunc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取公司列表TYPE
     */
    public static final int COMPANIES_TYPE_REJECT = -1;
    public static final int COMPANIES_TYPE_INREVIEW = 0;
    public static final int COMPANIES_TYPE_PASS = 1;
    public static final int COMPANIES_TYPE_CREATE = 2;
    public static final int COMPANIES_TYPE_PASS_AND_CREATE = 3;
    public static final int COMPANIES_TYPE_ALL = 4;

    /**
     * 获取公司列表
     *
     * @param type -1拒绝
     *             0审核中
     *             1通过
     *             2创始人
     *             3获取通过的，以及作为创始人的公司列表
     *             4获取所有和该用户相关的公司列表
     * @return
     */
    public Observable<List<CompanyBean>> getCompaniesWithType(int type) {
        return TenApp.getRetrofitClient().getTenUserApi()
                .getCompaniesWithType(type)
                .map(new HttpResultFunc<List<CompanyBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改用户信息
     *
     * @param map
     * @return
     */
    public Observable<Object> updateUserInfo(Map<String, String> map) {
        String json = TenApp.getInstance().getGsonInstance().toJson(map);
        RequestBody body = RetrofitUtils.stringToJsonBody(json);
        return TenApp.getRetrofitClient().getTenUserApi()
                .updateUserInfo(body)
                .map(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}