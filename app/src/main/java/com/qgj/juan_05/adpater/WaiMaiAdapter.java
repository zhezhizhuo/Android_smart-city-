package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.databinding.ItemHouseBinding;
import com.qgj.juan_05.databinding.ItemWaimaiBinding;
import com.qgj.juan_05.netwok.model.HouseModel;
import com.qgj.juan_05.netwok.model.WaiMai2Model;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WaiMaiAdapter extends RecyclerView.Adapter<WaiMaiAdapter.ViewHolder> {

        List<WaiMai2Model.RowsDTO> mList;
        Context mContext;

public WaiMaiAdapter(List<WaiMai2Model.RowsDTO> list, Context context) {
        mList = list;
        mContext = context;
        }

@NonNull
@NotNull
@Override
public WaiMaiAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    ItemWaimaiBinding binding = ItemWaimaiBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new WaiMaiAdapter.ViewHolder(binding);
        }

@Override
public void onBindViewHolder(@NonNull @NotNull WaiMaiAdapter.ViewHolder holder, int position) {
    //获取数据元
    WaiMai2Model.RowsDTO rowsDTO = mList.get(position);
    //加载图片
    Glide.with(mContext).load(MainActivity.serverURL+rowsDTO.getImgUrl()).into(holder.binding.imgUrl);
    // 标题
    holder.binding.sourceName.setText(rowsDTO.getName());
    //电话
    holder.binding.introduction.setText(rowsDTO.getIntroduction());
    //价格
    holder.binding.distance.setText("距离: "+rowsDTO.getDistance()+"Km");
    //评分
    holder.binding.score.setText("评分: "+rowsDTO.getScore());
    //地址
    holder.binding.address.setText("地址: "+rowsDTO.getAddress());
    //销售量
    holder.binding.saleQuantity.setText("销售量 "+rowsDTO.getSaleQuantity());
    //均价
    holder.binding.avgCost.setText("   "+rowsDTO.getAvgCost()+"💴");
    //配送时间
   // holder.binding.deliveryTime.setText(" "+rowsDTO.getDistance()+"min");

}

@Override
public int getItemCount() {
        return  mList==null? 0:mList.size();
        }

static class ViewHolder extends RecyclerView.ViewHolder{
    ItemWaimaiBinding binding;
    public ViewHolder(@NonNull @NotNull ItemWaimaiBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
}
}

