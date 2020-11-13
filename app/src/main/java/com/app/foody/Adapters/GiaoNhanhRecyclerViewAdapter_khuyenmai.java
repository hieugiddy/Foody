package com.app.foody.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.RecyclerViewBean_GiaoNhanh;
import com.app.foody.R;

import java.util.List;

public class GiaoNhanhRecyclerViewAdapter_khuyenmai extends RecyclerView.Adapter<GiaoNhanhRecyclerViewAdapter_khuyenmai.ViewHolder> {

    private List<RecyclerViewBean_GiaoNhanh> recyclerViewBeans;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public GiaoNhanhRecyclerViewAdapter_khuyenmai(Activity context, List<RecyclerViewBean_GiaoNhanh> colors) {
        this.mInflater = LayoutInflater.from(context);
        this.recyclerViewBeans = colors;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.giaonhanh_recyclerview_item_khuyenmai,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerViewBean_GiaoNhanh recyclerViewBean = recyclerViewBeans.get(position);
        holder.myView.setImageResource(recyclerViewBean.getImage());
        holder.myTextView.setText(recyclerViewBean.getCourse());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return recyclerViewBeans.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;
        ViewHolder(View itemView) {
            super(itemView);
            myView =(ImageView)itemView.findViewById(R.id.colorView);
            myTextView = (TextView)itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return null;
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
