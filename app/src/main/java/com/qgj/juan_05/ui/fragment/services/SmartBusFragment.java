package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HomeServiceAdapter;
import com.qgj.juan_05.adpater.ServicesBusLineAdapter;
import com.qgj.juan_05.databinding.FragmentSmartBusBinding;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;
import java.util.List;

public class SmartBusFragment extends Fragment {

    private SmartBusViewModel mViewModel;
    FragmentSmartBusBinding binding;

    public static SmartBusFragment newInstance() {
        return new SmartBusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSmartBusBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SmartBusViewModel.class);
        // TODO: Use the ViewModel
        initview();
        initData();
    }

    private void initData() {

//        mViewModel.getMbuslie().observe(getViewLifecycleOwner(), new Observer<BusLienModel>() {
//            @Override
//            public void onChanged(BusLienModel busLienModel) {
//                //给地铁添加路线图
//                List<BusLienModel.RowsDTO> rows = busLienModel.getRows();
//                //新数据
//                for (BusLienModel.RowsDTO row : rows) {
//                   mViewModel.getMline(row.getId()).observe(getViewLifecycleOwner(), new Observer<BusLineInfoModel>() {
//                       @Override
//                       public void onChanged(BusLineInfoModel busLineInfoModel) {
//
//                           row.mInfoModel=busLineInfoModel;
//                           Log.e("Demo4",busLineInfoModel.toString());
//                           Log.e("Demo4s",row.mInfoModel.toString());
//                       }
//                   });
                    new Thread(()->{
                        try {
                            BusLienModel busLienAll = ServiceDaoImpl.getBusLienAll();
                            for (BusLienModel.RowsDTO row : busLienAll.getRows()) {
                                BusLineInfoModel busLienInfoById = ServiceDaoImpl.getBusLienInfoById(row.getId());
//                              System.out.println(row.getId()+busLienInfoById.toString());

                                List<BusLineInfoModel.RowsDTO> rows = busLienInfoById.getRows();
                                row.mInfoModel=rows;
                            }
//                            BusLineInfoModel busLienInfoById = ServiceDaoImpl.getBusLienInfoById(row.getId());
//                            row.mInfoModel = busLienInfoById;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ServicesBusLineAdapter busLineAdapter = new ServicesBusLineAdapter(busLienAll.getRows(),getContext());
                                    binding .liens.setAdapter(busLineAdapter);
                                }
                            });
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }).start();
//                }
//            }
//        });
    }



    private void initview() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.liens.setLayoutManager(manager);
    }

}