package com.qgj.juan_05.ui.fragment.user;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentUpuserInfoBinding;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.UpUserinfoModel;
import com.qgj.juan_05.netwok.model.UserInfoModel;
import com.qgj.juan_05.ui.activity.MainActivity;


public class UpuserInfoFragment extends Fragment {

    UserViewModel mViewModel;

    NavController navController;
    FragmentUpuserInfoBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding= FragmentUpuserInfoBinding.inflate(getLayoutInflater());
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
//                binding.sex.setText(user.getSex().equals("0") ? "男":"女");
                if (user.getSex().equals("0")){
                    RadioButton childAt = (RadioButton)binding.sex.getChildAt(0);
                    childAt.setChecked(true);
                }else {
                    RadioButton childAt = (RadioButton)binding.sex.getChildAt(1);
                    childAt.setSelected(true);
                }
                binding.balance.setText(String.valueOf(user.getIdCard()));
                binding.emial.setText(String.valueOf(user.getEmail()));
                binding.iphone.setText(String.valueOf(user.getPhonenumber()));
                binding.nick.setText(user.getNickName());

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    private void send() {
        //全部验证不能为空
        String sfz = binding.balance.getText().toString();
        String emial = binding.emial.getText().toString();
        String iphone = binding.iphone.getText().toString();
        String nick = binding.nick.getText().toString();
        int checkedRadioButtonId = binding.sex.getCheckedRadioButtonId();
        if (sfz.equals("")&&emial.equals("")&&iphone.equals("")&&nick.equals("")){
            Toast.makeText(getActivity(), "输入的信息不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton viewById = getView().findViewById(checkedRadioButtonId);
        String sexs = viewById.getText().toString();
        String sex = "";
        if (sexs.equals("男")){
                sex="0";
        }else {
            sex="1";
        }

        //创建对象
        UpUserinfoModel model =new UpUserinfoModel(emial,sfz,nick,iphone,sex);
        mViewModel.getData(model).observe(getViewLifecycleOwner(), new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                if (dataModel.getCode() != 200) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage(dataModel.getMsg())
                            .setPositiveButton("确定", null)
                            .show();
                    return;
                }
                Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }

        });
    }
}