package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class CollectMoney {
    private Date Dates;
    private float SumCollect;

    public CollectMoney(Date dates, float sumCollect) {
        Dates = dates;
        SumCollect = sumCollect;
    }

    public void getDataCollectMoneySQL(){
        try {
            Connection connection = SQLmanagement.connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from Users");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Dates = rs.getDate(2);
                    SumCollect = rs.getFloat(3);
                    Log.i("DATA",Dates + "-" + SumCollect);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Date getDates() {
        return Dates;
    }

    public void setDates(Date dates) {
        Dates = dates;
    }

    public float getSumCollect() {
        return SumCollect;
    }

    public void setSumCollect(float sumCollect) {
        SumCollect = sumCollect;
    }
}
