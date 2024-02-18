package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class PlanMonney {
    private float Summoney;
    private Date Dates;
    private String Content;

    public PlanMonney(float summoney, Date dates, String content) {
        Summoney = summoney;
        Dates = dates;
        Content = content;
    }

    public void getDataPlanMonney(){
        try {
            Connection connection = SQLmanagement.connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from Logins");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Summoney = rs.getFloat(3);
                    Dates = rs.getDate(4);
                    Content = rs.getString(5);
                    Log.i("DATA",Summoney + "-" + Dates + "-" + Content);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public float getSummoney() {
        return Summoney;
    }

    public void setSummoney(float summoney) {
        Summoney = summoney;
    }

    public Date getDates() {
        return Dates;
    }

    public void setDates(Date dates) {
        Dates = dates;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
