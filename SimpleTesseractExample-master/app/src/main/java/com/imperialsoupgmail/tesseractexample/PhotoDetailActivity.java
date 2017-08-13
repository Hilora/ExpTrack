package com.imperialsoupgmail.tesseractexample;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PhotoDetailActivity extends AppCompatActivity {

    Button btn_img_process;




    public void init() {
        btn_img_process = (Button)findViewById(R.id.btn_img_process);

        btn_img_process.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCaptureImage(v);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

    }

    public void openCaptureImage(View view){
        Intent myIntent = new Intent(this, CaptureImageActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    public void openGallery(View view){
        Intent myIntent = new Intent(this, SpaceGalleryActivity.class);
        this.startActivity(myIntent);
    }

}
