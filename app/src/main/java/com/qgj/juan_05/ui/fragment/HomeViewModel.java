package com.qgj.juan_05.ui.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.*;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //轮播图
    MutableLiveData<AbnnerModel> mbnnerDate;

    //全部服务
    MutableLiveData<HomeServiceModel> mservicesData;

    //所有的新闻列表
    MutableLiveData<HomeNewsModel>  newslbData;

    public MutableLiveData<AbnnerModel> getMbnnerDate() {
        if (mbnnerDate==null){
            mbnnerDate = new MutableLiveData<>();
            loadbanner();
        }
        return mbnnerDate;
    }


    public MutableLiveData<HomeServiceModel> getMservicesData() {
        if (mservicesData==null){
            mservicesData = new MutableLiveData<>();
            loadService();
        }
        return mservicesData;
    }
    //加载轮播图
    private void loadbanner() {
        new Thread(()->{
            try {
                mbnnerDate.postValue(ServiceDaoImpl.getBannerImg());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    //加载全部服务
    private void loadService() {
        new Thread(()->{
            try {
            mservicesData.postValue(ServiceDaoImpl.getAllService());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    //加载新闻
    private void loadeNews() {
        new Thread(()->{
            try {
                newslbData.postValue(ServiceDaoImpl.getAllHomeNewsType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public MutableLiveData<HomeNewsModel> getNewslbData() {
        if (newslbData==null){
            newslbData = new MutableLiveData<>();
            loadeNews();
        }
        return newslbData;
    }




}