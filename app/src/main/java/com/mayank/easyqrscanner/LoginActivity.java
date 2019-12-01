package com.mayank.easyqrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText username, pwd;
    String id,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);

    }


    public void Skip(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void Login(View view) {
        id = username.getText().toString();
        pass = pwd.getText().toString();
        if(Objects.equals(id, "admin") && Objects.equals(pass, "1234")){
            FancyToast.makeText(getApplicationContext(),"SUCCESS",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();

        }
        else {
            FancyToast.makeText(getApplicationContext(),"Password Wrong",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
        }
    }
}
