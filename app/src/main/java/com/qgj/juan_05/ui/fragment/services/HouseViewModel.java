package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.HouseModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class HouseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //数据
        MutableLiveData<HouseModel> mhoude;

    public MutableLiveData<HouseModel> getMhoude() {
        if (mhoude==null){
            mhoude = new MutableLiveData<>();
            mhoudelode();
        }
        return mhoude;
    }

    private void mhoudelode() {
         new Thread(()->{
                     try {
                         mhoude.postValue(ServiceDaoImpl.getHouseAll());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }
}