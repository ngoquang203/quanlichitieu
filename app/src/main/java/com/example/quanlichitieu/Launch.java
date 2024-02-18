package com.example.quanlichitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Launch extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Boolean selectedActivity;
    private String dataEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        selectedActivity = sharedPreferences.getBoolean("login",false);
        dataEmail = sharedPreferences.getString("email","");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(selectedActivity == true){
                    Intent intent = new Intent(Launch.this,Home.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Launch.this,Login.class);
                    startActivity(intent);
                }
            }
        },3000);
    }
}