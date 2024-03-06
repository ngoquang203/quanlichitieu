package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.managementdata.CollectMoney;
import com.example.quanlichitieu.managementdata.PlanMonney;
import com.example.quanlichitieu.managementdata.SignUps;
import com.example.quanlichitieu.managementdata.SpentMoney;
import com.example.quanlichitieu.managementdata.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLException;

public class SignUp extends AppCompatActivity {
    private ImageButton clickBack;
    private TextInputEditText signupName,signupEmail,signupPassword,signupPasswordConfirm;
    private TextInputLayout textInputLayoutName,textInputLayoutID,textInputLayoutPassword,textInputLayoutConfirm;
    private CheckBox signupCheckBox;
    private Button signupButton;
    private String Name,Email,Password,PasswordConfirm;
    private Boolean check;
    private void Init(){
        clickBack = findViewById(R.id.signup_back);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupCheckBox = findViewById(R.id.signup_checkbox);
        signupButton = findViewById(R.id.signup_buttonAddUser);
        signupPasswordConfirm = findViewById(R.id.signup_passwordConfirm);
        textInputLayoutName= findViewById(R.id.signup_textInputLayoutName);
        textInputLayoutID= findViewById(R.id.signup_textInputLayoutID);
        textInputLayoutPassword= findViewById(R.id.signup_textInputLayoutPassword);
        textInputLayoutConfirm= findViewById(R.id.signup_textInputLayoutConfirm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Init();
        BackToLogin();
        setClickSignUp();
        setErronTextInput();
    }

    private void setErronTextInput() {
        signupName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 5) textInputLayoutName.setHelperTextEnabled(false);
                else{
                    textInputLayoutName.setHelperTextEnabled(true);
                    textInputLayoutName.setHelperText("Tên của bạn cần lớn hơn 6 kí tự");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signupEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 5) textInputLayoutID.setHelperTextEnabled(false);
                else{
                    textInputLayoutID.setHelperTextEnabled(true);
                    textInputLayoutID.setHelperText("Bạn cần điền ID lớn hơn 6 kí tự");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signupPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 5) textInputLayoutPassword.setHelperTextEnabled(false);
                else{
                    textInputLayoutPassword.setHelperTextEnabled(true);
                    textInputLayoutPassword.setHelperText("Bạn cần điền mật khẩu lớn hơn 6 kí tự");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signupPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(signupPassword.getText().toString())){
                    textInputLayoutConfirm.setHelperTextEnabled(false);
                }
                else {
                    textInputLayoutConfirm.setHelperTextEnabled(true);
                    textInputLayoutConfirm.setHelperText("Xác nhận mật khẩu không giống với mật khẩu");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                PasswordConfirm = signupPasswordConfirm.getText().toString();


                check = signupCheckBox.isChecked();

                if(Name.length() >= 6 && Email.length() >= 8 && Password.length() >= 6 && check == true && Password.equals(PasswordConfirm)){
                    SignUps signUps = new SignUps(Name,Email,Password);
                    try {
                        signUps.Insert();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    getDialog();
                }
            }
        });
    }

    private void getDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_notification);
        TextView title = dialog.findViewById(R.id.dialog_notification_Tilte);
        TextView messenge = dialog.findViewById(R.id.layout_notification_messenge);
        ImageButton button = dialog.findViewById(R.id.layout_notification_button);
        title.setText("Thông báo");
        messenge.setText("Đăng kí tài khoản thành công");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
        dialog.show();

    }
}