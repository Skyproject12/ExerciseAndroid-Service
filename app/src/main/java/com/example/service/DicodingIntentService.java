package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;


public class DicodingIntentService extends IntentService {
    public static String EXTRA_DURATION= "extra_duration";
    public static final String TAG= DicodingIntentService.class.getSimpleName();
    public DicodingIntentService() {
        super("DicodingIntentServiceA");
    }

    // akan menjalankan proses asynchronus tanpa harus mengkombinasikan dengan asyntask
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            // menerima duration sleep
            int duration = intent.getIntExtra(EXTRA_DURATION, 0);
            try {
                Thread.sleep(duration);
                Log.d(TAG, "onHandleIntent: Selesai....");
            }
            catch (InterruptedException e){
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }
    }

    // intent service akan mematikan dirinya sendriri
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
