package com.qgj.juan_05.ui.fragment.user;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.qgj.juan_05.netwok.model.AccountModel;
import com.qgj.juan_05.netwok.model.LoginModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;

public class LoginFragmentModel extends ViewModel {
    private MutableLiveData<LoginModel> login;



    public MutableLiveData<LoginModel> getAccount(String username, String password) {
            if (login==null){
                login = new MutableLiveData<>();
            }
        loadLogin(username, password);
        return login;
    }

    private void loadLogin(String username,String password) {

        AccountModel accountModel =new AccountModel();
        accountModel.setUsername(username);
        accountModel.setPassword(password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    login.postValue(ServiceDaoImpl.login(accountModel));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public MutableLiveData<LoginModel> getLogin(String username,String password) {
        if (login==null){
            loadLogin(username, password);
        }
        return login;
    }


}
