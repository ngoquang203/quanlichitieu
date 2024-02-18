package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DetailColect {
    private String IDservice;
    private float Price;
    private String Content;

    public DetailColect(String IDservice, float price, String content) {
        this.IDservice = IDservice;
        Price = price;
        Content = content;
    }

    public void getDataDetailColectSQL(){
        try {
            Connection connection = SQLmanagement.connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from Logins");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    IDservice = rs.getString(3);
                    Price = rs.getFloat(4);
                    Content = rs.getString(5);
                    Log.i("DATA",IDservice + "-" + Price + "-" + Content);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getIDservice() {
        return IDservice;
    }

    public void setIDservice(String IDservice) {
        this.IDservice = IDservice;
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
