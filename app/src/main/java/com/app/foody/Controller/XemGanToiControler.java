package com.app.foody.Controller;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.QuanAnAdapter;
import com.app.foody.Adapters.XemGanDayAdapter;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XemGanToiControler {
    QuanAnModel quanAnModel;
    Context context;
    XemGanDayAdapter adapterRecyclerGanToi;

    public XemGanToiControler(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerOdau, final ProgressBar progressBar, final Location viTriHienTai){
        final List<QuanAnModel> quanAnModelList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(context,2);
        recyclerOdau.setLayoutManager(layoutManager1);
        adapterRecyclerGanToi=new XemGanDayAdapter(context,quanAnModelList, R.layout.custom_layout_recyclerview_xemganday);
        recyclerOdau.setAdapter(adapterRecyclerGanToi);
        final OdauInterfaces odauInterface=new OdauInterfaces() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                Collections.sort(quanAnModelList);
                adapterRecyclerGanToi.notifyDataSetChanged();
                Collections.sort(quanAnModelList);
                progressBar.setVisibility(View.GONE);
            }


        };

        quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai);
    }
}
