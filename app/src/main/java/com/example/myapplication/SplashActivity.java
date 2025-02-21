package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_DISPLAY_LENGTH = 1000; // duration in milliseconds

        FirebaseAuth auth= FirebaseAuth.getInstance();

        /*if(auth.getCurrentUser()!= null){
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
        else{
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(mainIntent);
            finish();
        }*/

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}