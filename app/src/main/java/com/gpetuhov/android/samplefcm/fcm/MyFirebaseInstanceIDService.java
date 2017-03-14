package com.gpetuhov.android.samplefcm.fcm;

import com.google.firebase.iid.FirebaseInstanceIdService;


// Handle the creation, rotation, and updating of registration tokens
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    // Called when token is updated
    @Override
    public void onTokenRefresh() {
        // To get updated token instance
        // call FirebaseInstanceId.getInstance().getToken()

        // But in this sample we do nothing.
    }
}
