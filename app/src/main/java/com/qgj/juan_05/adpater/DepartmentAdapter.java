package com.qgj.juan_05.adpater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemDepartmentBinding;
import com.qgj.juan_05.databinding.ItemHomenewsBinding;
import com.qgj.juan_05.netwok.model.CardDepartmentModel;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    List<CardDepartmentModel.RowsDTO> mList;
    FragmentActivity mContext;

    NavController mController;
    public DepartmentAdapter(List<CardDepartmentModel.RowsDTO> list, FragmentActivity context, NavController navController) {
        mList = list;
        mContext = context;
        mController = navController;
    }

    @NonNull
    @NotNull
    @Override
    public DepartmentAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemDepartmentBinding binding = ItemDepartmentBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new DepartmentAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DepartmentAdapter.ViewHolder holder, int position) {
            //设置数据
        String categoryName = mList.get(position).getCategoryName();
        String money = "💴 "+mList.get(position).getMoney();
        holder.binding.name.setText(categoryName);
        holder.binding.money.setText(money);
        //按钮弹出id
        holder.binding.bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                mController.navigate(R.id.successFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDepartmentBinding binding;
        public ViewHolder(@NonNull @NotNull ItemDepartmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
