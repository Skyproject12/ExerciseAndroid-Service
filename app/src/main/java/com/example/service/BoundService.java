package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {
    final String TAG= BoundService.class.getSimpleName();
    MyBinder mBinder= new MyBinder();
    final long startTime = System.currentTimeMillis();
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        mTimer.start();
        return mBinder;
    }
    //untuk memanggil prosedur jarak jauh
    class MyBinder extends Binder {
        BoundService getService(){
            return BoundService.this;
        }
    }
    // menghitung mundur
    CountDownTimer mTimer= new CountDownTimer(100000,1000) {
        @Override
        public void onTick(long l) {
            // current time dikurang start time
            long elapsedTime= System.currentTimeMillis()- startTime;
            Log.d(TAG, "onTick"+elapsedTime);
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mTimer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG,"onRebind");
        super.onRebind(intent);
    }
}
