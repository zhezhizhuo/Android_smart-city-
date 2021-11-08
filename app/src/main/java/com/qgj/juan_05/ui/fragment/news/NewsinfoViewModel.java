package com.qgj.juan_05.ui.fragment.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.NewsInfoModel;
import com.qgj.juan_05.netwok.model.NewsInfoplModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class NewsinfoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //根据id查到个新闻
    MutableLiveData<NewsInfoModel> mNewsInfoModelMutableLiveData;
    //根据id查到个新闻评论
    MutableLiveData<NewsInfoplModel> mpldata;

    public MutableLiveData<NewsInfoplModel> getMpldata(int id) {
        if (mpldata == null){
            mpldata = new MutableLiveData<>();
        }
        loadpl(id);
        return mpldata;
    }

    private void loadpl(int id) {
        new Thread(()->{
            try {
                mpldata.postValue(ServiceDaoImpl.getNewsPingLunById(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public MutableLiveData<NewsInfoModel> getNewsInfoModelMutableLiveData(int id) {
        if (mNewsInfoModelMutableLiveData == null){
            mNewsInfoModelMutableLiveData = new MutableLiveData<>();
        }
        loader(id);
        return mNewsInfoModelMutableLiveData;
    }

    private void loader(int id) {
        new Thread(()->{
            try {
                mNewsInfoModelMutableLiveData.postValue(ServiceDaoImpl.getNewsInfoById(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}