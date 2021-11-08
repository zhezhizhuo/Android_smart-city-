package com.qgj.juan_05.ui.fragment.user;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qgj.juan_05.databinding.FragmentUppasswordBinding;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.UpPassWordModel;


/**

 */
public class UppasswordFragment extends Fragment {

    FragmentUppasswordBinding binding;
    UpdateFragmentViewModel mUpdateFragmentViewModel;

    NavController mController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentUppasswordBinding.inflate(getLayoutInflater());
        mUpdateFragmentViewModel = new ViewModelProvider(this).get(UpdateFragmentViewModel.class);
        // Inflate the layout for this fragment
        initview();
        return binding.getRoot();
    }

    private void initview() {
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "onclicl", Toast.LENGTH_SHORT).show();
                //校验数据之后提交
                comint();
            }
        });
    }

    private void comint() {
        String old = binding.yuanmim.getText().toString();
        String one = binding.onepasswold.getText().toString();
        if (old.equals("")|one.equals("")){
            Toast.makeText(getActivity(), "请输入原密码或新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        UpPassWordModel passWordModel = new UpPassWordModel();
        passWordModel.setNewPassword(one);
        passWordModel.setOldPassword(old);
        mUpdateFragmentViewModel.getData(passWordModel).observe(getViewLifecycleOwner(), new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                if (dataModel.getCode()!=200) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage(dataModel.getMsg())
                            .setPositiveButton("确定",null)
                            .show();
                    return;
                }
                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                mController.navigateUp();
            }
        });
    }
}