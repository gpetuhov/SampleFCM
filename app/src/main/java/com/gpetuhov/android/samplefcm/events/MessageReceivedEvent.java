package com.gpetuhov.android.samplefcm.events;

// Deliver message text from Service to the main fragment
public class MessageReceivedEvent {

    private String mText;

    public MessageReceivedEvent(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }
}
