package com.gpetuhov.android.samplefcm.fcm;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.gpetuhov.android.samplefcm.events.MessageReceivedEvent;

import org.greenrobot.eventbus.EventBus;

// Receive FCM messages
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String LOG_TAG = MyFirebaseMessagingService.class.getName();

    // Called when message is received.
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // This method is called only if the message is received,
        // when the app is in the FOREGROUND.
        // When the app is in the background, a standard notification
        // is displayed in the system tray and onMessageReceived is not called.
        // If the message, received when the app is in the background,
        // contains data payload, it can be retrieved
        // from the intent launched when the user taps on the notification.

        // In this sample we will handle messages only when the app is in the foreground.

        // Get text from received message
        String messageText = remoteMessage.getNotification().getBody();

        Log.d(LOG_TAG, "Received message: " + messageText);

        // Post message text to EventBus as STICKY event.
        // This is needed, because at this moment main fragment may not be started,
        // and can't receive events.
        // It will be able to get sticky event from EventBus after start.
        EventBus.getDefault().postSticky(new MessageReceivedEvent(messageText));
    }
}
