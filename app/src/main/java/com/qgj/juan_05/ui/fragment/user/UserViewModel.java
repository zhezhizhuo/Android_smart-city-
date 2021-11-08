package com.qgj.juan_05.ui.fragment.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.UpUserinfoModel;
import com.qgj.juan_05.netwok.model.UserInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;

public class UserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //用户信息
    MutableLiveData<UserInfoModel> userinfo;
    //修改密码
    MutableLiveData<DataModel> mData;
    public MutableLiveData<DataModel> getData(UpUserinfoModel model) {
        if (mData==null){
            mData = new MutableLiveData<>();
            loaddata(model);
        }
        return mData;
    }

    private void loaddata(UpUserinfoModel model) {
        if (MainActivity.token!=null&&!MainActivity.token.equals("")){
            new Thread(()->{
                try {
                            mData.postValue(ServiceDaoImpl.upUserinfo(MainActivity.token,model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public MutableLiveData<UserInfoModel> getUserinfo(String token) {
        if (userinfo == null){
            userinfo = new MutableLiveData<>();
            loadUserinfo( token);
        }
        return userinfo;
    }



    private void loadUserinfo(String token) {
        new Thread(()->{
            try {
                userinfo.postValue(ServiceDaoImpl.getUserInfo(token));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}