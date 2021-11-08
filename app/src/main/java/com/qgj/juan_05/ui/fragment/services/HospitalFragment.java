package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.databinding.FragmentHospitalBinding;
import com.qgj.juan_05.netwok.model.OutAbnnerModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;

import java.io.IOException;



public class HospitalFragment extends Fragment {
    private String count;
    private int id = 0;
    FragmentHospitalBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHospitalBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         id = getArguments().getInt("id");
        count = getArguments().getString("count");
        initdata();
        initview();
    }

    private void initview() {
        binding.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.token==null||MainActivity.token.equals("")){
                    Toast.makeText(getActivity(), "请先登录在来预约", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void initdata() {
        if (id==0){
            return;
        }
        new Thread(()->{

            try {
                    OutAbnnerModel outAbbner = ServiceDaoImpl.getHospitalBannerById(id);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.banner.setDate(new BannerView.InitAbbnerAdapter(){
                                    @Override
                                    public View getSubView(ViewGroup container, int position) {
                                        ImageView img =  new ImageView(getContext());
                                        //算出真实的postion
                                        int realpositton = position%3;
                                        //
                                        Glide.with(getContext()).load(MainActivity.serverURL + outAbbner.getData().get(realpositton).getImgUrl()).into(img);
                                        if(img.getParent() instanceof ViewGroup){
                                            ((ViewGroup)img.getParent()).removeView(img);
                                        }
                                        img.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        });
                                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                                        return img;
                                    }
                                },3);
                                binding.count.setText(count);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}