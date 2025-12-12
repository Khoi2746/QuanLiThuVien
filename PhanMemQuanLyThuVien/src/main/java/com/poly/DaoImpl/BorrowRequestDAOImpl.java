package com.fpoly.DaoImpl;

import com.fpoly.dao.BorrowRequestDao;
import com.fpoly.entity.BorrowRequest;
import com.fpoly.utils.XJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowRequestDaoImpl implements BorrowRequestDao {

    private static final String INSERT_SQL =
        "INSERT INTO BorrowRequest (UserID, HoTen, MSSV, Email, PhoneNumber, MaSach, SoLuong, Status, Note) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
        "UPDATE BorrowRequest SET UserID=?, HoTen=?, MSSV=?, Email=?, PhoneNumber=?, MaSach=?, SoLuong=?, Status=?, Note=? " +
        "WHERE RequestID=?";

    private static final String DELETE_SQL =
        "DELETE FROM BorrowRequest WHERE RequestID=?";

    private static final String SELECT_ALL_SQL =
        "SELECT * FROM BorrowRequest";

    private static final String SELECT_BY_ID_SQL =
        "SELECT * FROM BorrowRequest WHERE RequestID=?";

    private static final String SELECT_BY_USER_SQL =
        "SELECT * FROM BorrowRequest WHERE UserID=?";

    private static final String SELECT_BY_STATUS_SQL =
        "SELECT * FROM BorrowRequest WHERE Status=?";

    @Override
    public void insert(BorrowRequest br) {
        try {
            XJDBC.update(INSERT_SQL,
                    br.getUserID(),
                    br.getHoTen(),
                    br.getMSSV(),
                    br.getEmail(),
                    br.getPhoneNumber(),
                    br.getMaSach(),
                    br.getSoLuong(),
                    br.getStatus(),
                    br.getNote()
            );
        } catch (SQLException ex) {
            Logger.getLogger(BorrowRequestDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(BorrowRequest br) {
        try {
            XJDBC.update(UPDATE_SQL,
                    br.getUserID(),
                    br.getHoTen(),
                    br.getMSSV(),
                    br.getEmail(),
                    br.getPhoneNumber(),
                    br.getMaSach(),
                    br.getSoLuong(),
                    br.getStatus(),
                    br.getNote(),
                    br.getRequestID()
            );
        } catch (SQLException ex) {
            Logger.getLogger(BorrowRequestDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            XJDBC.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowRequestDaoImpl.class.getName())
                  .log(Level.SEVERE, "Lỗi delete BorrowRequest", ex);
        }
    }

    @Override
    public BorrowRequest selectById(int id) {
        List<BorrowRequest> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<BorrowRequest> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<BorrowRequest> selectByUserId(int userID) {
        return selectBySql(SELECT_BY_USER_SQL, userID);
    }

    @Override
    public List<BorrowRequest> selectByStatus(String status) {
        return selectBySql(SELECT_BY_STATUS_SQL, status);
    }

    private List<BorrowRequest> selectBySql(String sql, Object... args) {
        List<BorrowRequest> list = new ArrayList<>();
        try (ResultSet rs = XJDBC.query(sql, args)) {
            while (rs.next()) {
                BorrowRequest br = new BorrowRequest();
                br.setRequestID(rs.getInt("RequestID"));
                br.setUserID(rs.getInt("UserID"));
                br.setHoTen(rs.getString("HoTen"));
                br.setMSSV(rs.getString("MSSV"));
                br.setEmail(rs.getString("Email"));
                br.setPhoneNumber(rs.getString("PhoneNumber"));
                br.setMaSach(rs.getInt("MaSach"));
                br.setSoLuong(rs.getInt("SoLuong"));
                br.setRequestDate(rs.getTimestamp("RequestDate"));
                br.setStatus(rs.getString("Status"));
                br.setNote(rs.getString("Note"));
                list.add(br);
            }
        } catch (SQLException e) {
            Logger.getLogger(BorrowRequestDaoImpl.class.getName())
                  .log(Level.SEVERE, "Lỗi selectBySql BorrowRequest", e);
            throw new RuntimeException(e);
        }
        return list;
    }
}