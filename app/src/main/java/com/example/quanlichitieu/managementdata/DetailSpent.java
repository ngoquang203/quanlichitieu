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

public class DetailSpent {
   private int IDdetailspent;
   private int IDspent;
   private String IDservicespent,Nameservice;
   private float Price;
   private String Content;
   private Date Dates;
   private Time Times;

   public DetailSpent(){};
    public DetailSpent(int IDdetailspent, int IDspent, String IDservicespent,String nameservice, float price, String content, Date dates,Time times) {
        this.IDdetailspent = IDdetailspent;
        this.IDspent = IDspent;
        this.IDservicespent = IDservicespent;
        Nameservice = nameservice;
        Price = price;
        Content = content;
        Dates = dates;
        Times = times;
    }

    public static DetailSpent getuserlist(int IDspent) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from DetailSpent where IDspent = '" + IDspent + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        DetailSpent detailSpent = new DetailSpent();
        while(rs.next()){
            detailSpent = new DetailSpent(
                    rs.getInt("IDdetailspent"),
                    rs.getInt("IDspent"),
                    rs.getString("IDservicespent"),
                    rs.getString("Nameservice"),
                    rs.getFloat("Price"),
                    rs.getString("Content"),
                    rs.getDate("Dates"),
                    rs.getTime("Times")
            );
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return detailSpent;
    }

    public void Insert(int IDspent,String IDservicespent,String Nameservice,String Price,String Content,String Date,String Times) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        // Thực thi câu lệnh SQL để kiểm tra ID
        Statement statement = connection.createStatement();

        String sqlInserDetailSpent = "insert into DetailSpent(IDspent,IDservicespent,Nameservice,Price,Content,Dates,Times) values ("+
                IDspent + ",'" + IDservicespent +"',N'" + Nameservice + "'," + Price + ",N'" + Content + "','" + Date+ "','" + Times + "')";
        Log.e("add Expence : ",sqlInserDetailSpent);
        statement.executeUpdate(sqlInserDetailSpent);

        // Đóng kết nối đến SQL Server
        connection.close();
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public int getIDdetailspent() {
        return IDdetailspent;
    }

    public void setIDdetailspent(int IDdetailspent) {
        this.IDdetailspent = IDdetailspent;
    }

    public int getIDspent() {
        return IDspent;
    }

    public void setIDspent(int IDspent) {
        this.IDspent = IDspent;
    }

    public String getIDservicespent() {
        return IDservicespent;
    }

    public void setIDservicespent(String IDservicespent) {
        this.IDservicespent = IDservicespent;
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
