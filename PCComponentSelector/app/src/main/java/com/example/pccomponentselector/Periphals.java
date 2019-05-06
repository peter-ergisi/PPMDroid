package com.example.pccomponentselector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;//allows code to be done for Spinner by importing android widget spinner library
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.net.URI;

public class Periphals extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button SaveB;
    private Button LoadB;
    private Button BuyB;

    Spinner Mouse;
    Spinner Keyboard;
    Spinner Monitor;
    Spinner Headphones;
    Spinner Microphone;
    Spinner Speakers;

    ArrayList<String> MouseNames;
    ArrayList<String> KeyboardNames;
    ArrayList<String> MonitorNames;
    ArrayList<String> HeadphoneNames;
    ArrayList<String> MicrophoneNames;
    ArrayList<String> SpeakersNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periphals);

        MouseNames=new ArrayList<>();
        KeyboardNames=new ArrayList<>();
        MonitorNames=new ArrayList<>();
        HeadphoneNames=new ArrayList<>();
        MicrophoneNames=new ArrayList<>();
        SpeakersNames=new ArrayList<>();

        Mouse=(Spinner)findViewById(R.id.MouseSpinner);
        Keyboard=(Spinner)findViewById(R.id.KeyboardSpinner);
        Monitor=(Spinner)findViewById(R.id.MonitorSpinner);
        Headphones=(Spinner)findViewById(R.id.HeadphonesSpinner);
        Microphone=(Spinner)findViewById(R.id.MicrophoneSpinner);
        Speakers=(Spinner)findViewById(R.id.SpeakerSpinner);

        loadSpinnerDataMouse("http://178.62.33.12:3000/MOUSE");
        loadSpinnerDataKeyboard("http://178.62.33.12:3000/KEYBOARD");
        loadSpinnerDataMonitor("http://178.62.33.12:3000/MONITOR");
        loadSpinnerDataHeadphones("http://178.62.33.12:3000/HEADPHONE");
        loadSpinnerDataMicrophone("http://178.62.33.12:3000/MICROPHONE");
        loadSpinnerDataSpeakers("http://178.62.33.12:3000/SPEAKER");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SaveB = findViewById(R.id.saveButton3);
        SaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                saveCode();
            }
        });

        LoadB = findViewById(R.id.loadButton2);
        LoadB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                loadCode();
            }
        });

        BuyB = findViewById(R.id.BuyButton);
        BuyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                int userChoice0 = Mouse.getSelectedItemPosition();
                String userChoice1 = Mouse.getSelectedItem().toString();
                if(userChoice0 != -1 && userChoice0 != 0) {
                    userChoice1.replaceAll("\\s","+");
                    String url = "http://www.amazon.com/s?url=search-alias%3Daps&field-keywords=" + userChoice1;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.periphals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int MainNavid = item.getItemId();

        switch (MainNavid) {
            case R.id.HomeNavLink:
                Intent supToMain = new Intent(Periphals.this, MainActivity.class);
                startActivity(supToMain);
                break;
            case R.id.MyBuildNavLink:
                Intent supToMyBuild = new Intent(Periphals.this, MyBuildActivity.class);
                startActivity(supToMyBuild);
                break;

            case R.id.SupportNavLink:
                Intent supToSup = new Intent(Periphals.this, SupportCentreActivity.class);
                startActivity(supToSup);
                break;

            case R.id.PeripheralsNavLink:
                Intent mainToPer = new Intent(Periphals.this, Periphals.class);
                startActivity(mainToPer);
                break;

            case R.id.CommunityHubNavLink:
                Intent mainToCH = new Intent(Periphals.this,CommunityHub.class);
                startActivity(mainToCH);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadSpinnerDataMouse(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            MouseNames.add(country);
                        }
                    }
                    Mouse.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, MouseNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void loadSpinnerDataKeyboard(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            KeyboardNames.add(country);
                        }
                    }
                    Keyboard.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, KeyboardNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void loadSpinnerDataMonitor(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            MonitorNames.add(country);
                        }
                    }
                    Monitor.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, MonitorNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void loadSpinnerDataHeadphones(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            HeadphoneNames.add(country);
                        }
                    }
                    Headphones.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, HeadphoneNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void loadSpinnerDataMicrophone(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            MicrophoneNames.add(country);
                        }
                    }
                    Microphone.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, MicrophoneNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void loadSpinnerDataSpeakers(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    if(true){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Name");
                            SpeakersNames.add(country);
                        }
                    }
                    Speakers.setAdapter(new ArrayAdapter<String>(Periphals.this, android.R.layout.simple_spinner_item, SpeakersNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void saveCode() {
        int userChoice1 = Mouse.getSelectedItemPosition();
        int userChoice2 = Keyboard.getSelectedItemPosition();
        int userChoice3 = Monitor.getSelectedItemPosition();
        int userChoice4 = Headphones.getSelectedItemPosition();
        int userChoice5 = Microphone.getSelectedItemPosition();
        int userChoice6 = Speakers.getSelectedItemPosition();

        SharedPreferences sharedPref = getSharedPreferences("UserData",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("Mouse",userChoice1);
        prefEditor.putInt("Keyboard",userChoice2);
        prefEditor.putInt("Monitor",userChoice3);
        prefEditor.putInt("Headphones",userChoice4);
        prefEditor.putInt("Microphone",userChoice5);
        prefEditor.putInt("Speakers",userChoice6);
        prefEditor.commit();
    }

    private void loadCode() {
        SharedPreferences sharedPref = getSharedPreferences("UserData",MODE_PRIVATE);
        int spinnerValue1 = sharedPref.getInt("Mouse",-1);
        int spinnerValue2 = sharedPref.getInt("Keyboard",-1);
        int spinnerValue3 = sharedPref.getInt("Monitor",-1);
        int spinnerValue4 = sharedPref.getInt("Headphones",-1);
        int spinnerValue5 = sharedPref.getInt("Microphone",-1);
        int spinnerValue6 = sharedPref.getInt("Speakers",-1);

        if(spinnerValue1 != -1) {
            // set the selected value of the spinner
            Mouse.setSelection(spinnerValue1);
        }
        if(spinnerValue2 != -1) {
            // set the selected value of the spinner
            Keyboard.setSelection(spinnerValue2);
        }
        if(spinnerValue3 != -1) {
            // set the selected value of the spinner
            Monitor.setSelection(spinnerValue3);
        }
        if(spinnerValue4 != -1) {
            // set the selected value of the spinner
            Headphones.setSelection(spinnerValue4);
        }
        if(spinnerValue5 != -1) {
            // set the selected value of the spinner
            Microphone.setSelection(spinnerValue5);
        }
        if(spinnerValue6 != -1) {
            // set the selected value of the spinner
            Speakers.setSelection(spinnerValue6);
        }
    }
}

