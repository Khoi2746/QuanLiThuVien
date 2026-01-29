package com.poly.dao;

import java.sql.SQLException;
import java.util.List;

public interface OverdueDAO {

    List<Object[]> getOverdueList(String keyword) throws SQLException;

    void insertNotification(int borrowID, int userID) throws SQLException;

}
