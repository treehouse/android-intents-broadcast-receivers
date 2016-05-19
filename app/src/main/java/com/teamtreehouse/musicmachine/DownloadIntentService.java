package com.teamtreehouse.musicmachine;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.teamtreehouse.musicmachine.models.Song;

public class DownloadIntentService extends IntentService {
    private static final String TAG = DownloadIntentService.class.getSimpleName();

    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 22;

    public DownloadIntentService() {
        super("DownloadIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Song song = intent.getParcelableExtra(MainActivity.EXTRA_SONG);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_queue_music_white)
                .setContentTitle("Downloading")
                .setContentText(song.getTitle())
                .setProgress(0, 0, true);

        mNotificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

        downloadSong(song.getTitle());
    }

    private void downloadSong(String song) {
        long endTime = System.currentTimeMillis() + 2*1000;
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, song + " downloaded!");

        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
