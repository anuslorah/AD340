package slorah.com.notifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class DelayedMessageService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 123;
    private static final String CHANNEL_ID = "ANU";

    //add constructor
    public DelayedMessageService(){
        super("DelayedMessageService");
    }

    //IntentService requires overriding onHandleIntent method
    @Override
    protected void onHandleIntent(Intent intent){
        synchronized (this){
            try{
                //add delay for showing notification
                wait(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showNotification(text);
    }

    private void showNotification(final String text){

        //build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.simple_notification_title))
                .setContentText(text)
                .setVibrate(new long[] {0, 1000})
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //create intent for showing notification
        Intent myintent = new Intent(this, MainActivity.class);
        //use intent to create pending intent
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, myintent, PendingIntent.FLAG_UPDATE_CURRENT);
        //add pending intent to the notification
        builder.setContentIntent(actionPendingIntent);
        //issue the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //display notification using notification manager
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


}
