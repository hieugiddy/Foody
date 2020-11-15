package com.app.foody.Controller;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.app.foody.Adapters.MoiDayAdapter;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.Product;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

public class xemgandayController {
    Product quanAnModel;
    Context context;
    MoiDayAdapter gridViewAdapter;
    public xemgandayController(Context context) {
        this.context=context;
        quanAnModel=new Product();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerOdau, final ProgressBar progressBar){
         final List<Product> quanAnModelList=new ArrayList<>();

       RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(context,2);
        recyclerOdau.setLayoutManager(layoutManager1);
        gridViewAdapter =new MoiDayAdapter(quanAnModelList, R.layout.custom_layout_recyclerview_xemganday);
        recyclerOdau.setAdapter(gridViewAdapter);
        //
     OdauInterfaces odauInterface=new OdauInterfaces() {
         @Override
         public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {

         }

         @Override
         public void getDanhSachQuanAnModel(Product quanAnModel) {
            quanAnModelList.add(quanAnModel);
           gridViewAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
         }
     };
     quanAnModel.getDanhSachQuanAn(odauInterface);
 }

}
