package com.example.quanlichitieu.apdaptermanagement;

import com.example.quanlichitieu.managementdata.DetailColect;
import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Bill_data {
    private int idDataCategory;
    private String IDservicecollect,Nameservice;
    private float Price;
    private String Content;
    private Date Dates;
    private Time Times;
    public Bill_data(){};
    public Bill_data(int idDataCategory, String IDservicecollect,String Nameservice, float price, String content, Date dates,Time times) {
        this.idDataCategory = idDataCategory;
        this.IDservicecollect = IDservicecollect;
        this.Nameservice = Nameservice;
        Price = price;
        Content = content;
        Dates = dates;
        Times = times;
    }


    public static ArrayList<Bill_data> getuserList(int IDcollect,int IDspent) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sqlcollect = "select IDcollect,DetailColect.IDservicecollect,DetailColect.Nameservice,Price,Content,Dates,Times\n" +
                "from DetailColect inner join ServiceCollect on DetailColect.IDservicecollect = ServiceCollect.IDservicecollect\n" +
                "where IDcollect = '" + IDcollect + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        String sqlspent = "select IDspent,DetailSpent.IDservicespent,DetailSpent.Nameservice,Price,Content,Dates,Times\n" +
                "from DetailSpent inner join ServiceSpent on DetailSpent.IDservicespent = ServiceSpent.IDservicespent\n" +
                "where IDspent = '" + IDspent + "'";

        ResultSet rs = statement.executeQuery(sqlcollect);
        ArrayList<Bill_data> billData = new ArrayList<Bill_data>();

        while(rs.next()){
            billData.add(new Bill_data(rs.getInt("IDcollect"),
                    rs.getString("IDservicecollect"),
                    rs.getString("Nameservice"),
                    rs.getFloat("Price"),
                    rs.getString("Content"),
                    rs.getDate("Dates"),
                    rs.getTime("Times"))

            );
        }

        rs = statement.executeQuery(sqlspent);
        while(rs.next()){
            billData.add(new Bill_data(
                    rs.getInt("IDspent"),
                    rs.getString("IDservicespent"),
                    rs.getString("Nameservice"),
                    rs.getFloat("Price"),
                    rs.getString("Content"),
                    rs.getDate("Dates"),
                    rs.getTime("Times")
            ));
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return billData;
    }


    public String getNameservice() {
        return Nameservice;
    }

    public Time getTimes() {
        return Times;
    }

    public void setTimes(Time times) {
        Times = times;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public int getIdDataCategory() {
        return idDataCategory;
    }

    public void setIdDataCategory(int idDataCategory) {
        this.idDataCategory = idDataCategory;
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
