package com.qgj.juan_05.ui.fragment.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.HomeNewsModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class NewsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //轮播图
    MutableLiveData<AbnnerModel> mbannerDate;
    //所有的新闻列表
    MutableLiveData<HomeNewsModel>  newslbData;

    public MutableLiveData<AbnnerModel> getMbannerDate() {
        if (mbannerDate==null){
            mbannerDate = new MutableLiveData<>();
            loadbanner();
        }
        return mbannerDate;
    }

    public MutableLiveData<HomeNewsModel> getNewslbData() {
        if (newslbData==null){
            newslbData = new MutableLiveData<>();
            loadeNews();
        }
        return newslbData;
    }

    //加载轮播图
    private void loadbanner() {
        new Thread(()->{
            try {
                mbannerDate.postValue(ServiceDaoImpl.getBannerImg());
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

}