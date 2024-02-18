package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Serviceapp {
    private String Nameservice;
    private String Explain;
    private String Images;

    public Serviceapp(String nameservice, String explain,String images) {
        Nameservice = nameservice;
        Explain = explain;
        Images = images;
    }

    public static ArrayList<Serviceapp> getuserlist() throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        ArrayList<Serviceapp> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from Serviceapp";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Serviceapp(
                    rs.getString("Nameservice").trim(),
                    rs.getString("Explain").trim(),
                    rs.getString("Images").trim())
                    );// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }
    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }
}
