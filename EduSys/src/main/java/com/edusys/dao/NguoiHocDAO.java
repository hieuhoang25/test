/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuHoang
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    private final String INSERT_SQL="INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK)  VALUES(?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=?, NgayDK=? WHERE MaNH=?";
    private final String DELETE_SQL = "DELETE FROM NguoiHoc where MaNH=?";
    private final String SELETE_ALL_SQL = "SELECT * FROM NguoiHoc";
    private final String SELETE_BY_ID_SQL = "SELECT * FROM NguoiHoc where MaNH=?";
    @Override
    public void insert(NguoiHoc entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(),entity.getMaNV(), entity.getNgayDK());
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
    }

    @Override
    public void update(NguoiHoc entity) {
        try {
            XJdbc.update(UPDATE_SQL, entity.getHoTen(),entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(),entity.getNgayDK(),entity.getMaNH());
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list= selectBySQL(SELETE_BY_ID_SQL, id);
        if (list.isEmpty()) return null;
        return list.get(0);
        
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySQL(SELETE_ALL_SQL);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
          List<NguoiHoc> list = new ArrayList<>();
        try {
          
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {                
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString("MaNH"));
                nh.setHoTen(rs.getString("HoTen"));
                nh.setNgaySinh(rs.getDate("NgaySinh"));
                nh.setGioiTinh(rs.getBoolean("GioiTinh"));
                nh.setDienThoai(rs.getString("DienThoai"));
                nh.setEmail(rs.getString("Email"));
                nh.setGhiChu(rs.getString("GhiChu"));
                nh.setMaNV(rs.getString("MaNV"));
                nh.setNgayDK(rs.getDate("NgayDK"));
                list.add(nh);
            }
           
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
         return list;
    }
    public List<NguoiHoc>  selectByKeyword(String keyword){
     
        String sql = "SELECT * FROM NguoiHoc where HoTen like '%"+keyword+"%'";
        return this.selectBySQL(sql);
    }
}
