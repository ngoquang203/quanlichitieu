package com.example.quanlichitieu.sqlmanagement;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLmanagement {
    private static String sql = "jdbc:jtds:sqlserver://192.168.0.219:1433;databasename=QLCT;user=sinhvienFBU;password=ngoquanghy123"; // dia chi ket noi
    public static Connection connectionSQLSever(){
        Connection connection = null;
        try {
            try {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build(); // lay tat ca cac quyen
                StrictMode.setThreadPolicy(policy); // thiet lap chinh sac ket noi bao gom tat ca cac quyen
                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(sql);
                Log.i("THONG BAO:","Ket noi thanh cong");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void getData(){
        try {
            String invoid = "",nameinvoice ="";
            Connection connection = connectionSQLSever();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement("select * from DataInvoice");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    invoid = rs.getString(1);
                    nameinvoice = rs.getString(2);
                    Log.i("DATA",invoid + "-" + nameinvoice);
                }
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
