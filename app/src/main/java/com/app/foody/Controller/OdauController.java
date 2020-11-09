package com.app.foody.Controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.AdapterRecyclerOdau;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    QuanAnModel quanAnModel;
    Context context;
    AdapterRecyclerOdau adapterRecyclerOdau;
    public OdauController(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerOdau, final ProgressBar progressBar){
         final List<QuanAnModel> quanAnModelList=new ArrayList<>();
 RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
 recyclerOdau.setLayoutManager(layoutManager);
 adapterRecyclerOdau=new AdapterRecyclerOdau(quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);
     OdauInterfaces odauInterface=new OdauInterfaces() {
         @Override
         public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
             quanAnModelList.add(quanAnModel);
            adapterRecyclerOdau.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
         }
     };
     quanAnModel.getDanhSachQuanAn(odauInterface);
 }

}
