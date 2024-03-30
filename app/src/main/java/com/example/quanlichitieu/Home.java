package com.example.quanlichitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;


import com.example.quanlichitieu.fragment.ViewPageAdapter;

import com.example.quanlichitieu.managementdata.PlanMonney;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "my_channel";
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private String email;
    private int IDuser, IDcollect, IDplan, IDspent;
    private SharedPreferences sharedPreferences;
    private ArrayList<PlanMonney> arrayList;
    private boolean index = false;

    private void init() {
        viewPager = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        IDuser = sharedPreferences.getInt("IDuser", 0);
        IDspent = sharedPreferences.getInt("IDspent", 0);
        IDplan = sharedPreferences.getInt("IDplan", 0);
        try {
            arrayList = PlanMonney.getuserlist(IDuser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setNotification();
    }

    private void setNotification() {
        for (int i = 0; i < arrayList.size(); ++i) {
            if (arrayList.get(i).getMoneyNow() >= arrayList.get(i).getSummoney()) {
                if(index == false){
                    index = true;
                }
                break;
            }
        }
        if (index == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Thông báo"; // Tên kênh thông báo
                String description = "Một kế hoạch của bạn đã hoàn thành"; // Mô tả kênh
                int importance = NotificationManager.IMPORTANCE_DEFAULT; // Mức độ quan trọng của thông báo
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Đăng ký kênh với hệ thống

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification) // Icon của thông báo
                        .setContentTitle(name) // Tiêu đề thông báo
                        .setContentText(description) // Nội dung thông báo
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); // Đặt ưu tiên cho thông báo
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(NOTIFICATION_ID,builder.build());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        even_Click();
        even_bottomNavigator();

    }

    public void even_Click(){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);
    }



    public void even_bottomNavigator(){
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_transaction).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_budget).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_home){
                    viewPager.setCurrentItem(0);
                }
                else if(item.getItemId() == R.id.menu_transaction){
                    viewPager.setCurrentItem(1);
                }
                else if(item.getItemId() == R.id.menu_budget){
                    viewPager.setCurrentItem(2);
                }
                else if(item.getItemId() == R.id.menu_profile){
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}