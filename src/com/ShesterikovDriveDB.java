package com;

import java.sql.*;

public class ShesterikovDriveDB {
    final String DB_URL="jdbc:mysql://localhost/shesterikovdrivedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    final String USER = "root";
    final String PASS = "1111";
    private Connection conn;


    public ShesterikovDriveDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(String username, String password, String email){
        String sql = "insert into users(username, password, email) values ('" + username + "','" + password + "','" + email + "')";
        try {
            Statement stmt;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean loginUser(String username, String password) {
        boolean result = false;
        String sql = "select password from users where username='" + username + "'";
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            if (password.equals(rs.getString("password"))) {
                result = true;
            };
            stmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkUsername(String username) {
        boolean result = false;
        String sql = "select 1 from users where username='" + username + "'";
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            if (((Integer)1).equals(rs.getInt(1))) {
                result = true;
            };
            stmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
