package com.example.quanlichitieu.managementdata;

import android.util.Log;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CollectMoney {

   private int IDUsers,IDCollect;
    private long SumCollect;

    public CollectMoney(){};
    public CollectMoney(int IDUsers, int IDCollect, long sumCollect) {
        this.IDUsers = IDUsers;
        this.IDCollect = IDCollect;
        SumCollect = sumCollect;
    }
    public static void addIdCollect(int IDUsers) throws SQLException{
        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "insert into CollectMoney(IDuser) values(" + IDUsers + ")";
        ResultSet rs = statement.executeQuery(sql);
        connection.close();
    }
    public static CollectMoney getuserlist(int IDUsers) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from CollectMoney where IDuser = '" + IDUsers +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        CollectMoney collectMoney = new CollectMoney();
        while(rs.next()){
            collectMoney = new CollectMoney(
                    rs.getInt("IDuser"),
                    rs.getInt("IDcollect"),
                    rs.getLong("SumCollect"));
        }
        // Đọc dữ liệu từ ResultSet
        connection.close();// Đóng kết nối
        return collectMoney;
    }

    public int getIDUsers() {
        return IDUsers;
    }

    public void setIDUsers(int IDUsers) {
        this.IDUsers = IDUsers;
    }

    public int getIDCollect() {
        return IDCollect;
    }

    public void setIDCollect(int IDCollect) {
        this.IDCollect = IDCollect;
    }

    public long getSumCollect() {
        return SumCollect;
    }

    public void setSumCollect(long sumCollect) {
        SumCollect = sumCollect;
    }
}
