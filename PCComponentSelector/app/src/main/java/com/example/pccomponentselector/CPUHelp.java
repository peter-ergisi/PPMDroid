package com.example.pccomponentselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CPUHelp extends AppCompatActivity {

    private Button CPUHelpToSCButton; //declares button for MyBuild button variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuhelp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CPUHelpToSCButton = findViewById(R.id.CPUHelptoSCButtonLink);
        CPUHelpToSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToSC();
            }
        });
    }




    private void moveToSC() //moves user from current page to mybuild when called
    {
        Intent CHToSC = new Intent(CPUHelp.this, SupportCentreActivity.class);
        startActivity(CHToSC);

    }
}
