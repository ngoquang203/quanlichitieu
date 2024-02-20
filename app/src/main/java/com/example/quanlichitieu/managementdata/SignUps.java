package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUps {
    private String Name;
    private String Email;
    private String Password;
    public SignUps(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }
    public void Insert() throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        // Tạo câu lệnh SQL để kiểm tra xem ID đã tồn tại hay chưa
        String sqlCheckId = "SELECT COUNT(*) FROM Logins WHERE ID = '" + Email + "'";

        // Thực thi câu lệnh SQL để kiểm tra ID
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCheckId);
        int count = resultSet.next() ? resultSet.getInt(1) : 0;
        Log.e("count: ","" + count);
        // Nếu ID chưa tồn tại, hãy thêm đối tượng vào bảng
        if (count == 0) {
            // Tạo câu lệnh SQL để thêm đối tượng vào bảng
            String sqlInsert = "INSERT INTO Logins (ID, Passwords) VALUES ('" + Email + "','" + Password +"')";
            String sqlInsertUsers = "insert into Users(Names,ID) values ('" + Name+ "','" + Email + "')";
            // Thực thi câu lệnh SQL để thêm đối tượng
            statement.executeUpdate(sqlInsert);
            statement.executeUpdate(sqlInsertUsers);
            int IDUsers = Users.getuserlist(Email).getIDusers();
            String sqlInserIDcollect = "insert into CollectMoney(IDuser) values(" + IDUsers + ")";
            String sqlInsertIDspent = "insert into SpentMoney(IDuser) values(" + IDUsers + ")";
            String sqlInsertIDplan = "insert into PlanMonney(IDuser) values(" + IDUsers + ")";
            statement.executeUpdate(sqlInserIDcollect);
            statement.executeUpdate(sqlInsertIDspent);
            statement.executeUpdate(sqlInsertIDplan);
        }
        // Đóng kết nối đến SQL Server
        connection.close();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
