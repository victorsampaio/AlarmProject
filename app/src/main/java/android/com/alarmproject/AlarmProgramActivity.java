package android.com.alarmproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;


public class AlarmProgramActivity extends Activity {

    private static final String CATEGORY = "alarmProgram";
    private static final int seconds = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_program);

        TextView text = new TextView(this);
        text.setText("Scheduled Alarm for the " + seconds + " Seconds.");
        setContentView(text);

        programmer(seconds);
    }

    // The Alarm Scheduler to run from here to the 'x' seconds
    private void programmer(int seconds) {
        // Intent for Run the Broadcast
        Intent it = new Intent("START_ALARM");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmProgramActivity.this, 0,it, 0);

        // For execute the Alarm after 'x' seconds starting now.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);

        // Schedule Alarm
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        long time = calendar.getTimeInMillis();
        alarm.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        Log.i(CATEGORY, "Scheduled Alarm for the " + seconds + " Seconds.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(CATEGORY, "onDestroy() - Alarm Canceled");
        Intent intent = new Intent("START_ALARM");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmProgramActivity.this, 0, intent, 0);
        // Cancel the Alarm
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alarm_program, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
