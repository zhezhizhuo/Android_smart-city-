package com.qgj.juan_05.ui.fragment.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FeedbackFragmentBinding;
import com.qgj.juan_05.netwok.model.FeedBackModel;


public class FeedbackFragment extends Fragment {

    private FeedbackViewModel mViewModel;
    private FeedbackFragmentBinding binding;
    private String TGA ="FeedbackFragment";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FeedbackFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
       initview();
    }

    private void initview() {

        binding.sumbit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = binding.titile.getText().toString();
                String yjtext = binding.yjtext.getText().toString();
                if(title.equals("")||yjtext.equals("")){
                    Toast.makeText(getActivity(), "请输入意见标题和意见内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                //包装数据
                FeedBackModel model = new FeedBackModel();
                Log.e(TGA,model+"");
                model.setTitle(title);
                model.setContent(yjtext);
                mViewModel.getData(model).observe(getViewLifecycleOwner(),smodel->{
                    if (smodel.getCode()==200) {
                        Log.e(TGA,smodel.toString()+"");
                        Toast.makeText(getActivity(), smodel.getMsg(), Toast.LENGTH_SHORT).show();
                        //跳转回userFragment界面
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.userFragment);
                    }
                    Toast.makeText(getActivity(), smodel.getMsg(), Toast.LENGTH_SHORT).show();
                });
            }
        });


    }

}