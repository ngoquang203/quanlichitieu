package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlichitieu.managementdata.SignUps;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class SignUp extends AppCompatActivity {
    private ImageButton clickBack;
    private TextInputEditText signupName,signupEmail,signupPassword;
    private CheckBox signupCheckBox;
    private Button signupButton;
    private String Name,Email,Password;
    private Boolean check;
    private void Init(){
        clickBack = findViewById(R.id.signup_back);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupCheckBox = findViewById(R.id.signup_checkbox);
        signupButton = findViewById(R.id.signup_buttonAddUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Init();
        BackToLogin();
        setClickSignUp();
    }
    private void BackToLogin(){
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }
    private void setClickSignUp(){

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = signupName.getText().toString();
                Email = signupEmail.getText().toString();
                Password = signupPassword.getText().toString();
                check = signupCheckBox.isChecked();
                if(Name.length() >= 8 && Email.length() >= 8 && Password.length() >= 8 && check == true){
                    SignUps signUps = new SignUps(Name,Email,Password);
                    try {
                        signUps.Insert();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(SignUp.this,Login.class);
                    startActivity(intent);
                }
                else Toast.makeText(SignUp.this, "Please check again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}