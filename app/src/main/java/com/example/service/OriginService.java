package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.lang.ref.WeakReference;

public class OriginService extends Service implements DummyAsyncCallback {
    static final String TAG= OriginService.class.getSimpleName();
    public OriginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // membuat origin service dijalankan
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("OriginService","Origin Service dijalankan");
        DummAsync dummAsync= new DummAsync(this);
        dummAsync.execute();
        // berfungsi ketika suatuu service dimatikan oleh sistem kareena kekurangan memori maka service akan berjalan kembali setelah memori mencukupi
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void preAsync() {
        Log.d(TAG, "preAsync: Selesai.... :");
    }

    @Override
    public void postAsync() {
        Log.d(TAG, "postAsync: Selesai.... ");
        // menghasilkan callback  berupa fungsi memberhentikan OriginService
        stopSelf();
    }
    private static class DummAsync extends AsyncTask<Void, Void, Void>{
        // penghubung antara service dan asyntask
        WeakReference<DummyAsyncCallback> callback;

        public DummAsync(DummyAsyncCallback callback) {
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.get().preAsync();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // membuat system sleep selama 3 detik
                Thread.sleep(3000);
            } catch (Exception e) {
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.get().postAsync();
        }
    }
}
