package com.example.pccomponentselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

public class SupportCentreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private ImageButton CPUHelperButton;
        private ImageButton GPUHelperButton;
        private ImageButton MoboHelperButton;
        private ImageButton RAMHelperButton;
        private ImageButton CFHelperButton;
        private ImageButton CCHelperButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_centre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CPUHelperButton = findViewById(R.id.CPUHelperButtonLink);
        CPUHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToCPU();
            }
        });

        GPUHelperButton = findViewById(R.id.GPUHelperButtonLink);
        GPUHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToGPU();
            }
        });

        MoboHelperButton = findViewById(R.id.MoboHelperButtonLink);
        MoboHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToMobo();
            }
        });

        RAMHelperButton = findViewById(R.id.RAMHelperButtonLink);
        RAMHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToRAM();
            }
        });

        CFHelperButton = findViewById(R.id.CFHelperButtonLink);
        CFHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToCF();
            }
        });

        CCHelperButton = findViewById(R.id.CCHelperButtonLink);
        CCHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToCC();
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
        getMenuInflater().inflate(R.menu.support_centre, menu);
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

        switch(MainNavid){
            case R.id.HomeNavLink:
                Intent supToMain = new Intent(SupportCentreActivity.this, MainActivity.class);
                startActivity(supToMain);
                break;
            case R.id.MyBuildNavLink:
                Intent supToMyBuild = new Intent(SupportCentreActivity.this, MyBuildActivity.class);
                startActivity(supToMyBuild);
                break;

            case R.id.SupportNavLink:
                Intent supToSup = new Intent(SupportCentreActivity.this,SupportCentreActivity.class);
                startActivity(supToSup);
                break;

            case R.id.PeripheralsNavLink:
                Intent mainToPer = new Intent(SupportCentreActivity.this,Periphals.class);
                startActivity(mainToPer);
                break;

            case R.id.CommunityHubNavLink:
                Intent mainToCH = new Intent(SupportCentreActivity.this,CommunityHub.class);
                startActivity(mainToCH);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void moveToCPU()
    {
        Intent moveToCPU = new Intent(SupportCentreActivity.this, CPUHelp.class);
        startActivity(moveToCPU);

    }

    private void moveToGPU()
    {
        Intent moveToGPU = new Intent(SupportCentreActivity.this, GPUHelp.class);
        startActivity(moveToGPU);

    }


    private void moveToMobo()
    {
        Intent moveToMobo = new Intent(SupportCentreActivity.this, MoboHelp.class);
        startActivity(moveToMobo);

    }


    private void moveToRAM()
    {
        Intent moveToRAM = new Intent(SupportCentreActivity.this, RAMHelp.class);
        startActivity(moveToRAM);


    }


    private void moveToCF()
    {
        Intent moveToCF = new Intent(SupportCentreActivity.this, CaseFanHelp.class);
        startActivity(moveToCF);


    }


    private void moveToCC()
    {
        Intent moveToCC = new Intent(SupportCentreActivity.this, CPUCoolerHelp.class);
        startActivity(moveToCC);


    }
}
