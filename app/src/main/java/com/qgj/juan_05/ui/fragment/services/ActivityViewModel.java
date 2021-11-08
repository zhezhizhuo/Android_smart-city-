package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.ActivityBannerModel;
import com.qgj.juan_05.netwok.model.ActivityTypeModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class ActivityViewModel  extends ViewModel {
    //轮播图
    MutableLiveData<ActivityBannerModel> mactivybanner;
    //分类数据
    MutableLiveData<ActivityTypeModel> mactivyType;
    public MutableLiveData<ActivityBannerModel> getMactivybanner() {
        if (mactivybanner ==null){
            mactivybanner =new MutableLiveData<>();
            loadbanner();
        }
        return mactivybanner;
    }

    private void loadbanner() {
        new Thread(()->{
            try {
                mactivybanner.postValue(ServiceDaoImpl.getActivityBanner());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public MutableLiveData<ActivityTypeModel> getactivyType() {
        if (mactivyType ==null){
            mactivyType =new MutableLiveData<>();
            loadtype();
        }
        return mactivyType;
    }

    private void loadtype() {
        new Thread(()->{
            try {
                mactivyType.postValue(ServiceDaoImpl.getActivityType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
