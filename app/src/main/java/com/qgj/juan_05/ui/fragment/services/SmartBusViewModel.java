package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class SmartBusViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<BusLienModel> mbuslie;

    public MutableLiveData<BusLienModel> getMbuslie() {
        if (mbuslie==null){
            mbuslie = new MutableLiveData<>();
            loadlien();
        }
        return mbuslie;
    }

    private void loadlien() {
        new Thread(()->{

            try {
                mbuslie.postValue(ServiceDaoImpl.getBusLienAll());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }
}