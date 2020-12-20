package com.app.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.GanToiListViewBean_GiaoNhanh;
import com.app.foody.R;

import java.util.List;

public class AdapterRecyclerView_GiaoNhanh extends RecyclerView.Adapter<AdapterRecyclerView_GiaoNhanh.ViewHolder> {

    private List<GanToiListViewBean_GiaoNhanh> recyclerViewBeans;
    private LayoutInflater mInflater;

    public AdapterRecyclerView_GiaoNhanh(Context context, List<GanToiListViewBean_GiaoNhanh> colors) {
        this.mInflater = LayoutInflater.from(context);
        this.recyclerViewBeans = colors;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.giaonhanh_recycler_gantoi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GanToiListViewBean_GiaoNhanh recyclerViewBean = recyclerViewBeans.get(position);
        holder.anh.setImageResource(recyclerViewBean.getAnh());
        holder.monan.setText(recyclerViewBean.getMonan());
        holder.gia.setText(recyclerViewBean.getGia());
        holder.quangduong.setText(recyclerViewBean.getQuangduong());
        holder.thoigian.setText(recyclerViewBean.getThoigian());
    }

    @Override
    public int getItemCount() {
        return recyclerViewBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView anh;
        TextView monan, gia, quangduong, thoigian;
        public ViewHolder(View itemView){
            super(itemView);
            anh =(ImageView)itemView.findViewById(R.id.imgMonAnGanToi);
            monan = (TextView)itemView.findViewById(R.id.tvMonAnGanToi);
            gia = (TextView)itemView.findViewById(R.id.tvGiaTienGanToi);
            quangduong = (TextView)itemView.findViewById(R.id.tvQuangDuongGanToi);
            thoigian = (TextView)itemView.findViewById(R.id.tvQuangDuongGanToi);
        }
    }
}
