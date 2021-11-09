package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.qgj.juan_05.adpater.HomeServiceAdapter;
import com.qgj.juan_05.databinding.TypeServeFragmentBinding;
import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.util.PagerController;

import java.util.ArrayList;
import java.util.List;

public class TypeServeFragment extends Fragment {

    private TypeServeViewModel mViewModel;

    TypeServeFragmentBinding inflate;
    NavController navController;

    String type;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inflate = TypeServeFragmentBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(TypeServeViewModel.class);
        navController = NavHostFragment.findNavController(this);
        Bundle arguments = getArguments();
         type = arguments.getString("serviceType");
        return inflate.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<HomeServiceModel.RowsDTO> newss = new ArrayList<>();
        mViewModel = new ViewModelProvider(this).get(TypeServeViewModel.class);
        //服务列表
        RecyclerView.LayoutManager man = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        inflate.typeservice.setLayoutManager(man);

        mViewModel.getMserviceall().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
            @Override
            public void onChanged(HomeServiceModel homeServiceModel) {
                List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                for (HomeServiceModel.RowsDTO row : rows) {
                    if (row.getServiceType().equals(type)) {
                        newss.add(row);
                    }
                }
              //  添加到适配器里面
                if (newss.size()==0){
                    moreServier();
                    return;
                }
                inflate.listview.setVisibility(View.GONE);
                HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(newss,getContext(),navController);
                inflate.typeservice.setAdapter(serviceAdapter);
                inflate.typeservice.setVisibility(View.VISIBLE);
            }
        });

    }

    private void moreServier() {
        inflate.typeservice.setVisibility(View.GONE);
        ListView listView = inflate.listview;
        String[] strs ={"天气预报","违章查询"};
        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,strs);
        listView.setAdapter(listAdapter);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAtPosition = parent.getItemAtPosition(position);
                PagerController.PagerController(navController,itemAtPosition.toString());
            }
        });
    }

}