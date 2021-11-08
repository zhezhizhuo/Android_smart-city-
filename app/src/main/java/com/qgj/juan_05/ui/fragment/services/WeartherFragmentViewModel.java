package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.qgj.juan_05.netwok.model.WearthInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class WeartherFragmentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<WeartherModel> mWeartherModelMutableLiveData;
    MutableLiveData<WearthInfoModel> mWearthInfoModelMutableLiveData;

    public MutableLiveData<WearthInfoModel> getWearthInfoModelMutableLiveData() {
        if (mWearthInfoModelMutableLiveData==null){
            mWearthInfoModelMutableLiveData= new MutableLiveData<>();
        }
        loadwheartoday();
        return mWearthInfoModelMutableLiveData;
    }

    private void loadwheartoday() {
        new Thread(()->{

            try {
                mWearthInfoModelMutableLiveData.postValue(ServiceDaoImpl.getWeatherInfoToday());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public MutableLiveData<WeartherModel> getWeartherModelMutableLiveData() {
        if (mWeartherModelMutableLiveData==null){
            mWeartherModelMutableLiveData= new MutableLiveData<>();
        }
        loadwhear();
        return mWeartherModelMutableLiveData;
    }

    private void loadwhear() {
        new Thread(()->{

            try {
                mWeartherModelMutableLiveData.postValue(ServiceDaoImpl.getWeatherInfo());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}