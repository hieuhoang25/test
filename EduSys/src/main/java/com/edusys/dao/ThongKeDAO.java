/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HieuHoang
 */
public class ThongKeDAO {
    public List<Object[]> getListOfArray(String sql , String[] cols, Object... args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       
    }
    public List<Object[]> getBangDiem(Integer makh){
         return null;
    }
    public List<Object[]> getDiemChuyenDe(){
       String sql = "{call sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols);
      
    }
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{call sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThu(int nam){
        
        return null;
        
    }
    
}
