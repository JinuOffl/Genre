package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logoutBtn = (Button) findViewById(R.id.logout_btn);
        mAuth= FirebaseAuth.getInstance();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mAuth.signOut();
               Intent intent= new Intent(SettingsActivity.this, SplashActivity.class);
               startActivity(intent);
               finish();
               Toast.makeText(SettingsActivity.this,"Logout is Done",Toast.LENGTH_SHORT).show();
            }
        });
    }
}