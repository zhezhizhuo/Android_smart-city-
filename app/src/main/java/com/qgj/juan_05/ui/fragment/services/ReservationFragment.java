package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentReservationBinding;
import com.qgj.juan_05.netwok.model.CardDepartmentModel;
import com.qgj.juan_05.netwok.model.CardInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReservationFragment extends Fragment {


    FragmentReservationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    NavController navController ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReservationBinding.inflate(getLayoutInflater());
        navController  = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            initview();
            initdate();
    }

    private void initdate() {
        ArrayList<String> strings = new ArrayList<>();

        new Thread(()->{
            try {
                CardDepartmentModel departmentAll = ServiceDaoImpl.getDepartmentAll();
                List<CardDepartmentModel.RowsDTO> rows = departmentAll.getRows();
                for (CardDepartmentModel.RowsDTO row : rows) {
                    strings.add(row.getCategoryName());
                }
               getActivity().runOnUiThread(()->{
                   ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1,strings.toArray());
                   binding.listview.setAdapter(arrayAdapter);

               });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
        //初始化数据

    }

    private void initview() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navController.navigate(R.id.departmentFragment);
            }
        });
    }
}