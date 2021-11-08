package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentActivityInfoBinding;
import com.qgj.juan_05.netwok.model.ActivityInfoModel;
import com.qgj.juan_05.netwok.model.BaoMing;
import com.qgj.juan_05.netwok.model.BaoMingModel;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;

public class ActivityInfoFragment extends Fragment {

    FragmentActivityInfoBinding binding;

    int activityId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActivityInfoBinding.inflate(getLayoutInflater());
        //获取哦if
        activityId = getArguments().getInt("ActivityId");
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //加载动画
        initview();
        //按钮信息
        initbth();
    }

    private void initbth() {
        //按钮添加事件
        binding.baoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断用户是否登录
                if (MainActivity.token==null||MainActivity.token.equals("")){
                    Toast.makeText(getActivity(), "您尚未登陆 请先登录!!", Toast.LENGTH_SHORT).show();

                    return;
                }
                //先判断用户是否已经报名该活动了
                new Thread(()->{
                    try {
                        BaoMingModel activityStatus = ServiceDaoImpl.getActivityStatus(MainActivity.token, activityId);
                        if (activityStatus.isIsSignup()){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "你已经报名该活动 不能在报名", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }
                        //继续报名
                        BaoMing baoMing = new BaoMing();
                        baoMing.setActivityId(activityId);
                        DataModel dataModel = ServiceDaoImpl.baomingActivity(MainActivity.token, baoMing);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), dataModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }).start();
            }
        });
    }

    private void initview() {
        //根据id获取详细信息
        new Thread(()->{
            try {
                ActivityInfoModel activityInfoById = ServiceDaoImpl.getActivityInfoById(activityId);
                //渲染数据
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //添加图片
                        Glide.with(getActivity()).load(MainActivity.serverURL+activityInfoById.getData().getImgUrl()).fitCenter().into(binding.img);
                        //活动内容
                        binding.count.setText(activityInfoById.getData().getContent());
                    }
                });

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }
}