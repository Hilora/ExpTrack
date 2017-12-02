package com.imperialsoupgmail.tesseractexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportViewActivity extends AppCompatActivity {

    static String fileName = "Monthly_Report.json";
    String jsonString;

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

        jsonString = getData(this);



        //Get the bundle
        Bundle bundle = getIntent().getExtras();

    }

    public void saveData(Context context, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            System.out.println("File path -----" + context.getFilesDir().getPath() + "/" + fileName);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public String getData(Context context) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }

}
