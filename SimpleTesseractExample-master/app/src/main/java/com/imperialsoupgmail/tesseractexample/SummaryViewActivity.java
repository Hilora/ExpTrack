package com.imperialsoupgmail.tesseractexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SummaryViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText txtTotal = (EditText) findViewById(R.id.txtTotal);
        EditText txtDate = (EditText) findViewById(R.id.txtDate);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String total = bundle.getString("Total");
        String currency = bundle.getString("Currency");

        txtTotal.setText(total);
        txtDate.setText(currency);

    }

}
