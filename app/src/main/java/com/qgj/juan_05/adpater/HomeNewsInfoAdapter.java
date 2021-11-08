package com.qgj.juan_05.adpater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemHomenewsBinding;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeNewsInfoAdapter extends RecyclerView.Adapter<HomeNewsInfoAdapter.ViewHolder> {

    List<HomeNewAllModel.RowsDTO> mList;
    FragmentActivity mContext;

    NavController mController;
    public HomeNewsInfoAdapter(List<HomeNewAllModel.RowsDTO> list, FragmentActivity context, NavController navController) {
        mList = list;
        mContext = context;
        mController = navController;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemHomenewsBinding binding = ItemHomenewsBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        HomeNewAllModel.RowsDTO dataDTO = mList.get(position);
        //日期
        holder.binding.datetime.setText(dataDTO.getCreateTime());
        //点赞数量
        holder.binding.likeNum.setText(String.valueOf(dataDTO.getLikeNum())+"点赞量");
        //评论数量
        holder.binding.commentNum.setText(dataDTO.getCommentNum()+"评论量");
        holder.binding.newsTitlt.setText(dataDTO.getTitle());
        holder.binding.newsContent.setText(dataDTO.getContent());
        //阅读数量
        holder.binding.read.setText(String.valueOf(dataDTO.getReadNum())+"阅读量");
        //加载图片
        Glide.with(mContext)
                .load(MainActivity.serverURL + dataDTO.getCover())
                .fitCenter()
                .into(holder.binding.newimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("getNewsInfoById",dataDTO.getId());
                mController.navigate(R.id.newsinfo_Fragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemHomenewsBinding binding;
        public ViewHolder(@NonNull @NotNull ItemHomenewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}