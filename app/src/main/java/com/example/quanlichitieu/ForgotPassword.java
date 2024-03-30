package com.example.quanlichitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.managementdata.Logins;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class ForgotPassword extends AppCompatActivity {
    private TextView button;
    private TextInputEditText editText;
    private FirebaseAuth mAuth;
    private static final String TAG = ForgotPassword.class.getName();
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subPhone = editText.getText().toString();
                Logins logins;
                try {
                    logins = Logins.getLogins(subPhone);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(logins.getID() == null || logins.getID().length() < 10){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
                    builder.setMessage("Không có tài khoản nào đăng kí với số tài khoản này");
                    builder.setPositiveButton("Ok", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    String strPhone = "+84" + subPhone.substring(1);
                    onClickForgotPassword(strPhone);
                }
            }
        });
        clickButtonBack();
    }

    private void clickButtonBack() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this,Login.class);
                startActivity(intent);
            }
        });
    }

    private void onClickForgotPassword(String strPhone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(ForgotPassword.this, "false", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
                                gotoEnterOTP(strPhone,verificationID);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            goToactivity(user.getPhoneNumber());
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(ForgotPassword.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToactivity(String phoneNumber) {
        Intent intent = new Intent(ForgotPassword.this,Login.class);
        intent.putExtra("phone_Number",phoneNumber);
        startActivity(intent);
    }
    private void gotoEnterOTP(String strPhone, String verificationID) {
        Intent intent = new Intent(ForgotPassword.this,EnterOTP.class);
        intent.putExtra("phone_Number",strPhone);
        intent.putExtra("verificationID",verificationID);
        startActivity(intent);
    }

    private void Init() {
        button = findViewById(R.id.forgot_button);
        editText = findViewById(R.id.forgot_phone);
        mAuth = FirebaseAuth.getInstance();
        imageButton = findViewById(R.id.forgot_back);
    }



}