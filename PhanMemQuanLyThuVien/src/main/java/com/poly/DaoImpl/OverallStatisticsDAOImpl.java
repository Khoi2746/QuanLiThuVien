package com.poly.DaoImpl;


import com.poly.dao.OverallStatisticsDAO;
import com.fpoly.entity.OverallStatistics;
import com.fpoly.utils.XJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverallStatisticsDAOImpl implements OverallStatisticsDAO {

    @Override
    public OverallStatistics getOverallStatistics() {
        int tongSachDauVao = 0;
        int sachDaMuonChuaTra = 0;
        int sachHienCon = 0;

        String sqlTongSachDauVao = "SELECT SUM(Quantity) FROM Books"; // Đã sửa lỗi Book
        try (Connection con = XJDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlTongSachDauVao);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                tongSachDauVao = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // LƯU Ý: Ku em cần thay thế Borrowings và ReturnDate bằng tên chính xác trong DB
        String sqlSachDaMuonChuaTra = "SELECT COUNT(*) FROM Borrow WHERE ReturnDate IS NULL"; 
        
        try (Connection con = XJDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlSachDaMuonChuaTra);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                sachDaMuonChuaTra = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sachHienCon = tongSachDauVao - sachDaMuonChuaTra;
        
        return new OverallStatistics(tongSachDauVao, sachDaMuonChuaTra, sachHienCon);
    }
}