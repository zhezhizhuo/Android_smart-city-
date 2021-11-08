package com.qgj.juan_05.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.UserFragmentBinding;
import com.qgj.juan_05.netwok.model.UserInfoModel;
import com.qgj.juan_05.ui.activity.MainActivity;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;

    UserFragmentBinding mBinding;
    NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = UserFragmentBinding.inflate(getLayoutInflater());
        if (MainActivity.token!=null&&!MainActivity.token.equals("")){
            //渲染数据
            initInfView();
        }
        return mBinding.getRoot();
    }

    private void initInfView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getUserinfo(MainActivity.token).observe(getViewLifecycleOwner(), new Observer<UserInfoModel>() {
            @Override
            public void onChanged(UserInfoModel userInfoModel) {
                UserInfoModel.UserDTO user = userInfoModel.getUser();
                if (user==null){
                    return;
                }
                mBinding.loginShow.setVisibility(View.VISIBLE);
                mBinding.username.setText(user.getNickName());
                mBinding.jf.setText(user.getBalance()+"积分");
                mBinding.jf.setVisibility(View.VISIBLE);
                mBinding.login.setVisibility(View.GONE);
//                Glide.with(getActivity()).load(BaseApplication.serverURL+user.getAvatar()).into(mBinding.touxiang);
                Glide.with(getActivity()).load("https://up.enterdesk.com/edpic/6b/06/c4/6b06c4719374d57d32617249162cc88f.jpg").into(mBinding.touxiang);
            }
        });
        //给修改密码添加事件
        mBinding.xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.uppasswordFragment,new Bundle());
            }
        });

        //给展示个人信息
        mBinding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              navController.navigate(R.id.userinfoFragment,new Bundle());
            }
        });
        mBinding.touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.userinfoFragment,new Bundle());
            }
        });
        //退出
        mBinding.outlgoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.token="";
                mBinding.loginShow.setVisibility(View.GONE);
                navController.popBackStack();
              navController.navigate(R.id.loginFragment,new Bundle());
            }
        });
        //更新数据
        mBinding.upinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.upuserInfoFragment,new Bundle());
            }
        });
        //订单
        mBinding.dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "此功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        //意见反馈
        mBinding.yj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.feedbackFragment,new Bundle());
            }
        });
        //登录
        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController = Navigation.findNavController(v);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("login",true);
                        navController.popBackStack();
                       navController.navigate(R.id.loginFragment,bundle);
                    }
                });
            }
        });
        mBinding.logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (MainActivity.token==null&&MainActivity.token.equals("")){
                                return;
                    }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController = Navigation.findNavController(v);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("login",true);
                        navController.popBackStack();
                        navController.navigate(R.id.loginFragment,bundle);
                    }
                });
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.token!=null&&!MainActivity.token.equals("")){
            //渲染数据
            initInfView();
        }
    }


}