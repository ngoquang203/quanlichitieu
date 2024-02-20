package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class PlanMonney {
    private int IDuser,IDplan;
    private float Summoney;
    private Date Dates;
    private String Contents;

    public PlanMonney(){};
    public PlanMonney(int IDuser, int IDplan, float summoney, Date dates, String contents) {
        this.IDuser = IDuser;
        this.IDplan = IDplan;
        Summoney = summoney;
        Dates = dates;
        Contents = contents;
    }


    public static void addIdPlan(int IDUsers) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert into PlanMonney(IDuser) values(" + IDUsers + ")";
        ResultSet rs = statement.executeQuery(sql);
        connection.close();
    }
    public static PlanMonney getuserlist(int IDUsers) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from PlanMonney where IDuser = '" + IDUsers +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        PlanMonney planMonney = new PlanMonney();
        while(rs.next()){
            planMonney = new PlanMonney(
                    rs.getInt("IDuser"),
                    rs.getInt("IDplan"),
                    rs.getFloat("Summoney"),
                    rs.getDate("Dates"),
                    rs.getString("Content")
            );
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return planMonney;
    }

    public int getIDuser() {
        return IDuser;
    }

    public void setIDuser(int IDuser) {
        this.IDuser = IDuser;
    }

    public int getIDplan() {
        return IDplan;
    }

    public void setIDplan(int IDplan) {
        this.IDplan = IDplan;
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

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }
}
