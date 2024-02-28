package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailColect {
    private int IDdetailcolect;
    private int IDcollect;

    private String IDservicecollect;
    private float Price;
    private String Content;
    private Date Dates;


    public DetailColect(){};
    public DetailColect(int IDDetailcolect, int IDcollect, String IDservicecollect, float price, String content, Date dates) {
        IDdetailcolect = IDDetailcolect;
        this.IDcollect = IDcollect;
        this.IDservicecollect = IDservicecollect;
        Price = price;
        Content = content;
        Dates = dates;
    }

    public static DetailColect getuserlist(int IDcollect) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from DetailColect where IDcollect = '" + IDcollect + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        DetailColect detailColect = new DetailColect();
        while(rs.next()){
            detailColect = new DetailColect(
                rs.getInt("IDdetailcolect"),
                rs.getInt("IDcollect"),
                    rs.getString("IDservicecollect"),
                rs.getFloat("Price"),
                rs.getString("Content"),
                    rs.getDate("Dates")
            );
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return detailColect;
    }

    public void Insert(int IDcollect,String IDservicecollect,String Price,String Content,String Date) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        // Thực thi câu lệnh SQL để kiểm tra ID
        Statement statement = connection.createStatement();

            String sqlInserDetailCollect = "insert into DetailColect(IDcollect,IDservicecollect,Price,Content,Dates) values ("+
                     IDcollect + ",'" + IDservicecollect +"'," + Price + ",'" + Content + "','" + Date + "')";
            Log.e("add Income : ",sqlInserDetailCollect);
            statement.executeUpdate(sqlInserDetailCollect);

        // Đóng kết nối đến SQL Server
        connection.close();
    }

    public int getIDcollect() {
        return IDcollect;
    }

    public void setIDcollect(int IDcollect) {
        this.IDcollect = IDcollect;
    }

    public String getIDservicecollect() {
        return IDservicecollect;
    }

    public void setIDservicecollect(String IDservicecollect) {
        this.IDservicecollect = IDservicecollect;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getDates() {
        return Dates;
    }

    public void setDates(Date dates) {
        Dates = dates;
    }
}
