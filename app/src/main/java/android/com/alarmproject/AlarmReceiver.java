package android.com.alarmproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by VictorSampaio on 07/11/2014.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String CATEGORY = "alarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(CATEGORY, "Alarm Started");

        // It's possible initiate an Activity, Service or show a Notification to user.(here)
        Toast.makeText(context, "Alarm Started!", Toast.LENGTH_SHORT).show();
    }
}
