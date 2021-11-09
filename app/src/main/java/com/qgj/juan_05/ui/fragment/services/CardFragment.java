package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.ServicesCardAdapter;
import com.qgj.juan_05.databinding.FragmentCardBinding;
import com.qgj.juan_05.netwok.model.CardInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class CardFragment extends Fragment {

    FragmentCardBinding binding;

    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCardBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            initview();
            initdata();
    }

    private void initdata() {
        new Thread(()->{
            try {
                CardInfoModel cardInfo = ServiceDaoImpl.getCardInfo(MainActivity.token);
                Log.e("TGA",cardInfo.getMsg());

                ServicesCardAdapter adapter = new ServicesCardAdapter(cardInfo.getRows(),getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),  cardInfo.getMsg()+"", Toast.LENGTH_SHORT).show();
                        binding.card.setAdapter(adapter);
                    }
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }

    private void initview() {
        binding.card.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.outpatientFragment);
            }
        });
        binding.addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                navController.navigate(R.id.addCardFragment);
            }
        });
    }
}