package com.imperialsoupgmail.tesseractexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
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


public class ReportViewActivity extends AppCompatActivity {

    static String fileName = "Monthly_Report.json";
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);
        EditText lblMonth= (EditText) findViewById(R.id.lblInvoice);
        EditText lblMonthTotal = (EditText) findViewById(R.id.lblCurrency);
        EditText txtMonth= (EditText) findViewById(R.id.txtTotal);
        EditText txtMonthTotal = (EditText) findViewById(R.id.txtCurrency);
        lblMonth.setEnabled(false);
        lblMonthTotal.setEnabled(false);

//        Setting Month
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMMM");
            Date date = new Date();
            String month = dateFormat.format(date);
            txtMonth.setText(month);
        }catch (Exception e){

        }

//        createJsonFromTemplate();

        jsonString = getData(this);

        try {
            JSONObject json = new JSONObject(jsonString);
            String month = txtMonth.getText().toString();
            Double total = 0.0;
            String strTotal = "";
            JSONArray contacts = json.getJSONArray(month);

            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                //String title = c.getString("Title");
                strTotal = c.getString("Amount");

                strTotal = strTotal.replaceAll("\\D+","");
                System.out.println("Amount - "+strTotal);

                StringBuilder builder = new StringBuilder(strTotal);
                int x = 2;
                builder.insert(builder.length() - x, ".");


                strTotal = builder+"";
                System.out.println("Builder Value - "+strTotal);

                total +=  Double.parseDouble(strTotal);
                System.out.println("-----------------Monthly Expense ---------------- "+c.getString("Amount"));
                txtMonthTotal.setText(total+"");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open(fileName);

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

    public void createJsonFromTemplate() {
        jsonString = loadJSONFromAsset();
        saveData(this,jsonString);
        System.out.println("$$$$$$$$$$$$---------File templated------------$$$$$$$$$$$$$");
    }

}
