package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStartService;
    Button buttonStartIntenService;
    Button buttonStartBoundService;
    Button buttonStopBoundtService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartService= findViewById(R.id.button_start_service);
        buttonStartService.setOnClickListener(this);
        buttonStartIntenService=findViewById(R.id.button_start_intent_service);
        buttonStartIntenService.setOnClickListener(this);
        buttonStartBoundService= findViewById(R.id.button_start_bound_service);
        buttonStartBoundService.setOnClickListener(this);
        buttonStopBoundtService= findViewById(R.id.button_stop_bound_service);
        buttonStopBoundtService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_start_service:
                Intent intent= new Intent(MainActivity.this, OriginService.class);
                // menjalankan sebuah service
                startService(intent);
                break;
            case R.id.button_start_intent_service:
                break;
            case R.id.button_start_bound_service:
                break;
            case R.id.button_stop_bound_service:
                break;
        }
    }
}
