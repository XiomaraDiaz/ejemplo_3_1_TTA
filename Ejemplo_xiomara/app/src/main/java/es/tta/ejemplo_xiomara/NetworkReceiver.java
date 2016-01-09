package es.tta.ejemplo_xiomara;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ///
 */
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    //onReceive: se sobrescribe éste método, qué pasa cuando te quedas sin cx
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
    }
}
