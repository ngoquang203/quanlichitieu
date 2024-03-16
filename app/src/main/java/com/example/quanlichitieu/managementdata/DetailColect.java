package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class DetailColect {
    private int IDdetailcolect;
    private int IDcollect;

    private String IDservicecollect,Nameservice;
    private long Price;
    private String Content;
    private Date Dates;
    private Time Times;
    private long MoneyNow;


    public DetailColect(){};
    public DetailColect(int IDDetailcolect, int IDcollect, String IDservicecollect,String nameservice, long price, String content, Date dates,Time times,long moneynow) {
        IDdetailcolect = IDDetailcolect;
        this.IDcollect = IDcollect;
        this.IDservicecollect = IDservicecollect;
        Nameservice = nameservice;
        Price = price;
        Content = content;
        Dates = dates;
        Times = times;
        MoneyNow = moneynow;
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
                rs.getString("Nameservice"),
                rs.getLong("Price"),
                rs.getString("Content"),
                    rs.getDate("Dates"),
                    rs.getTime("Times"),
                    rs.getLong("MoneyNow")
            );
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return detailColect;
    }

    public void Insert(int IDcollect,String IDservicecollect,String Nameservice,String Price,String Content,String Date,String Times,String MoneyNow) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        // Thực thi câu lệnh SQL để kiểm tra ID
        Statement statement = connection.createStatement();

            String sqlInserDetailCollect = "insert into DetailColect(IDcollect,IDservicecollect,Nameservice,Price,Content,Dates,Times,MoneyNow) values (" +
                    IDcollect + ",'" + IDservicecollect + "',N'"+ Nameservice + "'," + Price + ",N'" + Content + "','" + Date + "','" + Times + "'," + MoneyNow + ")\n";
            Log.e("add Income : ",sqlInserDetailCollect);

        statement.executeUpdate(sqlInserDetailCollect);
        // Đóng kết nối đến SQL Server
        connection.close();
    }

    public long getMoneyNow() {
        return MoneyNow;
    }

    public void setMoneyNow(long moneyNow) {
        MoneyNow = moneyNow;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
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

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
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
