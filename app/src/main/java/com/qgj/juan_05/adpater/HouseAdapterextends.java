package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.databinding.ItemHouseBinding;
import com.qgj.juan_05.databinding.ItemMovieplBinding;
import com.qgj.juan_05.netwok.model.HouseModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HouseAdapterextends extends RecyclerView.Adapter<HouseAdapterextends.ViewHolder> {

        List<HouseModel.RowsDTO> mList;
        Context mContext;

public HouseAdapterextends(List<HouseModel.RowsDTO> list, Context context) {
        mList = list;
        mContext = context;
        }

@NonNull
@NotNull
@Override
public HouseAdapterextends.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    ItemHouseBinding binding = ItemHouseBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new HouseAdapterextends.ViewHolder(binding);
        }

@Override
public void onBindViewHolder(@NonNull @NotNull HouseAdapterextends.ViewHolder holder, int position) {
    //获取数据元
    HouseModel.RowsDTO rowsDTO = mList.get(position);
    //加载图片
    Glide.with(mContext).load(MainActivity.serverURL+rowsDTO.getPic()).into(holder.binding.pic);
    // 标题
    holder.binding.sourceName.setText(rowsDTO.getSourceName());
    //电话
    holder.binding.tel.setText("电话:"+rowsDTO.getTel());
    //简介
   // holder.binding.description.setText(rowsDTO.getDescription());
    //价格
    holder.binding.price.setText("价格:"+rowsDTO.getPrice());
}

@Override
public int getItemCount() {
        return  mList==null? 0:mList.size();
        }

static class ViewHolder extends RecyclerView.ViewHolder{
    ItemHouseBinding binding;
    public ViewHolder(@NonNull @NotNull ItemHouseBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
}
}

