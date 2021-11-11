package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class MoviesViewModel extends ViewModel {
    MutableLiveData<MovieModel> movieDate;

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
