package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.qgj.juan_05.netwok.model.OutPationModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class OutpatientViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<OutPationModel> mOutPationModelMutableLiveData;

    public MutableLiveData<OutPationModel> getOutPationModelMutableLiveData() {
        if (mOutPationModelMutableLiveData==null){
            mOutPationModelMutableLiveData= new MutableLiveData<>();
        }
        loadedate();
        return mOutPationModelMutableLiveData;
    }

    private void loadedate() {
        new Thread(()->{
            try {
                mOutPationModelMutableLiveData.postValue(ServiceDaoImpl.getOutpatientAll());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}