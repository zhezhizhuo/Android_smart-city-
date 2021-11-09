package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class SmartBusViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //全部车辆
    MutableLiveData<BusLienModel> mbuslie;
    //车辆的信息
    MutableLiveData<BusLineInfoModel> mline;

    public MutableLiveData<BusLineInfoModel> getMline(int id) {
        if (mline==null){
            mline = new MutableLiveData<>();
            loadRow(id);
        }
        return mline;
    }

    private void loadRow(int id) {
        new Thread(()->{

            try {
                mline.postValue(ServiceDaoImpl.getBusLienInfoById(id));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }

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