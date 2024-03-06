package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceCollect {
    private String IDservicecollect,Nameservice,Explain;

    public ServiceCollect(String IDservicecollect, String nameservice, String explain) {
        this.IDservicecollect = IDservicecollect;
        Nameservice = nameservice;
        Explain = explain;
    }

    public static ArrayList<ServiceCollect> getuserlist() throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        ArrayList<ServiceCollect> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from ServiceCollect";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new ServiceCollect(
                    rs.getString("IDservicecollect").trim(),
                    rs.getString("Nameservice").trim(),
                    rs.getString("Explain").trim())
            );// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }

    public String getIDservicecollect() {
        return IDservicecollect;
    }

    public void setIDservicecollect(String IDservicecollect) {
        this.IDservicecollect = IDservicecollect;
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


}
