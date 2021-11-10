 package com.qgj.juan_05.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemBuslineBinding;
import com.qgj.juan_05.databinding.ItemLiensBinding;
import com.qgj.juan_05.databinding.ItemWearthercenterBinding;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicesBusLineAdapter extends RecyclerView.Adapter<ServicesBusLineAdapter.ViewHolder> {

    List<BusLienModel.RowsDTO> mList;
    Context mContext;


    ItemBuslineBinding   binding;
    public ServicesBusLineAdapter(List<BusLienModel.RowsDTO>   list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ServicesBusLineAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemBuslineBinding binding = ItemBuslineBinding.inflate(LayoutInflater.from(mContext),parent,false);
        ViewHolder holder = new ViewHolder(binding);
        Animation rotateAnimation = new RotateAnimation(0, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(50);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        holder.binding.checked.setAnimation(rotateAnimation);
        holder.binding.checked.setOnClickListener(new View.OnClickListener() {
            boolean flag;
            @Override
            public void onClick(View v) {
                if(flag){
                    binding.liest.setVisibility(View.GONE);
//                    holder.binding.checked.setRotationY(holder.binding.checked.getHeight()/2);
//                    holder.binding.checked.setRotationX(holder.binding.checked.getWidth()/2);
                    binding.checked.setRotation(0);
                    flag=false;
                }else {
                    binding.liest.setVisibility(View.VISIBLE);
//                    holder.binding.checked.setRotationY(holder.binding.checked.getHeight()/2);
//                    holder.binding.checked.setRotationX(holder.binding.checked.getWidth()/2);
                    binding.checked.setRotation(90);
                    flag=true;
                }
            }
        });
//        BusLienModel.RowsDTO rowsDTO = mList.get(1);
//        holder.binding.prices.setText("  $"+rowsDTO.getPrice());
//        if (rowsDTO.mInfoModel!=null) {
//            List<BusLineInfoModel.RowsDTO> rows = rowsDTO.mInfoModel;
//            Log.e("log",rows.toString());
//            holder.binding.liest.setAdapter(new BusListview(rows,mContext));
//        }
//        if (rowsDTO.mInfoModel.getRows()!=null) {
//            List<BusLineInfoModel.RowsDTO> rows = rowsDTO.mInfoModel.getRows();
//            holder.binding.liest.setAdapter(new BusListview(rows,mContext));
//        }
//        holder.
        return holder;
    }
    int mInt;
    @Override
    public void onBindViewHolder(@NonNull @NotNull ServicesBusLineAdapter.ViewHolder holder, int position) {
        //获取数据元
        BusLienModel.RowsDTO rowsDTO = mList.get(position);
        binding = holder.binding;
        mInt = rowsDTO.getId();
        holder.binding.lienname.setText(rowsDTO.getName());
        holder.binding.beginEnd.setText(rowsDTO.getFirst()+" -- "+rowsDTO.getEnd());
        holder.binding.end.setText(rowsDTO.getStartTime()+"-"+rowsDTO.getEndTime());
        holder.binding.begin.setText(rowsDTO.getStartTime()+"-"+rowsDTO.getEndTime());
        holder.binding.mileage.setText(rowsDTO.getMileage()+".0km");
        holder.binding.prices.setText("  $"+rowsDTO.getPrice());
        if (rowsDTO.mInfoModel!=null) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            holder.binding.liest.setLayoutManager(manager);
            List<BusLineInfoModel.RowsDTO> rows = rowsDTO.mInfoModel;
            Log.e("log",rows.toString());
            holder.binding.liest.setAdapter(new BusListview(rows,mContext));
        }
        //获取根据id所有的路径
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,strs);
//            holder.binding.liest.setAdapter(arrayAdapter);
//      binding.liest.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        //数据源拿到
//       rowsDTO.getInfoModel().getRows(); //数据给listview

       // binding.liest.setAdapter(new BusListview(infoModel.getRows(),mContext));
//            ArrayList<String> str = new ArrayList<>();
//            for (BusLineInfoModel.RowsDTO row : infoModel.getRows()) {
//                String name = row.getName();
//                str.add(name);
//            }
//        holder.binding.liest.setAdapter(new RecyclerView.Adapter() {
//            ItemLiensBinding binding;
//            @NonNull
//            @NotNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//                this.binding = ItemLiensBinding.inflate(LayoutInflater.from(mContext));
//                return new ViewHolder(binding);
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
//                String name = infoModel.getRows().get(position).getName();
//                Log.e("TGA",infoModel.getRows().get(position).getName());
//                binding.t1.setText(name);
//            }
//
//            @Override
//            public int getItemCount() {
////                return infoModel.getRows()==null ? 0:infoModel.getRows().size();
//                return 0;
//            }
//
//             class ViewHolder extends RecyclerView.ViewHolder {
//                 ItemLiensBinding binding;
//                 public ViewHolder(@NonNull @NotNull ItemLiensBinding binding) {
//                    super(binding.getRoot());
//                    this.binding=binding;
//                }
//            }
//        });
    }
    private void setData(int position) {

    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        ItemBuslineBinding binding;
        public ViewHolder(@NonNull @NotNull ItemBuslineBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}