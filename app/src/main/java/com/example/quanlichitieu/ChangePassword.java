package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quanlichitieu.managementdata.Logins;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class ChangePassword extends AppCompatActivity {
    private TextInputEditText password,confirmPassword;
    private TextView button;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getDataIntent();
        Init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mPass = password.getText().toString();
                String mConfirm = confirmPassword.getText().toString();
                if(mPass.equals(mConfirm)){
                    try {
                        Logins.changePassword("0969950346",mPass);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(ChangePassword.this,Login.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getDataIntent() {
        String subPhonen = getIntent().getStringExtra("phone_number");
        phone = "0" + subPhonen.substring(3);

    }

    private void Init() {
        password = findViewById(R.id.change_password);
        confirmPassword = findViewById(R.id.change_passwordConfirm);
        button = findViewById(R.id.change_button);
    }
}