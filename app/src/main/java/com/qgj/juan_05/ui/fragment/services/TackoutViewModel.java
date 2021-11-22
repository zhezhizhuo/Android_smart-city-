package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.TackOutModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class TackoutViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<AbnnerModel> banner;

    MutableLiveData<TackOutModel> out;

    public MutableLiveData<TackOutModel> getOut() {
        if (out ==null){
            out= new MutableLiveData<>();
            loadOut();
        }
        return out;
    }

    private void loadOut() {
         new Thread(()->{
                     try {
                         out.postValue(ServiceDaoImpl.gethotel());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }

    public MutableLiveData<AbnnerModel> getBanner() {
        if (banner==null){
            banner = new MutableLiveData<>();
            loadbanner();
        }
        return banner;
    }

    private void loadbanner() {
         new Thread(()->{
                     try {
                         banner.postValue(ServiceDaoImpl.getTackOut());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }
}