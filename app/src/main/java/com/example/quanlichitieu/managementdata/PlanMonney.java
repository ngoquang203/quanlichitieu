package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class PlanMonney {
    private int ID,IDuser,IDplan;
    private long Summoney;
    private Date DateStart;
    private Date DateEnd;
    private String Contents;

    private long MoneyNow;

    public PlanMonney(){};
    public PlanMonney(int id,int IDuser, int IDplan, long summoney, Date dateStart,Date dateEnd, String contents,long moneyNow) {
        ID = id;
        this.IDuser = IDuser;
        this.IDplan = IDplan;
        Summoney = summoney;
        DateStart = dateStart;
        DateEnd = dateEnd;
        Contents = contents;
        MoneyNow = moneyNow;
    }


    public static void addIdPlan(int IDUsers) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert into PlanMonney(IDuser) values(" + IDUsers + ")";
        ResultSet rs = statement.executeQuery(sql);
        connection.close();
    }
    public static ArrayList<PlanMonney> getuserlist(int IDUsers) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from PlanMonney where IDuser = '" + IDUsers +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<PlanMonney> planMonney = new ArrayList<>();
        while(rs.next()){
            planMonney.add(new PlanMonney(
                    rs.getInt("IDplan"),
                    rs.getInt("IDuser"),
                    rs.getInt("IDplan"),
                    rs.getLong("Summoney"),
                    rs.getDate("DateStart"),
                    rs.getDate("DateEnd"),
                    rs.getString("Content"),
                    rs.getLong("MoneyNow")
            ));
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return planMonney;
    }
    public static void addPlanMoney(int idUser, int namePlanMoney, String SumMoney, String dateStart, String dateEnd, String contents) throws SQLException {
        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert into \n" +
                "PlanMonney(IDuser,NamePlanMoney,Summoney,DateStart,DateEnd,Content,MoneyNow) \n" +
                "values (" + idUser + "," + namePlanMoney + "," + SumMoney + ",'" + dateStart + "','" + dateEnd + "',N'" + contents + "',0)";
        statement.executeUpdate(sql);
        connection.close();
    }
    public static void deletePlanMoneyInTable(int IDplan) throws Exception{
        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();
        String sql = "delete from PlanMonney where IDplan = " + IDplan + ";";
        statement.executeUpdate(sql);
        connection.close();
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

    public long getSummoney() {
        return Summoney;
    }

    public void setSummoney(long summoney) {
        Summoney = summoney;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }
    public long getMoneyNow() {
        return MoneyNow;
    }

    public void setMoneyNow(long moneyNow) {
        MoneyNow = moneyNow;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
