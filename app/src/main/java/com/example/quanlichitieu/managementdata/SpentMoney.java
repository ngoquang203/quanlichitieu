package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class SpentMoney {
    private Date Dates;
    private float SumSpent;

    public void getDataCollectMoneySQL(){
        try {
            Connection connection = SQLmanagement.connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from Users");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Dates = rs.getDate(2);
                    SumSpent = rs.getFloat(3);
                    Log.i("DATA",Dates + "-" + SumSpent);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public SpentMoney(Date dates, float sumSpent) {
        Dates = dates;
        SumSpent = sumSpent;
    }

    public Date getDates() {
        return Dates;
    }

    public void setDates(Date dates) {
        Dates = dates;
    }

    public float getSumSpent() {
        return SumSpent;
    }

    public void setSumSpent(float sumSpent) {
        SumSpent = sumSpent;
    }



}
