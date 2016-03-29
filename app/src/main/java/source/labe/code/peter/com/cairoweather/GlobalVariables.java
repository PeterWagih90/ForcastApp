package source.labe.code.peter.com.cairoweather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Peter on 3/29/2016.
 */
public class GlobalVariables {
    public static final boolean DEBUG = false;
    public static boolean isConnected=false;
    /**
     * this is a broadcast receiver that will be registered for receiving the network status broadcast from the OS.
     * and will set the <strong>isConnected</strong> flag to according value.
     */
    public static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni=cm.getActiveNetworkInfo();
            if(ni!=null && ni.isConnected()){
                isConnected=true;
            }else{
                isConnected=false;
            }
        }
    };
}
