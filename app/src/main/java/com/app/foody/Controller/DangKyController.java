package com.app.foody.Controller;

import com.app.foody.Model.ThanhVienModel;

public class DangKyController {
    ThanhVienModel thanhVienModel;

    public DangKyController(){
        thanhVienModel=new ThanhVienModel();
    }

   public void ThemThongTinThanhVienController(ThanhVienModel thanhVienModel,String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel,uid);
   }
}
