package com.example.quanlichitieu.apdaptermanagement;

import com.example.quanlichitieu.sqlmanagement.SQLmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Table_data {
    private Date dates;
    private String IDservice;
    private String Nameservice;
    private long Price;

    public Table_data(Date dates,String idservice, String nameservice, long price) {
        this.dates = dates;
        IDservice = idservice;
        Nameservice = nameservice;
        Price = price;
    }
    public static ArrayList<Table_data> getuserListCollect(int IDcollect,String dateStart,String dateEnd) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sqlcollect = "select Dates,IDservicecollect,Nameservice,Price from DetailColect where IDcollect = " + IDcollect + " and Dates >= '" + dateStart +  "' and Dates <= '" + dateEnd + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet

        ResultSet rs = statement.executeQuery(sqlcollect);
        ArrayList<Table_data> tableData = new ArrayList<Table_data>();

        while (rs.next()) {
            tableData.add(new Table_data(rs.getDate("Dates"),
                    rs.getString("IDservicecollect"),
                    rs.getString("Nameservice"),
                    rs.getLong("Price"))

            );
        }
        return tableData;
    }
    public static ArrayList<Table_data> getuserListSpent(int IDspent,String dateStart,String dateEnd) throws SQLException {

        Connection connection = SQLmanagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sqlcollect = "select Dates,IDservicespent,Nameservice,Price from DetailSpent where IDspent = " + IDspent + " and Dates >= '" + dateStart +  "' and Dates <= '" + dateEnd + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet


        ResultSet rs = statement.executeQuery(sqlcollect);
        ArrayList<Table_data> tableData = new ArrayList<Table_data>();

        while (rs.next()) {
            tableData.add(new Table_data(rs.getDate("Dates"),
                    rs.getString("IDservicespent"),
                    rs.getString("Nameservice"),
                    rs.getLong("Price"))

            );
        }
        return tableData;
    }

    public String getIDservice() {
        return IDservice;
    }

    public void setIDservice(String IDservice) {
        this.IDservice = IDservice;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }
}
