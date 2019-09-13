package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStartService;
    Button buttonStartIntenService;
    Button buttonStartBoundService;
    Button buttonStopBoundtService;
    boolean mServiceBound= false;
    BoundService mBoundService;
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

    // listener connection to service bound
    private ServiceConnection serviceConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder myBinder= (BoundService.MyBinder) iBinder;
            mBoundService= myBinder.getService();
            mServiceBound= true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceBound= false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_start_service:
                Intent intent= new Intent(MainActivity.this, OriginService.class);
                // menjalankan sebuah service
                startService(intent);
                break;
            case R.id.button_start_intent_service:
                Intent intent1= new Intent(MainActivity.this,DicodingIntentService.class);
                intent1.putExtra(DicodingIntentService.EXTRA_DURATION, 5000);
                startService(intent1);
                break;
            case R.id.button_start_bound_service:
                Intent intentBound= new Intent(MainActivity.this, BoundService.class);
                // berfungsi mengikat suatu class bountService ke activity main
                // bind auto create digunakan untuk menjalankan component dari aplikasi
                bindService(intentBound, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.button_stop_bound_service:
                unbindService(serviceConnection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // mendestroy suatu system ketika unbindService service connection
        if(mServiceBound){
            unbindService(serviceConnection);
        }
    }
}
