package com.yamin.session1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.yamin.session1.activities.MainActivity;
import com.yamin.session1.utils.MyNotificationManagers;

public class TimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int isTimeZoneChanged = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);
        if (isTimeZoneChanged == 0)
            MyNotificationManagers.showNotification("TIMEZONE", "changed", MainActivity.class, context);
        int isTime = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0);
        if (isTime == 0)
            MyNotificationManagers.showNotification("TIME", "changed", MainActivity.class, context);
    }
}
