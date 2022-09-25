package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActiviy extends AppCompatActivity {
    private EditText emailid;
    private EditText password;
    private Button singin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activiy);
        emailid=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        singin=findViewById(R.id.signinbtn);
        String email=emailid.getText().toString();
        String pass=password.getText().toString();

        String last_email=getIntent().getStringExtra("email");
        String last_pass=getIntent().getStringExtra("password");


        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!last_email.equals(email) || !last_pass.equals(pass)){
                    Toast.makeText(LoginActiviy.this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(LoginActiviy.this,MainActivity.class);
                    Toast.makeText(LoginActiviy.this    , "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}