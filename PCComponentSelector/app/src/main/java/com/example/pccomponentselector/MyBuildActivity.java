package com.example.pccomponentselector;

import android.content.Intent;
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

import android.content.SharedPreferences;


public class MyBuildActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button SaveB;
    private Button LoadB;
    private Button BuyB;

    Spinner CPU;
    Spinner CPUCOOL;
    Spinner GPU;
    Spinner MOBO;
    Spinner RAM;
    Spinner CASE;
    Spinner CASECOOL;

    ArrayList<String> CPUNames;
    ArrayList<String> CPUCOOLNames;
    ArrayList<String> GPUNames;
    ArrayList<String> MOBONames;
    ArrayList<String> RAMNames;
    ArrayList<String> CASENames;
    ArrayList<String> CASECOOLNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_build);

        CPUNames=new ArrayList<>();
        CPUCOOLNames=new ArrayList<>();
        GPUNames=new ArrayList<>();
        MOBONames=new ArrayList<>();
        RAMNames=new ArrayList<>();
        CASENames=new ArrayList<>();
        CASECOOLNames=new ArrayList<>();

        CPU=(Spinner)findViewById(R.id.CPUSpinner);
        CPUCOOL=(Spinner)findViewById(R.id.CPUCoolerSpinner);
        GPU=(Spinner)findViewById(R.id.GPUSpinner);
        MOBO=(Spinner)findViewById(R.id.MotherboardSpinner);
        RAM=(Spinner)findViewById(R.id.RAMSpinner);
        CASE=(Spinner)findViewById(R.id.CaseSpinner);
        CASECOOL=(Spinner)findViewById(R.id.CaseFanSpinner);

        loadSpinnerDataCPU("http://178.62.33.12:3000/CPU");
        loadSpinnerDataCPUCOOL("http://178.62.33.12:3000/CPUCOOL");
        loadSpinnerDataGPU("http://178.62.33.12:3000/GPU");
        loadSpinnerDataMOBO("http://178.62.33.12:3000/MOBO");
        loadSpinnerDataRAM("http://178.62.33.12:3000/RAM");
        loadSpinnerDataCASE("http://178.62.33.12:3000/CASE");
        loadSpinnerDataCASECOOL("http://178.62.33.12:3000/CASECOOL");

        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SaveB = findViewById(R.id.saveButton);
        SaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                saveCode();
            }
        });

        LoadB = findViewById(R.id.LoadButton);
        LoadB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                loadCode();
            }
        });

        BuyB = findViewById(R.id.BuildButton);
        BuyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                int userChoice0 = CPU.getSelectedItemPosition();
                String userChoice1 = CPU.getSelectedItem().toString();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_build, menu);
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
        int MyBuildid = item.getItemId();
        switch(MyBuildid) {
            case R.id.HomeNavLink:
                Intent myBuildToMain = new Intent(MyBuildActivity.this, MainActivity.class);
                startActivity(myBuildToMain);
                break;
            case R.id.MyBuildNavLink:
                Intent myBuildToMyBuild = new Intent(MyBuildActivity.this, MyBuildActivity.class);
                startActivity(myBuildToMyBuild);
                break;
            case R.id.SupportNavLink:
                Intent myBuildToSup = new Intent( MyBuildActivity.this,SupportCentreActivity.class);
                startActivity(myBuildToSup);
                break;
            case R.id.PeripheralsNavLink:
                Intent mainToPer = new Intent(MyBuildActivity.this,Periphals.class);
                startActivity(mainToPer);
                break;
            case R.id.CommunityHubNavLink:
                Intent mainToCH = new Intent(MyBuildActivity.this,CommunityHub.class);
                startActivity(mainToCH);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadSpinnerDataCPU(String url) {
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
                            CPUNames.add(country);
                        }
                    }
                    CPU.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, CPUNames));
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

    private void loadSpinnerDataCPUCOOL(String url) {
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
                            CPUCOOLNames.add(country);
                        }
                    }
                    CPUCOOL.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, CPUCOOLNames));
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

    private void loadSpinnerDataGPU(String url) {
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
                            GPUNames.add(country);
                        }
                    }
                    GPU.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, GPUNames));
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

    private void loadSpinnerDataMOBO(String url) {
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
                            MOBONames.add(country);
                        }
                    }
                    MOBO.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, MOBONames));
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

    private void loadSpinnerDataRAM(String url) {
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
                            RAMNames.add(country);
                        }
                    }
                    RAM.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, RAMNames));
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

    private void loadSpinnerDataCASE(String url) {
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
                            CASENames.add(country);
                        }
                    }
                    CASE.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, CASENames));
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

    private void loadSpinnerDataCASECOOL(String url) {
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
                            CASECOOLNames.add(country);
                        }
                    }
                    CASECOOL.setAdapter(new ArrayAdapter<String>(MyBuildActivity.this, android.R.layout.simple_spinner_item, CASECOOLNames));
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
        int userChoice1 = CPU.getSelectedItemPosition();
        int userChoice2 = CPUCOOL.getSelectedItemPosition();
        int userChoice3 = GPU.getSelectedItemPosition();
        int userChoice4 = MOBO.getSelectedItemPosition();
        int userChoice5 = RAM.getSelectedItemPosition();
        int userChoice6 = CASE.getSelectedItemPosition();
        int userChoice7 = CASECOOL.getSelectedItemPosition();

        SharedPreferences sharedPref = getSharedPreferences("UserData",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("CPU",userChoice1);
        prefEditor.putInt("CPUCOOL",userChoice2);
        prefEditor.putInt("GPU",userChoice3);
        prefEditor.putInt("MOBO",userChoice4);
        prefEditor.putInt("RAM",userChoice5);
        prefEditor.putInt("CASE",userChoice6);
        prefEditor.putInt("CASECOOL",userChoice7);
        prefEditor.commit();
    }

    private void loadCode() {
        SharedPreferences sharedPref = getSharedPreferences("UserData",MODE_PRIVATE);
        int spinnerValue1 = sharedPref.getInt("CPU",-1);
        int spinnerValue2 = sharedPref.getInt("CPUCOOL",-1);
        int spinnerValue3 = sharedPref.getInt("GPU",-1);
        int spinnerValue4 = sharedPref.getInt("MOBO",-1);
        int spinnerValue5 = sharedPref.getInt("RAM",-1);
        int spinnerValue6 = sharedPref.getInt("CASE",-1);
        int spinnerValue7 = sharedPref.getInt("CASECOOL",-1);
        if(spinnerValue1 != -1) {
            // set the selected value of the spinner
            CPU.setSelection(spinnerValue1);
        }
        if(spinnerValue2 != -1) {
            // set the selected value of the spinner
            CPUCOOL.setSelection(spinnerValue2);
        }
        if(spinnerValue3 != -1) {
            // set the selected value of the spinner
            GPU.setSelection(spinnerValue3);
        }
        if(spinnerValue4 != -1) {
            // set the selected value of the spinner
            MOBO.setSelection(spinnerValue4);
        }
        if(spinnerValue5 != -1) {
            // set the selected value of the spinner
            RAM.setSelection(spinnerValue5);
        }
        if(spinnerValue6 != -1) {
            // set the selected value of the spinner
            CASE.setSelection(spinnerValue6);
        }
        if(spinnerValue7 != -1) {
            // set the selected value of the spinner
            CASECOOL.setSelection(spinnerValue7);
        }
    }

}