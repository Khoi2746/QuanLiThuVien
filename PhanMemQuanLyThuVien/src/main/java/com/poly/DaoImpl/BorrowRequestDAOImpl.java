package com.poly.DaoImpl;

import com.fpoly.Dao.BorrowRequestDAO;
import com.fpoly.entity.BorrowRequest;
import com.fpoly.utils.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowRequestDAOImpl implements BorrowRequestDAO {

    final String INSERT_SQL = "INSERT INTO BorrowRequest "
            + "(MaSach, TenSach, TacGia, TheLoai, SoLuong, StudentName, StudentId, Email, Phone, BorrowDate) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";

    final String SELECT_ALL_SQL = "SELECT * FROM BorrowRequest";
    final String SELECT_BY_ID_SQL = "SELECT * FROM BorrowRequest WHERE ID = ?";

    @Override
    public void insert(BorrowRequest request) {
        try {
            XJDBC.update(INSERT_SQL,
                    request.getMaSach(),
                    request.getTenSach(),
                    request.getTacGia(),
                    request.getTheLoai(),
                    request.getSoLuong(),
                    request.getStudentName(),
                    request.getStudentId(),
                    request.getEmail(),
                    request.getPhone(),
                    request.getBorrowDate()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BorrowRequest> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public BorrowRequest selectById(int id) {
        List<BorrowRequest> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    protected List<BorrowRequest> selectBySql(String sql, Object... args) {
        List<BorrowRequest> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) {
                BorrowRequest entity = new BorrowRequest();
                entity.setId(rs.getInt("ID"));
                entity.setMaSach(rs.getInt("MaSach"));
                entity.setTenSach(rs.getString("TenSach"));
                entity.setTacGia(rs.getString("TacGia"));
                entity.setTheLoai(rs.getString("TheLoai"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setStudentName(rs.getString("StudentName"));
                entity.setStudentId(rs.getString("StudentId"));
                entity.setEmail(rs.getString("Email"));
                entity.setPhone(rs.getString("Phone"));
                entity.setBorrowDate(rs.getDate("BorrowDate"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // đảm bảo đóng resources bằng XJDBC.close
            XJDBC.close(rs);
        }
        return list;
    }
}
