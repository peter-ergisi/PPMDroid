package com.example.pccomponentselector;

import android.content.Intent;//allows code to be done for buttons by importing android content intent library
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
import android.widget.Button;//allows code to be done for buttons by importing android widget button library
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        //Declarations of button variables for main Activity
        private Button MyBuildButton; //declares button for MyBuild button variable
        private Button SupportCentreButton;
        //Declarations of Navigation Bar variables for main Activity
        private NavigationView MyBuildNavigation; //declares Navigation Bar button for MyBuild button variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Definitions of button variables for main Activity
        MyBuildButton = findViewById(R.id.MyBuildButtonLink);
        MyBuildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToMyBuild();
            }
        });

        SupportCentreButton =  findViewById(R.id.SupportCentreButtonLink);
        SupportCentreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToSupportCentre();
            }
        });

        //Definitions of Navigation Bar button variables for main Activity
        MyBuildNavigation = (NavigationView) findViewById(R.id.MyBuildNavLink);

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
        getMenuInflater().inflate(R.menu.main, menu);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch(MainNavid){
            case R.id.HomeNavLink:
                Intent mainToMain = new Intent(MainActivity.this, MainActivity.class);
                startActivity(mainToMain);
                break;
            case R.id.MyBuildNavLink:
                Intent mainToMyBuild = new Intent(MainActivity.this, MyBuildActivity.class);
                startActivity(mainToMyBuild);
                break;
            case R.id.SupportNavLink:
                Intent mainToSup = new Intent(MainActivity.this,SupportCentreActivity.class);
                startActivity(mainToSup);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void moveToMyBuild() //moves user from current page to mybuild when called
    {
        Intent mainToMyBuild = new Intent(MainActivity.this, MyBuildActivity.class);
        startActivity(mainToMyBuild);
        //put Mainactivity if function doesn't work
    }

    private void moveToSupportCentre() //moves user from current page to mybuild when called
    {
        Intent mainToSupportCentre = new Intent(MainActivity.this, SupportCentreActivity.class);
        startActivity(mainToSupportCentre);
        //put Mainactivity if function doesn't work
    }


}
