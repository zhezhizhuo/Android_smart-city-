package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.databinding.ItemCardBinding;
import com.qgj.juan_05.databinding.ItemWearthercenterBinding;
import com.qgj.juan_05.netwok.model.CardInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ServicesCardAdapter extends RecyclerView.Adapter<ServicesCardAdapter.ViewHolder> {

    List<CardInfoModel.RowsDTO> mList;
    Context mContext;

    public ServicesCardAdapter(List<CardInfoModel.RowsDTO>  list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ServicesCardAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(mContext));
        return new ServicesCardAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ServicesCardAdapter.ViewHolder holder, int position) {
        //获取数据元
        CardInfoModel.RowsDTO data = mList.get(position);
        holder.binding.name.setText("姓名 :  "+data.getName());
        holder.binding.iphone.setText("手机号码 :  "+data.getCardId());
        holder.binding.sfz.setText("身份证 :  "+data.getTel());
        holder.binding.blh.setText("病例号 :  "+data.getId());
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemCardBinding binding;
        public ViewHolder(@NonNull @NotNull ItemCardBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}