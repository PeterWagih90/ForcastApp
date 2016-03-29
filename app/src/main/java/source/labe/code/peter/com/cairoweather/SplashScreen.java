package source.labe.code.peter.com.cairoweather;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Timer;
        import java.util.TimerTask;

        import org.apache.http.HttpResponse;
        import org.apache.http.HttpStatus;
        import org.apache.http.StatusLine;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;

        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.SharedPreferences;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.Menu;
        import android.view.MenuItem;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;

public class SplashScreen extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initConnectivityStatus();
        Timer t = new Timer();
        TimerTask ts = new TimerTask() {

            @Override
            public void run() {
                if (GlobalVariables.isConnected) {
                    // new
                    // getMyJasonCast().execute("http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
                    try {
                        // testing not full updated
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        newFragment = new ProgressDialogFragment();
                        newFragment.show(ft, "Dialog");
                        String response = SplashScreen.this
                                .run("http://api.wunderground.com/api/838ed9367e8876bf%20/forecast/q/EG/Cairo.json");
                        // FragmentTransaction ft =
                        // getSupportFragmentManager().beginTransaction();
                        ProgressDialogFragment prev = (ProgressDialogFragment) getSupportFragmentManager()
                                .findFragmentByTag("dialog");
                        if (prev != null) {
                            ft.remove(prev);
                            ft.commit();
                        }
                        SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                        sh.edit().putString("dataoffline", response).commit();
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.putExtra("Data", response);
                        intent.putExtra("offline", false);
                        startActivity(intent);
                        SplashScreen.this.finish();
                    } catch (IOException e) {
                        SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                        if (sh.contains("dataoffline")) {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            intent.putExtra("Data", sh.getString("dataoffline", loadJSONFromAsset()));
                            intent.putExtra("offline", true);
                            startActivity(intent);
                            // Session_setup.if_from_session=true;

                            SplashScreen.this.finish();
                        } else {
                            String myJson = loadJSONFromAsset();
                            sh.edit().putString("dataoffline", myJson).commit();
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            intent.putExtra("Data", myJson);
                            intent.putExtra("offline", true);
                            startActivity(intent);
                            // Session_setup.if_from_session=true;

                            SplashScreen.this.finish();
                        }
                        SplashScreen.this.finish();
                    }
                } else {
                    SplashScreen.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                            if (sh.contains("dataoffline")) {
                                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                intent.putExtra("Data", sh.getString("dataoffline", loadJSONFromAsset()));
                                intent.putExtra("offline", true);
                                startActivity(intent);
                                // Session_setup.if_from_session=true;

                                SplashScreen.this.finish();
                            } else {
                                String myJson = loadJSONFromAsset();
                                sh.edit().putString("dataoffline", myJson).commit();
                                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                intent.putExtra("Data", myJson);
                                intent.putExtra("offline", true);
                                startActivity(intent);
                                // Session_setup.if_from_session=true;

                                SplashScreen.this.finish();
                            }
                            // TODO Auto-generated method stub
                            // Toast.makeText(getApplicationContext(), "Please
                            // check your connection and try again!",
                            // Toast.LENGTH_LONG).show();
                            // SplashScreen.this.finish();

                        }
                    });

                }

            }
        };

        t.schedule(ts, 200);

    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.splash_screen, menu);
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
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(GlobalVariables.receiver, intentFilter);
        }

    }

    public static class ProgressDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Checking Data Please Wait ...");
            dialog.setTitle(null);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            return dialog;
        }
    }

    FragmentManager fragmentManager;
    ProgressDialogFragment newFragment;

    private class getMyJasonCast extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            newFragment = new ProgressDialogFragment();
            newFragment.show(ft, "Dialog");
        }

        @Override
        protected String doInBackground(String... uri) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            try {
                HttpGet request = new HttpGet(uri[0]);
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

                }

            } catch (Exception e) {

            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if (result != null && !result.isEmpty()) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ProgressDialogFragment prev = (ProgressDialogFragment) getSupportFragmentManager()
                        .findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                    ft.commit();
                }

                SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                sh.edit().putString("dataoffline", result).commit();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("Data", result);
                intent.putExtra("offline", false);
                startActivity(intent);

                SplashScreen.this.finish();
            } else {
                SharedPreferences sh = getSharedPreferences("locale", MODE_PRIVATE);
                if (sh.contains("dataoffline")) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("Data", sh.getString("dataoffline", loadJSONFromAsset()));
                    intent.putExtra("offline", true);
                    startActivity(intent);

                    SplashScreen.this.finish();
                } else {
                    String myJson = loadJSONFromAsset();
                    sh.edit().putString("dataoffline", myJson).commit();
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("Data", myJson);
                    intent.putExtra("offline", true);
                    startActivity(intent);

                    SplashScreen.this.finish();
                }
                SplashScreen.this.finish();
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(GlobalVariables.receiver);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("default.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

