package source.labe.code.peter.com.cairoweather;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements HorizontalListView.ScrollChangedLitener{

    private DetailedForcastAdapter fristadapter;
    private FullDaysForcastAdapter secondAdapter;
    private TextView lastchecked;
    private SimpleDateFormat dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HorizontalListView listview = (HorizontalListView) findViewById(R.id.horizontalScrollView1);
        HorizontalListView listview2 = (HorizontalListView) findViewById(R.id.horizontalScrollView2);
        lastchecked = (TextView) findViewById(R.id.lastChecked);
        dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a",Locale.getDefault());
        if (getIntent().hasExtra("Data")) {
            ForcastModel fM = new ForcastModel();
            fM.prepareData(getIntent().getStringExtra("Data"));
            fristadapter = new DetailedForcastAdapter(MainActivity.this, fM.getAllForcastDay());
            listview.setAdapter(fristadapter);
            fristadapter.notifyDataSetChanged();
            ForcastModel.PeriodSimpleForcast theDyaWeCheck = fM.getSimpleForCastList().allPeriods.get(0);

            String fullDate = theDyaWeCheck.getDay()+ "-"+theDyaWeCheck.getMonth()+"-"+theDyaWeCheck.getYear()+" "+fM.getTime().replace("EET", "");
            //Date lastTime = Calendar.getInstance().getTime();

            lastchecked.setText("Last Checked : " + fullDate);
            secondAdapter = new FullDaysForcastAdapter(MainActivity.this, fM.getSimpleForCastList().getAllPeriods());
            listview2.setAdapter(secondAdapter);
            secondAdapter.notifyDataSetChanged();

            initConnectivityStatus();
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(GlobalVariables.receiver, intentFilter);
            if(	MainActivity.this != null){
                Timer t1 = new Timer();
                TimerTask ts = new TimerTask() {

                    @Override
                    public void run() {
                        initConnectivityStatus();
                        //if (GlobalVariables.isConnected) {

                        new getMyJasonCast().execute(
                                "http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
                        //}

                    }
                };
                t1.schedule(ts, 180000);
            }
        } else {
            initConnectivityStatus();
            if (GlobalVariables.isConnected) {
                new getMyJasonCast()
                        .execute("http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
            }else{

            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * Initial <strong>isConnected</strong> flag and register our receiver for
     * connectivity change event of OS.
     */
    private void initConnectivityStatus() {
        if (GlobalVariables.DEBUG) {
            GlobalVariables.isConnected = true;
        } else {
            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isConnected()) {
                GlobalVariables.isConnected = true;
            }

        }

    }

    private class getMyJasonCast extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... uri) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            try {
                HttpGet request = new HttpGet(uri[0]);
                // String s = userEmail + ":" + userPassword;
                // request.addHeader("Authorization", "Basic "
                // + Base64.encodeToString(s.getBytes(), Base64.NO_WRAP)
                // .toString());
                response = httpclient.execute(request);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();

                    out.close();

                } else {
                    // Closes the connection.
                    response.getEntity().getContent().close();
                    // throw new IOException(statusLine.getReasonPhrase());
                }

            } catch (Exception e) {
                //// Log.v("ProfileStatusListException2", e.toString());
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && !result.isEmpty()) {
                SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                sh.edit().putString("dataoffline", result).commit();// last update
                if (MainActivity.this != null) {
                    HorizontalListView listview = (HorizontalListView) findViewById(R.id.horizontalScrollView1);
                    HorizontalListView listview2 = (HorizontalListView) findViewById(R.id.horizontalScrollView2);
                    if (listview != null && listview2 != null) {
                        ForcastModel fM = new ForcastModel();
                        fM.prepareData(result);
                        //ArrayList<ForecastDay> all = fM.getAllForcastDay();
                        fristadapter = new DetailedForcastAdapter(MainActivity.this, fM.getAllForcastDay());
                        listview.setAdapter(fristadapter);
                        fristadapter.notifyDataSetChanged();
                        secondAdapter = new FullDaysForcastAdapter(MainActivity.this,
                                fM.getSimpleForCastList().getAllPeriods());
                        listview2.setAdapter(secondAdapter);
                        secondAdapter.notifyDataSetChanged();
                        lastchecked = (TextView) findViewById(R.id.lastChecked);
                        ForcastModel.PeriodSimpleForcast theDyaWeCheck = fM.getSimpleForCastList().allPeriods.get(0);
                        String fullDate = theDyaWeCheck.getDay()+ "-"+theDyaWeCheck.getMonth()+"-"+theDyaWeCheck.getYear()+" "+fM.getTime().replace("EET", "");
                        //Date lastTime = Calendar.getInstance().getTime();

                        lastchecked.setText("Last Checked : " + fullDate);

                        Timer t1 = new Timer();
                        TimerTask ts = new TimerTask() {

                            @Override
                            public void run() {
                                initConnectivityStatus();
                                //	if (GlobalVariables.isConnected) {

                                new getMyJasonCast().execute(
                                        "http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
                                //		}

                            }
                        };
                        t1.schedule(ts, 180000);
                    }
                }
            } else {
                if(	MainActivity.this != null){
                    Timer t1 = new Timer();
                    TimerTask ts = new TimerTask() {

                        @Override
                        public void run() {
                            initConnectivityStatus();
//						if (GlobalVariables.isConnected) {

                            new getMyJasonCast().execute(
                                    "http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
//						}

                        }
                    };
                    t1.schedule(ts, 180000);
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(GlobalVariables.receiver);
    }

    @Override
    public void onScrollChangedListner(HorizontalListView object) {


    }

}
