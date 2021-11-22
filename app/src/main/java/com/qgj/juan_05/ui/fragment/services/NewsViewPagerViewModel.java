package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.adpater.HomeNewsInfoAdapter;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.NewsInfoModel;
import com.qgj.juan_05.netwok.model.NewsInfoplModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

public class NewsViewPagerViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //根据id查到个新闻
    MutableLiveData<HomeNewAllModel> mNewsInfoModelMutableLiveData;
    //根据id查到个新闻评论
    MutableLiveData<NewsInfoplModel> mpldata;

    public MutableLiveData<NewsInfoplModel> getMpldata(int id) {
        if (mpldata == null){
            mpldata = new MutableLiveData<>();
            loadpl(id);
        }
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

    public MutableLiveData<HomeNewAllModel> getNewsInfoModelMutableLiveData(int id) {
        if (mNewsInfoModelMutableLiveData == null){
            mNewsInfoModelMutableLiveData = new MutableLiveData<>();
        }
        loader(id);
        return mNewsInfoModelMutableLiveData;
    }

    private void loader(int id) {
        new Thread(()->{
            try {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    if (getActivity() != null) {
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
                HomeNewAllModel homeNewsModel = null;

                    homeNewsModel = ServiceDaoImpl.getHomeNewsById(id);




        Collections.sort(homeNewsModel.getRows(), new Comparator<HomeNewAllModel.RowsDTO>() {
                                    DateFormat f = new SimpleDateFormat("yyyy-mm-dd");
                                    @Override
                                    public int compare(HomeNewAllModel.RowsDTO o1, HomeNewAllModel.RowsDTO o2) {
                                        try {
                                            return f.parse(o1.getCreateTime()).compareTo(f.parse(o2.getCreateTime()));
                                        } catch (ParseException e) {
                                            throw new IllegalArgumentException(e);
                                        }
                                    }
                                });
        mNewsInfoModelMutableLiveData.postValue(homeNewsModel);
//                                HomeNewsInfoAdapter adapter = new HomeNewsInfoAdapter(homeNewsModel.getRows(),getActivity(),navController);
//                                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
//                                binding.newsinfo.setLayoutManager(manager);
//                                binding.newsinfo.setAdapter(adapter);
//                            }
//                        });
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}