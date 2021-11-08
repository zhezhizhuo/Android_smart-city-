package com.qgj.juan_05.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.databinding.FragmentUserinfoBinding;
import com.qgj.juan_05.netwok.model.UserInfoModel;
import com.qgj.juan_05.ui.activity.MainActivity;


/**

 */
public class UserinfoFragment extends Fragment {


    UserViewModel mViewModel;
    NavController navController;
    FragmentUserinfoBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
         navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentUserinfoBinding.inflate(getLayoutInflater());
         initview();
        return binding.getRoot();
    }

    private void initview() {
        if (MainActivity.token ==null &&MainActivity.token.equals("")){
            return;
        }
        mViewModel.getUserinfo(MainActivity.token).observe(getViewLifecycleOwner(), new Observer<UserInfoModel>() {
            @Override
            public void onChanged(UserInfoModel userInfoModel) {
                UserInfoModel.UserDTO user = userInfoModel.getUser();
                if (user==null){
                    Toast.makeText(getActivity(), "错误请重新登陆", Toast.LENGTH_SHORT).show();
                }
                binding.sex.setText(user.getSex().equals("0") ? "男":"女");
                binding.balance.setText(String.valueOf(user.getBalance()));
                binding.emial.setText(String.valueOf(user.getEmail()));
                binding.iphone.setText(String.valueOf(user.getPhonenumber()));
                binding.usernmae.setText(user.getNickName());
                binding.score.setText(user.getScore()+"");
                Glide.with(getActivity()).load("https://up.enterdesk.com/edpic/6b/06/c4/6b06c4719374d57d32617249162cc88f.jpg").into(binding.phone);
            }
        });
    }
}