package com.qgj.juan_05.ui.fragment.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.UpPassWordModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class UpdateFragmentViewModel extends ViewModel {

    private MutableLiveData<DataModel> mData;



    public MutableLiveData<DataModel> getData(UpPassWordModel model) {

            mData = new MutableLiveData<>();
        loadeData(model);
        return mData;
    }

    private void loadeData(UpPassWordModel model) {

        new Thread(()->{
            try {
               mData.postValue(ServiceDaoImpl.upDatePassWold(MainActivity.token,model));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
