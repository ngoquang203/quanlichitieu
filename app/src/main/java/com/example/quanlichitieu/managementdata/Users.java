package com.example.quanlichitieu.managementdata;

import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private int IDusers;
    private String Names;
    private String Sex;
    private String NumberPhone;
    private String Email;
    public Users(){};
    public Users(int idUsers,String names, String sex, String numberPhone, String email) {
        IDusers = idUsers;
        Names = names;
        Sex = sex;
        NumberPhone = numberPhone;
        Email = email;
    }

    public static Users getuserlist(String email) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from Users where ID = '" + email +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        Users users = new Users();
        while(rs.next()){
            users = new Users(
                    rs.getInt("IDuser"),
                    rs.getString("Names"),
                    rs.getString("Sex"),
                    rs.getString("NumberPhone"),
                    rs.getString("Email"));
        }
                // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return users;
    }

    public int getIDusers() {
        return IDusers;
    }

    public void setIDusers(int IDusers) {
        this.IDusers = IDusers;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
