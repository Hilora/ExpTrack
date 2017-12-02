package com.imperialsoupgmail.tesseractexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);
        EditText lblMonth= (EditText) findViewById(R.id.lblMonth);
        EditText lblMonthTotal = (EditText) findViewById(R.id.lblMonthTotal);
        EditText txtMonth= (EditText) findViewById(R.id.txtMonth);
        EditText txtMonthTotal = (EditText) findViewById(R.id.txtMonthTotal);
        txtMonth.setEnabled(false);
        txtMonthTotal.setEnabled(false);

//        Setting Month
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMMM");
            Date date = new Date();
            String month = dateFormat.format(date);
            txtMonth.setText(month);
        }catch (Exception e){

        }

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

    }

    void

}
