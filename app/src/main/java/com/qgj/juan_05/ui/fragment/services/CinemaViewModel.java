package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.MoviesInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class CinemaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //根据id拿到详细信息
    MutableLiveData<MoviesInfoModel> ifmode;
    public MutableLiveData<MoviesInfoModel> getMode(int id) {
        if (ifmode ==null){
            ifmode = new MutableLiveData<>();
            loadmode(id);
        }
        return ifmode;
    }

    private void loadmode(int id) {
        new Thread(()->{
            try {
                ifmode.postValue(ServiceDaoImpl.getMovieInfoById(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}