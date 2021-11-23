package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.TackOutModel;
import com.qgj.juan_05.netwok.model.WaiMai2Model;
import com.qgj.juan_05.netwok.model.WaiMaiModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class TackoutViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<AbnnerModel> banner;

    //外面分类
    MutableLiveData<WaiMaiModel> waiMiaiType;
    MutableLiveData<WaiMai2Model> waiMaiInfo;

    public MutableLiveData<WaiMaiModel> getWaiMiaiType() {
        if (waiMiaiType == null){
            waiMiaiType= new MutableLiveData<>();
            loadWaiMai();
        }
        return waiMiaiType;
    }

    private void loadWaiMai() {
         new Thread(()->{
                     try {
                         waiMiaiType.postValue(ServiceDaoImpl.getWaiMaiFenLei());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }

    public MutableLiveData<WaiMai2Model> getWaiMaiInfo(int id) {
        if (waiMaiInfo == null){
            waiMaiInfo= new MutableLiveData<>();
            loadWaiMai2(id);
        }
        return waiMaiInfo;
    }

    private void loadWaiMai2(int id) {
        new Thread(()->{
            try {
                waiMaiInfo.postValue(ServiceDaoImpl.getWaiMaiInfoAll(id));
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