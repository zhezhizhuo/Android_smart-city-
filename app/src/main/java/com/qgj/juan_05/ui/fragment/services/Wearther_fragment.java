package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.qgj.juan_05.adpater.WearTherAdapter;
import com.qgj.juan_05.adpater.WearTherBodyAdapter;
import com.qgj.juan_05.databinding.WeartherFragmentBinding;
import com.qgj.juan_05.netwok.model.WearthInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;

import java.util.List;

public class Wearther_fragment extends Fragment {

    private WeartherFragmentViewModel mViewModel;
    WeartherFragmentBinding binding;
    public static Wearther_fragment newInstance() {
        return new Wearther_fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = WeartherFragmentBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    private void initview() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.center.setLayoutManager(manager);
        binding.body.setLayoutManager(manager1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initview();
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WeartherFragmentViewModel.class);

        //前面的数据
        mViewModel.getWearthInfoModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<WearthInfoModel>() {
            @Override
            public void onChanged(WearthInfoModel wearthInfoModel) {
                //渲染数据
                WearthInfoModel.DataDTO data = wearthInfoModel.getData();
                binding.air.setText(data.getAir());
                binding.today.setText(data.getLabel());
                binding.Temperature.setText(data.getMaxTemperature()+"℃ /  "+data.getMinTemperature()+"℃");
            }
        });


        //后面的数据
        // TODO: Use the ViewModel
        mViewModel.getWeartherModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<WeartherModel>() {
            @Override
            public void onChanged(WeartherModel weartherModel) {
                //截取数据
                //中间时间段的数据
                List<WeartherModel.DataDTO.TodayDTO.HoursDTO> hours = weartherModel.getData().getToday().getHours();
                WearTherAdapter adapter = new WearTherAdapter(hours,getActivity());
                binding.center.setAdapter(adapter);
//                //天气情况、生活指数的数据
//                //airQuantity /**  "airQuantity": {
//                //                "no2": 37,
//                //                "pm25": 22,
//                //                "o3": 39,
//                //                "so2": 9,
//                //                "pm10": 50,
//                //                "co": 0.3
//                //            },**/
//                WeartherModel.DataDTO.TodayDTO.AirQuantityDTO airQuantity = weartherModel.getData().getToday().getAirQuantity();
//                //comfortLevel
//                WeartherModel.DataDTO.TodayDTO.ComfortLevelDTO comfortLevel = weartherModel.getData().getToday().getComfortLevel();
//                //tempInfo
//                WeartherModel.DataDTO.TodayDTO.TempInfoDTO tempInfo = weartherModel.getData().getToday().getTempInfo();
                //更新时间
              String  updateTime= weartherModel.getData().getToday().getUpdateTime();
              binding.uptime.setText("更新时间:  "+updateTime);
               //风向
                WeartherModel.DataDTO.TodayDTO.WindDTO wind = weartherModel.getData().getToday().getWind();
                binding.windleav.setText(wind.getWindStrength());
                binding.wind.setText(wind.getWindDirection());
                //最近几天的weatherList
                List<WeartherModel.DataDTO.WeatherListDTO> weatherList = weartherModel.getData().getWeatherList();

                WearTherBodyAdapter wearTherBodyAdapter = new WearTherBodyAdapter(weatherList,getActivity());
                binding.body.setAdapter(wearTherBodyAdapter);
            }
        });
    }



}