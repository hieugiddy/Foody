package com.app.foody.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.DuocDatNhieuItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.app.foody.Model.BinhLuanModel;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DuocDatNhieuAdapter extends BaseAdapter {
    public ArrayList<DuocDatNhieuItem> arrayingListener;
    Context mContext;

    public DuocDatNhieuAdapter(ArrayList<DuocDatNhieuItem> mListenerList, Context context) {
        mContext = context;
        this.arrayingListener = new ArrayList<DuocDatNhieuItem>();
        this.arrayingListener =mListenerList;
    }

    public class ViewHolder {
        ImageView anh;
        TextView title,theloai,gia,hethang;
        ImageButton themgiohang;
    }

    @Override
    public int getCount() {
        return arrayingListener.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayingListener.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.duocdatnhieu_item, null);
            holder = new ViewHolder();
            holder.anh = (ImageView) view.findViewById(R.id.anhmonan);
            holder.title=(TextView)view.findViewById(R.id.tenmon_datnhieu);
            holder.theloai=(TextView)view.findViewById(R.id.chuyenmuc);
            holder.gia=(TextView)view.findViewById(R.id.gia);
            holder.hethang=(TextView)view.findViewById(R.id.hethang);
            holder.themgiohang=(ImageButton) view.findViewById(R.id.themvaogio);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        try {
            int image = arrayingListener.get(position).getImage();
            holder.anh.setImageResource(image);
            holder.title.setText(arrayingListener.get(position).getTitle());
            holder.theloai.setText(arrayingListener.get(position).getTheloai());
            holder.gia.setText(arrayingListener.get(position).getGia());
        }catch (Exception ex){


        }

        return view;
    }

}