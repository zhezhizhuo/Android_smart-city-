package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.LifeModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class LifemoneyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //生活
    MutableLiveData<LifeModel> lifemodel;
    
    
    //轮播图
    MutableLiveData<AbnnerModel> lunbotu;

    public MutableLiveData<AbnnerModel> getLunbotu() {
        if (lunbotu == null){
            lunbotu = new MutableLiveData<>();
            loadlunbotu();
        }
        return lunbotu;
    }

    private void loadlunbotu() {
         new Thread(()->{
                     try {
                         lunbotu.postValue(ServiceDaoImpl.getLifeBanner());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }

    public MutableLiveData<LifeModel> getLifemodel() {

        if (lifemodel==null){
            lifemodel= new MutableLiveData<>();
            loadlife();
        }

        return lifemodel;
    }

    private void loadlife() {
         new Thread(()->{
                     try {
                         lifemodel.postValue(ServiceDaoImpl.getLifeAll());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }
}