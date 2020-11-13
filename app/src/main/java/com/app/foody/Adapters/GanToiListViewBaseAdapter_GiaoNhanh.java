package com.app.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.foody.Model.GanToiListViewBean_GiaoNhanh;
import com.app.foody.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class GanToiListViewBaseAdapter_GiaoNhanh extends BaseAdapter {
    public ArrayList<GanToiListViewBean_GiaoNhanh> arrayListGanToi;
    private List<GanToiListViewBean_GiaoNhanh> listGanToi;
    Context mContext;

    public GanToiListViewBaseAdapter_GiaoNhanh(List<GanToiListViewBean_GiaoNhanh> ganToiListViewBeans, Context context) {
        mContext = context;
        this.listGanToi = ganToiListViewBeans;
        this.arrayListGanToi = new ArrayList<GanToiListViewBean_GiaoNhanh>();
        this.arrayListGanToi.addAll(ganToiListViewBeans);
    }
    public class  ViewHolder{
        ShapeableImageView anh;
        TextView monan, gia, quangduong, thoigian;
    }
    @Override
    public int getCount() {
        return listGanToi.size();
    }

    @Override
    public Object getItem(int position) {
        return listGanToi.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.giaonhanh_recycler_gantoi,null);
            viewHolder = new ViewHolder();
            viewHolder.anh =(ShapeableImageView) convertView.findViewById(R.id.imgMonAnGanToi);
            viewHolder.monan = (TextView)convertView.findViewById(R.id.tvMonAnGanToi);
            viewHolder.gia = (TextView)convertView.findViewById(R.id.tvGiaTienGanToi);
            viewHolder.quangduong = (TextView)convertView.findViewById(R.id.tvQuangDuongGanToi);
            viewHolder.thoigian = (TextView)convertView.findViewById(R.id.tvThoiGianGanToi);
            convertView.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder)convertView.getTag();
        }
        try{
            int image = listGanToi.get(position).getAnh();
            viewHolder.anh.setImageResource(image);
            viewHolder.monan.setText(listGanToi.get(position).getMonan());
            viewHolder.gia.setText(listGanToi.get(position).getGia());
            viewHolder.quangduong.setText(listGanToi.get(position).getQuangduong());
            viewHolder.thoigian.setText(listGanToi.get(position).getThoigian());
        }catch (Exception ex){
        }
        return convertView;
    }
}
