package com.imperialsoupgmail.tesseractexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LearnReceiptActivity extends AppCompatActivity {

    Button btnSave ;
    EditText txtTotal;
    EditText txtDate;
    static String fileName = "keys.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_receipt_view);

         txtTotal = (EditText) findViewById(R.id.txtMonth);
          txtDate = (EditText) findViewById(R.id.lblCurrency);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String oldKeys  = getData(LearnReceiptActivity.this);
                System.out.println("Old word - "+oldKeys);

                try {


                    String newKeys = txtDate.getText().toString();
                    JSONObject json = new JSONObject(oldKeys);
                    JSONArray contacts = json.getJSONArray("data");
                    JSONObject newJson = new JSONObject();
                    newJson.put("key",newKeys);
                    contacts.put(newJson);


                    saveData(LearnReceiptActivity.this,json.toString());
                    System.out.println("Json String  word - "+getData(LearnReceiptActivity.this));
                    System.out.println("New word - "+getData(LearnReceiptActivity.this));


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

    public void saveData(Context context, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);

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
