/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
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
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    private final String INSERT_SQL="INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, Mota) VALUES (?, ?, ?, ?,?,?)";
    private final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ? , Hinh = ?  , Mota = ?  WHERE MaCD = ?";
    private final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD=? ";
    @Override
    public void insert(ChuyenDe entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),entity.getHinh(), entity.getMoTa());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(ChuyenDe entity) {
        try {
            XJdbc.update(UPDATE_SQL, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs =  XJdbc.query(sql, args);
            while (rs.next()) {                
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString("MaCD"));
                cd.setTenCD(rs.getString("TenCD"));
                cd.setHocPhi(rs.getDouble("HocPhi"));
                cd.setThoiLuong(rs.getInt("ThoiLuong"));
                cd.setHinh(rs.getString("Hinh"));
                cd.setMoTa(rs.getString("MoTa"));
                list.add(cd);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}
