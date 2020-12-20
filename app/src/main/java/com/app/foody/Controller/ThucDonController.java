package com.app.foody.Controller;

import android.util.Log;

import com.app.foody.Controller.Interfaces.ThucDonInterface;
import com.app.foody.Model.ThucDonModel;

import java.util.List;

public class ThucDonController {
    ThucDonModel thucDonModel;

    public ThucDonController(){
        thucDonModel = new ThucDonModel();
    }
    public void getDanhSachThucDonQuanAnTheoMa(String maquanan){
        ThucDonInterface thucDonInterface = new ThucDonInterface() {
            @Override
            public void getThucDonThanhCong(List<ThucDonModel> thucDonModelList) {
                for (ThucDonModel thucDonModel : thucDonModelList){
                    Log.d("kiemtra"," "+thucDonModel.getTenthucdon());
                }

            }
        };
        thucDonModel.getDanhSachThucDonQuanAnTheoMa(maquanan,thucDonInterface);
    }

}
