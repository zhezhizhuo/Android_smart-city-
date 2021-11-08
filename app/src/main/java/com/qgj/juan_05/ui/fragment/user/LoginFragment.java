package com.qgj.juan_05.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentLoginBinding;
import com.qgj.juan_05.netwok.model.LoginModel;
import com.qgj.juan_05.ui.activity.MainActivity;


public class LoginFragment extends Fragment {

    LoginFragmentModel mLoginFragmentModel;

    FragmentLoginBinding binding;
    NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void logniData() {

      binding.login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String username = binding.username.getText().toString();
              String password = binding.pasword.getText().toString();
              if(username.equals("")||password.equals("")){
                  Toast.makeText(getActivity(), "请输入用户名或者密码", Toast.LENGTH_SHORT).show();
                  return;
              }
              MutableLiveData<LoginModel> account = mLoginFragmentModel.getAccount(username, password);
              account.observe(getViewLifecycleOwner(), new Observer<LoginModel>() {
                  @Override
                  public void onChanged(LoginModel loginModel) {
                      if(loginModel!=null&&loginModel.getCode()==200){
                          navController.navigate(R.id.userFragment);
                          Toast.makeText(getActivity(), loginModel.getMsg(), Toast.LENGTH_SHORT).show();
                          //保存token
                          MainActivity.token = loginModel.getToken();
                      }else {
                          Toast.makeText(getActivity(), loginModel.getMsg(), Toast.LENGTH_SHORT).show();
                      }

                  }
              });
              account.removeObservers(getViewLifecycleOwner());
          }
      });
      binding.back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              navController.navigate(R.id.userFragment);
          }
      });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         binding = FragmentLoginBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        mLoginFragmentModel  = new ViewModelProvider(this).get(LoginFragmentModel.class);
        return binding.getRoot();

    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logniData();
    }
}
