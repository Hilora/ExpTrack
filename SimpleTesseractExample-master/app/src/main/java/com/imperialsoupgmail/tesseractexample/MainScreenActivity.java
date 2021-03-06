package com.imperialsoupgmail.tesseractexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity {

    Button btnCamera ;
    Button btnBills ;
    Button btnReport;




    public void init() {
        btnCamera = (Button)findViewById(R.id.btnCamera);
        btnBills = (Button)findViewById(R.id.btnBills);
        btnReport = (Button)findViewById(R.id.btnReport);

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCaptureImage(v);
            }
        });
        btnBills.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGallery(v);
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openReport(v);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

    }

    public void openCaptureImage(View view){
        Intent myIntent = new Intent(this, CaptureImageActivity.class);
        myIntent.putExtra("key", false); //Optional parameters
        this.startActivity(myIntent);
    }

    public void openGallery(View view){
        Intent myIntent = new Intent(this, SpaceGalleryActivity.class);
        this.startActivity(myIntent);


    }

    public void openReport(View view){
        Intent myIntent = new Intent(this, ReportViewActivity.class);
        this.startActivity(myIntent);


    }


}
