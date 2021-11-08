package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ServicesFragmentBinding;

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
    }

    private void onclickServoceyTpe() {

          binding.bmfw.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Bundle bundle = new Bundle();
                  bundle.putString("serviceType",binding.bmtext.getText().toString());
                  navController.navigate(R.id.typeServeFragment,bundle);
              }
          });
        binding.czfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType",binding.cztext.getText().toString());
               // Toast.makeText(getActivity(), binding.cztext.getText().toString(), Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.typeServeFragment,bundle);

            }
        });
        binding.shfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType",binding.chtext.getText().toString());
               // Toast.makeText(getActivity(), binding.chtext.getText().toString(), Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.typeServeFragment,bundle);

            }
        });
        binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType",binding.moretext.getText().toString());
               // Toast.makeText(getActivity(), binding.moretext.getText().toString(), Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.typeServeFragment,bundle);
            }
        });
    }

}