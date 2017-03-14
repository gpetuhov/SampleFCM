package com.gpetuhov.android.samplefcm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpetuhov.android.samplefcm.events.MessageReceivedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    private static final String LOG_TAG = MainFragment.class.getName();

    // Displays message text on screen
    @BindView(R.id.message_text) TextView mMessageTextView;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    @Override
    public void onStart() {
        super.onStart();

        // Register to listen to EventBus events
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Unregister from EventBus
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();
    }

    // Called when a message is received (in the UI thread).
    // During registration all sticky subscriber methods will immediately get the previously posted sticky event.
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {

        Log.d(LOG_TAG, "Displaying message in TextView");

        // Get message text from the event and display in TextView
        mMessageTextView.setText(messageReceivedEvent.getText());

        // Remove (consume) sticky event so that it won’t be delivered anymore
        EventBus.getDefault().removeStickyEvent(messageReceivedEvent);
    }
}
