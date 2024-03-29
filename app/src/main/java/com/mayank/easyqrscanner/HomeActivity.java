package com.mayank.easyqrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rey.material.widget.Button;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonScan;
    TextView textViewName, textViewAddress, textViewAge;
    private IntentIntegrator qrScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonScan = findViewById(R.id.buttonScan);
        textViewName = findViewById(R.id.textViewName);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewAge = findViewById(R.id.textViewAge);

        qrScan = new IntentIntegrator(this);
        buttonScan.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                FancyToast.makeText(this,"FAILED",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                    textViewName.setText(obj.getString("name"));
                    textViewAddress.setText(obj.getString("address"));
                    textViewAge.setText(obj.getString("age"));
                    FancyToast.makeText(getApplicationContext(), "SUCCESS", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                 } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}
