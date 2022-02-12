/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
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
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    private final String INSERT_SQL = "INSERT INTO KhoaHoc (MaKH, MaCD, HocPhi, ThoiLuong , NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?,?,?)";
    private final String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ? , GhiChu = ? , MaNV = ?, NgayTao = ?   WHERE MaKH = ?";
    private final String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH = ?";
    private final String SELECT_ALL_SQL = "SELECT* FROM KhoaHoc";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH = ?";

    @Override
    public void insert(KhoaHoc entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaKH(), entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(KhoaHoc entity) {
        try {
            XJdbc.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return  this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {                
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setMaCD(rs.getString("MaCD"));
                kh.setHocPhi(rs.getDouble("HocPhi"));
                kh.setThoiLuong(rs.getInt("ThoiLuong"));
                kh.setNgayKG(rs.getDate("NgayKG"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setMaNV(rs.getString("MaNV"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                list.add(kh);
            }
            
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        
        return list;
    }
    public List<KhoaHoc> selectByChuyenDe(String maCD){
        String sql = "SELECT * FROM KhoaHoc where MaCD=?";
        return selectBySQL(sql, maCD);
    }
}
