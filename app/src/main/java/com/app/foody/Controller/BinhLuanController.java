package com.app.foody.Controller;

import com.app.foody.Model.BinhLuanModel;

import java.util.List;

public class BinhLuanController {
        BinhLuanModel  binhLuanModel;
        public BinhLuanController(){
            binhLuanModel=new BinhLuanModel();
        }
        public void ThemBinhluan(String maQuanAn, BinhLuanModel binhLuanModel, List<String> listHinh){
            binhLuanModel.ThemBinhluan(maQuanAn,binhLuanModel,listHinh);
        }

}
