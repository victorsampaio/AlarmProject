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


public class AlarmProgramRepeatActivity extends Activity {

    private static final String CATEGORY = "alarmProgramRepeatActivity";
    // Time for initiate the alarm in the first time
    private static final int seconds = 5;
    // Repeat every 10 seconds
    private static final int repeatTime = 10 * 1000;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.activity_alarm_program_repeat);
        TextView text = new TextView(this);
        text.setText("Scheduled Alarm for the " + seconds + " Seconds. ");
        setContentView(text);
        // 5 seconds
        programmer(seconds);

    }

    // Program Alarm
    private void programmer(int seconds) {
        // Intent for  trigger a Broadcast "AlarmReceiver"
        Intent it = new Intent("ALARM_EXECUTE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmProgramRepeatActivity.this, 0, it,0);
        // Execute Alarm after 5 seconds
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, seconds);

        //Schedule Alarm
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        long time = c.getTimeInMillis();

        //Repeat after 10 seconds
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, time, repeatTime,pendingIntent);
        Log.i(CATEGORY, "Alarm schedule for " + seconds + " seconds. Repeat at " + repeatTime);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alarm_program_repeat, menu);
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
