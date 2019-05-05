package com.example.pccomponentselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CPUCoolerHelp extends AppCompatActivity {
    private Button CCHelpToSCButton; //declares button for MyBuild button variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpucoolerhelp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CCHelpToSCButton = findViewById(R.id.CCHelptoSCButtonLink);
        CCHelpToSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View MBB){
                moveToSC();
            }
        });
    }

    private void moveToSC() //moves user from current page to mybuild when called
    {
        Intent CCHToSC = new Intent(CPUCoolerHelp.this, SupportCentreActivity.class);
        startActivity(CCHToSC);

    }
}
