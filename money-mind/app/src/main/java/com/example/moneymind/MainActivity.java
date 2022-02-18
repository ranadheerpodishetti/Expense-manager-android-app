package com.example.moneymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText mEmail,mpassword;
    Button mLogin;
    TextView msignup;
    ProgressBar progressBar;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.emailID);
        mpassword = findViewById(R.id.password);
        mLogin =  findViewById(R.id.login);
        msignup = findViewById(R.id.toSignup);
        progressBar = findViewById(R.id.progressBar2);


        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,signUp.class));
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                String email = mEmail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                Log.i("MainActivity", email+" "+password);

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("password is Required");
                    return;
                }

                if(password.length() < 6){
                    mpassword.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login is Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });


    }
}


