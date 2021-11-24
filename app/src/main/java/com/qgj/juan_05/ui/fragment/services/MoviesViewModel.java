package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.CinameModel;
import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class MoviesViewModel extends ViewModel {
    MutableLiveData<MovieModel> movieDate;
    // 影院
    MutableLiveData<CinameModel> cinamemodel;

    public MutableLiveData<CinameModel> getCinamemodel() {
        if (cinamemodel == null){
            cinamemodel = new MutableLiveData<>();
            cinamemodelload();
        }
        return cinamemodel;
    }

    private void cinamemodelload() {
         new Thread(()->{
                     try {
                         cinamemodel.postValue(ServiceDaoImpl.getCinema());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }

    //轮播图
    MutableLiveData<AbnnerModel> banner;
    //轮播图
    public MutableLiveData<AbnnerModel> getBanner() {
        if (banner==null){
            banner = new MutableLiveData<>();
            loadbanner();
        }
        return banner;
    }
    //轮播图
    private void loadbanner() {
        new Thread(()->{
            try {
                banner.postValue(ServiceDaoImpl.getMovieBanner());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public MutableLiveData<MovieModel> getMovieDate() {
        if (movieDate==null){
            movieDate = new MutableLiveData<>();
            loadMovie();
        }
        return movieDate;
    }

    private void loadMovie() {
        new Thread(()->{
            try {
                movieDate.postValue(ServiceDaoImpl.getMovieAll());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
