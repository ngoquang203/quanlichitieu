package com.example.quanlichitieu;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class EnterOTP extends AppCompatActivity {
    private TextInputEditText editTextOTP;
    private TextView buttonOTP;
    private TextView sendAgain;
    private String mverificationID,strPhone;
    private FirebaseAuth mAuth;
    private static final String TAG = EnterOTP.class.getName();
    private PhoneAuthProvider.ForceResendingToken mfResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        getDataIntent();
        Init();
        buttonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOTP = editTextOTP.getText().toString();
                onClickSendOTP(strOTP);
            }
        });
        sendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickOTPAgain();
            }
        });
    }
    private void onclickOTPAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        .setForceResendingToken(mfResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(EnterOTP.this, "false", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
                                mverificationID = verificationID;
                                mfResendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void getDataIntent() {
        strPhone = getIntent().getStringExtra("phone_Number");
        mverificationID = getIntent().getStringExtra("verificationID");
    }

    private void onClickSendOTP(String strOTP) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationID, strOTP);
        signInWithPhoneAuthCredential(credential);

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
                                Toast.makeText(EnterOTP.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void goToactivity(String phoneNumber) {
        Intent intent = new Intent(EnterOTP.this,ChangePassword.class);
        intent.putExtra("phone_number",phoneNumber);
        startActivity(intent);
    }


    private void Init() {
        editTextOTP = findViewById(R.id.enterOTP_edittext);
        buttonOTP = findViewById(R.id.enterOTP_button);
        sendAgain = findViewById(R.id.enterOTP_sendAgain);
        mAuth = FirebaseAuth.getInstance();
    }
}