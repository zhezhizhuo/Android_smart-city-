package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentAddCardBinding;
import com.qgj.juan_05.netwok.model.AddCardModel;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class AddCardFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentAddCardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCardBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //放回
        NavController navController = NavHostFragment.findNavController(this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
        //添加car
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验数据
                if(infodata()){
                    Toast.makeText(getActivity(), "请输入完整信息！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //拿数据
                /**
                 * {
                 * "address": "大连市甘井子区",
                 * "birthday": "2001-09-10",
                 * "cardId": "210882199001302318",
                 * "name": "张三",
                 * "sex": "0",
                 * "tel": "15800000000"
                 * }
                 */
                String address = binding.adress.getText().toString();
                String birthday = binding.birthday.getText().toString();
                String cardId = binding.cardId.getText().toString();
                String name = binding.name.getText().toString();
                String tel = binding.tel.getText().toString();

                RadioButton radioButton = getActivity().findViewById(binding.sex.getCheckedRadioButtonId());
                int i =1;
                if (radioButton.getText().toString().equals("男")) {
                   i=0;
                }
                AddCardModel cardModel = new AddCardModel(address,birthday,cardId,name,i,tel);
                //发送请求
                addcard(cardModel);
            }
        });
    }

    private void addcard(AddCardModel cardModel) {
        new Thread(()->{
            try {
                DataModel dataModel = ServiceDaoImpl.addCard(MainActivity.token, cardModel);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), dataModel.getMsg(), Toast.LENGTH_SHORT).show();
                        if (dataModel.getCode()==200){
                            NavController navController = NavHostFragment.findNavController(AddCardFragment.this);
                            navController.navigate(R.id.cardFragment);
                        }
                    }
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }

    private boolean infodata() {

        String address = binding.adress.getText().toString();
        String birthday = binding.birthday.getText().toString();
        String cardId = binding.cardId.getText().toString();
        String name = binding.name.getText().toString();
        String tel = binding.tel.getText().toString();
        if (address.equals("")){
            return true;
        }
        if (birthday.equals("")){
            return true;
        }
        if (cardId.equals("")){
            return true;
        }
        if (name.equals("")){
            return true;
        }
        if (tel.equals("")){
            return true;
        }

        return false;
    }
}