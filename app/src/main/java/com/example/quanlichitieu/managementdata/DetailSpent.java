package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DetailSpent {
    private String IDspent;
    private float Price;
    private String Content;

    public DetailSpent(String IDspent, float price, String content) {
        this.IDspent = IDspent;
        Price = price;
        Content = content;
    }

    public void getDataDetailSpent(){
        try {
            Connection connection = SQLmanagement.connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from Logins");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    IDspent = rs.getString(3);
                    Price = rs.getFloat(4);
                    Content = rs.getString(5);
                    Log.i("DATA",IDspent + "-" + Price + "-" + Content);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getIDspent() {
        return IDspent;
    }

    public void setIDspent(String IDspent) {
        this.IDspent = IDspent;
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
}
