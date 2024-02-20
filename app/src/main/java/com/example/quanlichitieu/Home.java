package com.example.quanlichitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.quanlichitieu.fragment.MainFragment;
import com.example.quanlichitieu.fragment.ViewPageAdapter;
import com.example.quanlichitieu.managementdata.CollectMoney;
import com.example.quanlichitieu.managementdata.Users;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;

public class Home extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private String email;
    private int IDuser,IDcollect,IDplan,IDspent;
    private SharedPreferences sharedPreferences;
    private void init(){
        viewPager = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        IDuser = sharedPreferences.getInt("IDuser",0);

        IDspent = sharedPreferences.getInt("IDspent",0);
        IDplan = sharedPreferences.getInt("IDplan",0);

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
                        bottomNavigationView.getMenu().findItem(R.id.menu_add).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_budget).setChecked(true);
                        break;
                    case 4:
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
                else if(item.getItemId() == R.id.menu_add){
                    viewPager.setCurrentItem(2);
                }
                else if(item.getItemId() == R.id.menu_budget){
                    viewPager.setCurrentItem(3);
                }
                else if(item.getItemId() == R.id.menu_profile){
                    viewPager.setCurrentItem(4);
                }
                return true;
            }
        });
    }
}