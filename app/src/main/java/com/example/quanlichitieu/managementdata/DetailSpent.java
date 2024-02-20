package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailSpent {
   private int IDdetailspent;
   private int IDspent;
   private String IDservicespent;
   private float Price;
   private String Content;
   private Date Dates;

   public DetailSpent(){};
    public DetailSpent(int IDdetailspent, int IDspent, String IDservicespent, float price, String content, Date dates) {
        this.IDdetailspent = IDdetailspent;
        this.IDspent = IDspent;
        this.IDservicespent = IDservicespent;
        Price = price;
        Content = content;
        Dates = dates;
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
                    rs.getFloat("Price"),
                    rs.getString("Content"),
                    rs.getDate("Dates")
            );
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return detailSpent;
    }

    public void Insert(int IDspent,String IDservicespent,float Price,String Content,String Date) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        // Thực thi câu lệnh SQL để kiểm tra ID
        Statement statement = connection.createStatement();

        String sqlInserDetailSpent = "insert into DetailSpent(IDspent,IDservicespent,Price,Content,Dates) values ("+
                IDspent + ",'" + IDservicespent +"'," + Price + ",'" + Content + "','" + Date+ "')";

        statement.executeUpdate(sqlInserDetailSpent);

        // Đóng kết nối đến SQL Server
        connection.close();
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
