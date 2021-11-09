package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class ServicesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //全部服务
    MutableLiveData<HomeServiceModel> mserviceall;
    public MutableLiveData<HomeServiceModel> getMserviceall() {
        if (mserviceall==null){
            mserviceall = new MutableLiveData<>();
            loadService();
        }
        return mserviceall;
    }

    private void loadService() {
        new Thread(()->{
            try {
                mserviceall.postValue(ServiceDaoImpl.getAllService());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}