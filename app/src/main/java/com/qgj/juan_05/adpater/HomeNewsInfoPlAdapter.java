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
import com.qgj.juan_05.databinding.ItemPlBinding;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.NewsInfoplModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeNewsInfoPlAdapter  extends RecyclerView.Adapter<HomeNewsInfoPlAdapter.ViewHolder> {

    List<NewsInfoplModel.RowsDTO> mList;
    FragmentActivity mContext;

    NavController mController;
    public HomeNewsInfoPlAdapter(List<NewsInfoplModel.RowsDTO> list, FragmentActivity context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public HomeNewsInfoPlAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemPlBinding binding = ItemPlBinding.inflate(LayoutInflater.from(mContext));
        return new HomeNewsInfoPlAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeNewsInfoPlAdapter.ViewHolder holder, int position) {
        //获取数据元
        NewsInfoplModel.RowsDTO rowsDTO = mList.get(position);
        holder.binding.content.setText(TextUtil.FormatString(rowsDTO.getContent()));
        holder.binding.username.setText("昵称: "+rowsDTO.getUserName());
        holder.binding.datime.setText(rowsDTO.getCommentDate());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPlBinding binding;
        public ViewHolder(@NonNull @NotNull ItemPlBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
