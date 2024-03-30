package com.example.quanlichitieu;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlichitieu.fragment.MainFragment;
import com.example.quanlichitieu.managementdata.CollectMoney;
import com.example.quanlichitieu.managementdata.Logins;
import com.example.quanlichitieu.managementdata.PlanMonney;
import com.example.quanlichitieu.managementdata.SpentMoney;
import com.example.quanlichitieu.managementdata.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLException;
import java.util.List;

public class Login extends AppCompatActivity {
    private TextInputEditText emailText,passwordText;
    private Button clickLogin,clickForgot,clickSignUp;
    private String email,password;
    private SharedPreferences sharedPreferences;
    public void Init(){
        emailText = findViewById(R.id.loginName);
        passwordText = findViewById(R.id.loginPassword);
        clickLogin = findViewById(R.id.loginButton);
        clickForgot = findViewById(R.id.loginForgot);
        clickSignUp = findViewById(R.id.loginSignUp);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Init();
        clickLoginListen();
        clickSignUp();
        clickForgotButton();
    }

    private void clickForgotButton() {
        clickForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });
    }


    public void setTextEmail(){
        email = emailText.getText().toString();
    }
    public void setTextPassword(){
        password = passwordText.getText().toString();
    }
    public void clickLoginListen(){
        clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextEmail();
                setTextPassword();
                try {
                    boolean count = false;
                    List<Logins> logins = Logins.getuserlist();
                    for(int i = 0;i<logins.size();++i){
                        Log.e("ID : ",logins.get(i).getID() + "-"+logins.get(i).getPasswords());
                        if(email.equals(logins.get(i).getID()) && password.equals(logins.get(i).getPasswords())){
                            Log.e("TRUE","yes");
                            count = true;
                            break;
                        }
                    }
                    if(count){
                        int IDuser,IDcollect,IDspent,IDplan;
                        long SumCollect,SumSpent;

                        IDuser = Users.getuserlist(email).getIDusers();
                        CollectMoney collectMoney = CollectMoney.getuserlist(IDuser);
                        SpentMoney spentMoney = SpentMoney.getuserlist(IDuser);
                        IDcollect = collectMoney.getIDCollect();
                        SumCollect = collectMoney.getSumCollect();
                        IDspent = spentMoney.getIDspent();
                        SumSpent = spentMoney.getSumSpent();
                        sharedPreferences.edit().putBoolean("login",true).apply();
                        sharedPreferences.edit().putString("email",email).apply();
                        sharedPreferences.edit().putInt("IDuser",IDuser).apply();
                        sharedPreferences.edit().putInt("IDcollect",IDcollect).apply();
                        sharedPreferences.edit().putInt("IDspent",IDspent).apply();
                        sharedPreferences.edit().putLong("SumCollect",SumCollect).apply();
                        sharedPreferences.edit().putLong("SumSpent",SumSpent).apply();

                        Intent intent = new Intent(Login.this,Home.class);
                        intent.putExtra("key_email",email);
                        startActivity(intent);
                    }
                    else Toast.makeText(Login.this, "Login false", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    private void clickSignUp(){
        clickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}