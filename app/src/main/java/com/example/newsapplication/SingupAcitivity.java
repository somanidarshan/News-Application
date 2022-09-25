package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingupAcitivity extends AppCompatActivity {

    private EditText emailid;
    private EditText password;
    private Button singup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_acitivity);

        emailid=findViewById(R.id.singup_emailid);
        password=findViewById(R.id.singup_password);
        singup=findViewById(R.id.signupbtn);
        String email=emailid.getText().toString();
        String pass=password.getText().toString();
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(SingupAcitivity.this, LoginActiviy.class);
                    i.putExtra("email", email);
                    i.putExtra("password", pass);
                    startActivity(i);
                    finish();
                }
        });
    }
}