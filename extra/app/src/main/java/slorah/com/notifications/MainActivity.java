package slorah.com.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String CHANNEL_ID = "ANU";
    public static final int NOTIFICATION_ID = 123;

    //based on https://developer.android.com/guide/topics/ui/notifiers/notifications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create channel as you start the app
        createNotificationChannel();

        //clicking this button will fire the simple notification
        Button myButton = findViewById(R.id.simple_notifiaction);
        myButton.setOnClickListener(this);

        //clicking this button will fire the notification that start an activity with backstack
        Button myOtherButton = findViewById(R.id.start_activity);
        myOtherButton.setOnClickListener(this);

        //clicking this button will fire custom designed notification
        Button myThirdButton = findViewById(R.id.custom_notification);
        myThirdButton.setOnClickListener(this);
    }//end onCreate()


    public void onClick(View view) {

        if (view.getId() == R.id.simple_notifiaction) {
            //set intent to show mainactivity when notifiation tapped
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            //set pending intent on the intent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            //build the actual notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.nut)
                    .setContentTitle(getString(R.string.simple_notification_title))
                    .setContentText(getString(R.string.simple_notification_text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            //self defined id, each notification requires a unique id
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
        if (view.getId() == R.id.start_activity) {

            //creates intent for the activity that will start
            Intent resultIntent = new Intent(this, Notified.class);
            //creates TaskStackBuilder and adds the intent, which inflates the back stack
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            //gets the PendingIntent containing the entire back stack
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent notifyIntent = new Intent(this, Prenotified.class);
            //sets the activity to start in a new, empty task
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //creates the PendingIntent
            PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                    this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );


            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.nut)
                    .setContentTitle(getString(R.string.start_activity_notification_title))
                    .setContentText(getString(R.string.start_activity_notification_text))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(resultPendingIntent) //sets intent that will fire when the user taps the notification
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());

        }
        if (view.getId() == R.id.custom_notification) {

            synchronized (this){
                try{
                    wait(10000);
                    //sets intent to show mainactivity when notifiation tapped
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    //sets pending intent on the intent
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                    //gets the layouts to use in the custom notification
                    RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification_small);
                    RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.custom_notification_large);

                    //applies the layouts to the notification
                    Notification customNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.mipmap.nut)
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                            .setCustomContentView(notificationLayout)
                            .setCustomBigContentView(notificationLayoutExpanded)
                            .setTimeoutAfter(10000) //this makes the notification time out
                            .build();
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                    notificationManager.notify(NOTIFICATION_ID, customNotification);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }//end onClick()

    private void createNotificationChannel() {
        //creates the NotificationChannel, but only on API 26+ because
        //the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.enableVibration(true);

            //registers channel with the system
            //the importance other notification behaviors can't be changed after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }//end createNotificationChannel()
}