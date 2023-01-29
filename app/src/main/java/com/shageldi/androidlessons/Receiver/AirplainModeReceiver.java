package com.shageldi.androidlessons.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplainModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){

        }
    }
}
