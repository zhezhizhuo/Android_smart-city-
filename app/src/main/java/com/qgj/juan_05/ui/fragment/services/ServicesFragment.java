package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HomeServiceAdapter;
import com.qgj.juan_05.databinding.ServicesFragmentBinding;
import com.qgj.juan_05.netwok.model.HomeServiceModel;

import java.util.ArrayList;
import java.util.List;

public class ServicesFragment extends Fragment {

    private ServicesViewModel mViewModel;
    ServicesFragmentBinding binding;
   NavController navController;
    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = ServicesFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot() ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        // TODO: Use the ViewModel
        //给所有的服务类型添加点击事件
        onclickServoceyTpe();
        RecyclerView.LayoutManager man = new GridLayoutManager(getActivity(),3);
        binding.serviceType.setLayoutManager(man);
        mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
            @Override
            public void onChanged(HomeServiceModel homeServiceModel) {
                List<HomeServiceModel.RowsDTO> newss = new ArrayList<>();
                List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                for (HomeServiceModel.RowsDTO row : rows) {
                    if (row.getServiceType().equals(binding.bmtext.getText().toString())) {
                        newss.add(row);
                    }
                }
                //  添加到适配器里面
                if (newss.size()==0){
                    return;
                }
                HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(newss,getContext(),navController);
                setRightData(serviceAdapter);
            }
        });
    }

    public void setRightData(HomeServiceAdapter serviceAdapter){
        binding.serviceType.setAdapter(serviceAdapter);
    }
    //被点击的按钮
    LinearLayout temp;
    private void onclickServoceyTpe() {
//        binding.bg.setBackgroundColor(getActivity().getResources().getColor(R.color.serversbg));
//        temp= binding.bmfw;
//        temp.setBackgroundColor(Color.WHITE);
          binding.bmfw.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
//                  Bundle bundle = new Bundle();
//                  bundle.putString("serviceType",binding.bmtext.getText().toString());
//                  temp.setBackgroundColor(getActivity().getResources().getColor(R.color.serversbg));
//                  temp = binding.bmfw;
//                  temp.setBackgroundColor(Color.WHITE);
                  //设置数据
                  mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
                      @Override
                      public void onChanged(HomeServiceModel homeServiceModel) {
                          List<HomeServiceModel.RowsDTO> newss = new ArrayList<>();
                          List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                          for (HomeServiceModel.RowsDTO row : rows) {
                              if (row.getServiceType().equals(binding.bmtext.getText().toString())) {
                                  newss.add(row);
                              }
                          }
                          //  添加到适配器里面
                          if (newss.size()==0){
                              return;
                          }
                          HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(newss,getContext(),navController);
                          setRightData(serviceAdapter);
                      }
                  });
              }
          });
        binding.czfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("serviceType",binding.cztext.getText().toString());
//               // Toast.makeText(getActivity(), binding.cztext.getText().toString(), Toast.LENGTH_SHORT).show();
//                navController.navigate(R.id.typeServeFragment,bundle);
//                temp.setBackgroundColor(getActivity().getResources().getColor(R.color.serversbg));
//                temp = binding.czfw;
//                temp.setBackgroundColor(Color.WHITE);
                mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
                    @Override
                    public void onChanged(HomeServiceModel homeServiceModel) {
                        List<HomeServiceModel.RowsDTO> newss = new ArrayList<>();
                        List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                        for (HomeServiceModel.RowsDTO row : rows) {
                            if (row.getServiceType().equals(binding.cztext.getText().toString())) {
                                newss.add(row);
                            }
                        }
                        //  添加到适配器里面
                        if (newss.size()==0){
                            return;
                        }
                        HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(newss,getContext(),navController);
                        setRightData(serviceAdapter);
                    }
                });
            }
        });
        binding.shfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("serviceType",binding.chtext.getText().toString());
//               // Toast.makeText(getActivity(), binding.chtext.getText().toString(), Toast.LENGTH_SHORT).show();
//                navController.navigate(R.id.typeServeFragment,bundle);
//                temp.setBackgroundColor(getActivity().getResources().getColor(R.color.serversbg));
//                temp = binding.shfw;
//                temp.setBackgroundColor(Color.WHITE);
                mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
                    @Override
                    public void onChanged(HomeServiceModel homeServiceModel) {
                        List<HomeServiceModel.RowsDTO> newss = new ArrayList<>();
                        List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                        for (HomeServiceModel.RowsDTO row : rows) {
                            if (row.getServiceType().equals(binding.chtext.getText().toString())) {
                                newss.add(row);
                            }
                        }
                        //  添加到适配器里面
                        if (newss.size()==0){
                            return;
                        }
                        HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(newss,getContext(),navController);
                        setRightData(serviceAdapter);
                    }
                });
            }
        });
        binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("serviceType",binding.moretext.getText().toString());
//               // Toast.makeText(getActivity(), binding.moretext.getText().toString(), Toast.LENGTH_SHORT).show();
//                navController.navigate(R.id.typeServeFragment,bundle);
//                temp.setBackgroundColor(getActivity().getResources().getColor(R.color.serversbg));
//                temp = binding.more;
//                temp.setBackgroundColor(Color.WHITE);
                mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
                    @Override
                    public void onChanged(HomeServiceModel homeServiceModel) {
                        List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();

                        HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(rows,getContext(),navController);
                        setRightData(serviceAdapter);
                    }
                });
            }
        });
    }

}