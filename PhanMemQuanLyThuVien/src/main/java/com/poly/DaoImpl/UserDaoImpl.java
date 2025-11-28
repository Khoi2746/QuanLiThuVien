package com.poly.DaoImpl;

import com.fpoly.dao.UserDao;
import com.fpoly.entity.User;
import com.fpoly.utils.XJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String INSERT_SQL =
            "INSERT INTO Users (Username, Password, FullName, Email, RoleID) VALUES (?, ?, ?, ?, ?)";

    private final String UPDATE_SQL =
            "UPDATE Users SET Username=?, Password=?, FullName=?, Email=?, RoleID=? WHERE UserID=?";

    private final String DELETE_SQL =
            "DELETE FROM Users WHERE UserID=?";

    private final String SELECT_ALL_SQL =
            "SELECT U.UserID, U.Username, U.Password, U.FullName, U.Email, U.RoleID, R.RoleName " +
            "FROM Users U JOIN UserRoles R ON U.RoleID = R.RoleID";

    private final String SELECT_BY_ID_SQL =
            "SELECT U.UserID, U.Username, U.Password, U.FullName, U.Email, U.RoleID, R.RoleName " +
            "FROM Users U JOIN UserRoles R ON U.RoleID = R.RoleID WHERE U.UserID=?";

    private final String SELECT_BY_USERNAME_SQL =
            "SELECT U.UserID, U.Username, U.Password, U.FullName, U.Email, U.RoleID, R.RoleName " +
            "FROM Users U JOIN UserRoles R ON U.RoleID = R.RoleID WHERE U.Username LIKE ?";

    // Đọc dữ liệu từ ResultSet
    private User readFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("UserID"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getInt("RoleID"),
                rs.getString("RoleName")
        );
    }

    @Override
    public void create(User entity) {
        try (Connection conn = XJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getFullName());
            stmt.setString(4, entity.getEmail());
            stmt.setInt(5, entity.getRoleID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Thêm User thất bại: " + e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try (Connection conn = XJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getFullName());
            stmt.setString(4, entity.getEmail());
            stmt.setInt(5, entity.getRoleID());
            stmt.setInt(6, entity.getUserID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cập nhật User thất bại: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = XJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Xóa User thất bại: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public User findById(int id) {
        List<User> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<User> findByUsername(String keyword) {
        return selectBySql(SELECT_BY_USERNAME_SQL, "%" + keyword + "%");
    }

    private List<User> selectBySql(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        try (Connection conn = XJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
public boolean checkUsernameExists(String username) {
    String sql = "SELECT Username FROM Users WHERE Username = ?";
    try (Connection conn = XJDBC.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        return rs.next(); // nếu có dữ liệu → tồn tại

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


   @Override
public boolean updatePassword(String username, String newPass) {
    String sql = "UPDATE Users SET Password = ? WHERE Username = ?";

    try (Connection conn = XJDBC.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, newPass);
        ps.setString(2, username);

        return ps.executeUpdate() > 0; // trả về true nếu cập nhật thành công

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
