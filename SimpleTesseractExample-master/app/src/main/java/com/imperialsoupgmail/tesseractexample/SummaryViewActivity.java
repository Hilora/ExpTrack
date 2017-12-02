package com.imperialsoupgmail.tesseractexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SummaryViewActivity extends AppCompatActivity {

    Button btnSave ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText txtTotal = (EditText) findViewById(R.id.txtMonth);
        EditText txtDate = (EditText) findViewById(R.id.txtMonthTotal);
        EditText lblInvoice = (EditText) findViewById(R.id.lblInvoice);
        EditText lblCurrency = (EditText) findViewById(R.id.lblCurrency);
        lblInvoice.setEnabled(false);
        lblCurrency.setEnabled(false);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Do the saving part here

            }
        });



        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String total = bundle.getString("Total");
        String currency = bundle.getString("Currency");

        txtTotal.setText(total);
        txtDate.setText(currency);






    }

}
