package com.example.pccomponentselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RAMHelp extends AppCompatActivity {
    private Button RAMHelpToSCButton; //declares button for MyBuild button variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramhelp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RAMHelpToSCButton = findViewById(R.id.RAMHelptoSCButtonLink);
        RAMHelpToSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToSC();
            }
        });


    }

    private void moveToSC() //moves user from current page to mybuild when called
    {
        Intent RHToSC = new Intent(RAMHelp.this, SupportCentreActivity.class);
        startActivity(RHToSC);

    }

}
