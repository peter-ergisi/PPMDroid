package com.example.pccomponentselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CaseFanHelp extends AppCompatActivity {
    private Button CFHelpToSCButton; //declares button for MyBuild button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_fan_help);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CFHelpToSCButton = findViewById(R.id.CFHelptoSCButtonLink);
        CFHelpToSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToSC();
            }
        });
    }

    private void moveToSC() //moves user from current page to mybuild when called
    {
        Intent CFHToSC = new Intent(CaseFanHelp.this, SupportCentreActivity.class);
        startActivity(CFHToSC);

    }

}
