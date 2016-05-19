package com.teamtreehouse.musicmachine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by benjakuben on 5/18/16.
 */
public class NetworkConnectionReceiver extends BroadcastReceiver {

    public static final String NOTIFY_NETWORK_CHANGE = "NOTIFY_NETWORK_CHANGE";
    public static final String EXTRA_IS_CONNECTED = "EXTRA_IS_CONNECTED";

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Intent localIntent = new Intent(NOTIFY_NETWORK_CHANGE);
        localIntent.putExtra(EXTRA_IS_CONNECTED, isOnline());
        LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent);
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}








