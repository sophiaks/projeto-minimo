package br.edu.insper.al.sophiaks.projeto_minimo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashSet;
import java.util.Set;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    Set<String> listTitle = new HashSet<String>();
    Set<String> listBody = new HashSet<String>();
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d("MyFirebaseMessagingService", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("TAG", "Message data payload: " + remoteMessage.getData());
        }

// Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d("Message", "Message Notification title: " + remoteMessage.getNotification().getTitle());
            saveNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }
    private void saveNotification(String title, String body) {
        SharedPreferences sharedPreferences = getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        // Your notification serialization logic here…
        SharedPreferences.Editor editor = sharedPreferences.edit();
        listTitle.add(title);
        listBody.add(body);
//        editor.putString("title", title);
        editor.putStringSet("listTitle", listTitle);
        editor.putStringSet("listBody",listBody);
        editor.apply();
        Log.d("Message", "Message Notification title set: " + sharedPreferences.getStringSet("listTitle",null));

    }
}
