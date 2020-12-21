package com.app.foody.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.MonAnModel;
import com.app.foody.R;

import java.util.List;

public class AdapterMonAn extends RecyclerView.Adapter<AdapterMonAn.HolderMonAn> {
    Context context;
    List<MonAnModel> monAnModelList;

    public AdapterMonAn(Context context, List<MonAnModel> monAnModelList){
        this.context = context;
        this.monAnModelList = monAnModelList;
    }


    @NonNull
    @Override
    public HolderMonAn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_monan,parent,false);

        return new HolderMonAn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderMonAn holder, int position) {
        MonAnModel monAnModel = monAnModelList.get(position);
        holder.txtTenMonAn.setText(monAnModel.getTenmon());

        holder.txtSoLuong.setTag(0);

        //img Tang so luong
        holder.imgTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem = Integer.parseInt(holder.txtSoLuong.getTag().toString());
                dem++;
                holder.txtSoLuong.setText(dem + "");
                holder.txtSoLuong.setTag(dem);

            }
        });

        //img Giam so luong
        holder.imgGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem = Integer.parseInt(holder.txtSoLuong.getTag().toString());
                if(dem != 0){
                    dem--;
                }
                holder.txtSoLuong.setText(dem + "");
                holder.txtSoLuong.setTag(dem);

            }
        });
    }

    @Override
    public int getItemCount() {
        return monAnModelList.size();
    }

    public class HolderMonAn extends RecyclerView.ViewHolder {
        TextView txtTenMonAn, txtSoLuong;
        ImageView imgGiamSoLuong, imgTangSoLuong;

        public HolderMonAn(@NonNull View itemView) {
            super(itemView);
            txtTenMonAn = (TextView) itemView.findViewById(R.id.txtTenMonAn);
            txtSoLuong = (TextView) itemView.findViewById(R.id.txtSoLuong);
            imgGiamSoLuong = (ImageView) itemView.findViewById(R.id.imgGiamSoLuong);
            imgTangSoLuong = (ImageView) itemView.findViewById(R.id.imgTangSoLuong);

        }
    }
}
