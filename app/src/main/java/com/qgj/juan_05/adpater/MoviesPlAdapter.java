package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.databinding.ItemMovieplBinding;
import com.qgj.juan_05.databinding.ItemWearthercenterBinding;
import com.qgj.juan_05.netwok.model.MoviePlModel;
import com.qgj.juan_05.netwok.model.WeartherModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoviesPlAdapter extends RecyclerView.Adapter<MoviesPlAdapter.ViewHolder> {

    List<MoviePlModel.RowsDTO> mList;
    Context mContext;

    public MoviesPlAdapter(List<MoviePlModel.RowsDTO>  list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public MoviesPlAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemMovieplBinding binding = ItemMovieplBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new MoviesPlAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MoviesPlAdapter.ViewHolder holder, int position) {
        //获取数据元
        MoviePlModel.RowsDTO rowsDTO = mList.get(position);
             //日期
            holder.binding.datetime.setText(rowsDTO.getCommentDate());
            //内容
            holder.binding.content.setText(rowsDTO.getContent());
            //评星
            holder.binding.lear.setRating(rowsDTO.getScore());
            //用户名
            holder.binding.username.setText(rowsDTO.getNickName());
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemMovieplBinding binding;
        public ViewHolder(@NonNull @NotNull ItemMovieplBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
