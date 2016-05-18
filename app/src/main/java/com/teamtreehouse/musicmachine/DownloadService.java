package com.teamtreehouse.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();
    private DownloadHandler mHandler;

    @Override
    public void onCreate() {
        DownloadThread thread = new DownloadThread();
        thread.setName("DownloadThread");
        thread.start();

        while (thread.mHandler == null) {

        }
        mHandler = thread.mHandler;
        mHandler.setService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(MainActivity.EXTRA_SONG);
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        mHandler.sendMessage(message);
        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
