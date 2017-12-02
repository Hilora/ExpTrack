package com.imperialsoupgmail.tesseractexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SummaryViewActivity extends AppCompatActivity {

    Button btnSave ;
    static String monthlyReportFilename = "Monthly_Report.json";
    String jsonString;
    String month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText txtTotal = (EditText) findViewById(R.id.txtTotal);
        final EditText txtDate = (EditText) findViewById(R.id.txtCurrency);
        EditText lblInvoice = (EditText) findViewById(R.id.lblInvoice);
        EditText lblCurrency = (EditText) findViewById(R.id.lblCurrency);
        lblInvoice.setEnabled(false);
        lblCurrency.setEnabled(false);

        ///Setting Month
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMMM");
            Date date = new Date();
            month = dateFormat.format(date);

        }catch (Exception e){

        }


        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Do the saving part here

                System.out.println("In Key saving file )))))))))))))))))))))))))))))))))))))))))))))))");
                String oldKeys  = getData(SummaryViewActivity.this);
                System.out.println("Old word - "+oldKeys);

                try {


                    String total = txtTotal.getText().toString();
                    JSONObject json = new JSONObject(oldKeys);
                    JSONArray contacts = json.getJSONArray(month);
                    JSONObject newJson = new JSONObject();
                    newJson.put("Amount",total);
                    contacts.put(newJson);


                    saveData(SummaryViewActivity.this,json.toString());
                    System.out.println("Json String  word - "+getData(SummaryViewActivity.this));
                    System.out.println("New report - "+getData(SummaryViewActivity.this));


                } catch (Exception e) {
                    e.printStackTrace();
                }
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



    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open(monthlyReportFilename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public void saveData(Context context, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + monthlyReportFilename);

            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public String getData(Context context) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + monthlyReportFilename);
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

    public void createJsonFromTemplate() {
        jsonString = loadJSONFromAsset();
        saveData(this,jsonString);
        System.out.println("$$$$$$$$$$$$---------File templated------------$$$$$$$$$$$$$");
    }


}
