package com.hapyness.behappy;

import android.app.PendingIntent;
import android.app.Service;
import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by zwerltic on 5/9/15.
 */
public class UpdateService extends Service {
    private static Timer timer = new Timer();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<String> urls= new ArrayList<String>();
        urls.add("http://i.imgur.com/Ojq8wUr.jpg");
        String[] otherList = new String[] {"http://i.imgur.com/Ojq8wUr.jpg", "http://i.imgur.com/8T29gHv.jpg", "http://i.imgur.com/M48jGJn.jpg", "http://i.imgur.com/jenZrHn.jpg", "http://i.imgur.com/wcbCIc3.jpg", "http://i.imgur.com/SazaHUq.jpg", "http://i.imgur.com/YoQdcrT.jpg", "http://i.imgur.com/smqUxFE.jpg", "http://i.imgur.com/zriEI7a.jpg"};
        urls.addAll(Arrays.asList(otherList));
        timer.scheduleAtFixedRate(new RunNotif(intent, urls), 0, 3000);

        return Service.START_NOT_STICKY;
    }

    private class RunNotif extends TimerTask {
        Intent intent;
        public ArrayList<String> urls;
        public RunNotif(Intent intent, ArrayList<String> urls) {
            this.intent = intent;
            this.urls = urls;
        }
        public void run() {
            String from = intent.getStringExtra(DisplaySettings.EXTRA_MESSAGE);
            String to = intent.getStringExtra(DisplaySettings.to1);
            String interval = intent.getStringExtra(DisplaySettings.interval1);

            if(!urls.isEmpty()) {
                try {
                    Bitmap bitmap = getBitmapFromURL(urls.get(0));


                    Notification pageNotif = new NotificationCompat.Builder(getApplication())
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentText("")
                            .setContentTitle("lalal")
                            .extend(new NotificationCompat.WearableExtender()
                                    .setBackground(bitmap)
                                    .setHintShowBackgroundOnly(true))
                            .build();
                    Notification notification = new NotificationCompat.Builder(getApplication())
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setAutoCancel(true)
                            .setContentTitle("")
                            .setContentText("Smile")
                            .extend(new NotificationCompat.WearableExtender()
                                    .setCustomSizePreset(Notification.WearableExtender.SIZE_FULL_SCREEN)
                                    .addPage(pageNotif)
                                    .setBackground(bitmap))
                            .build();


                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());


                    int notificationId = 1;
                    notificationManager.notify(notificationId, notification);
                    urls.remove(0);
                } catch (
                        Exception e
                        )

                {
                    e.printStackTrace();
                }
            }

        }
    }
    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
