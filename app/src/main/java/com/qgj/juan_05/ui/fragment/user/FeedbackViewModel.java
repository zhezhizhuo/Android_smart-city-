package com.qgj.juan_05.ui.fragment.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.FeedBackModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class FeedbackViewModel extends ViewModel {
    // TODO: Implement the ViewModel

     MutableLiveData<DataModel> mData;




    public MutableLiveData<DataModel> getData(FeedBackModel model) {
        if (mData == null){
            mData = new MutableLiveData<>();
        }
        loadMdata(model);
        return mData;
    }
    private void loadMdata(FeedBackModel model) {
        if (MainActivity.token ==null&&MainActivity.token.equals("")){
            return;
        }
        new Thread(()->{
            try {
                mData.postValue(ServiceDaoImpl.sendFeedBack(MainActivity.token,model));;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}