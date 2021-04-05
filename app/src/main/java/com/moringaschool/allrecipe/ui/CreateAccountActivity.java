package com.moringaschool.allrecipe.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.allrecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.createUserButton) Button mCreateUserButton;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView (R.id.emailEditText)EditText mEmailEditText;
    @BindView (R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText)EditText mConfirmPasswordEditText;
    @BindView(R.id.loginTextView)TextView mLoginTextView;

    private FirebaseAuth mAuth;

public static final String TAG =CreateAccountActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();



    }

    private void createNewUser(){
        final String name = mNameEditText.getText().toString().trim();
        final String email =mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,task ->{
                   if (task.isSuccessful()){
                       Log.d(TAG,"Authentication successful");
                   }else{
                       Toast.makeText(CreateAccountActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                   }
                });
    }

    @Override
    public void onClick(View v) {
        if(v== mLoginTextView){
            Intent intent =new Intent(CreateAccountActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(v==mCreateUserButton){
            createNewUser();
        }

    }
}