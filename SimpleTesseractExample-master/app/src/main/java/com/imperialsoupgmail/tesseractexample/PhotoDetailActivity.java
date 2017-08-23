package com.imperialsoupgmail.tesseractexample;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PhotoDetailActivity extends AppCompatActivity {






    public void init() {




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
        Intent my = new Intent(PhotoDetailActivity.this, CaptureImageActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(my);
    }

    public void openGallery(View view){
        Intent myIntent = new Intent(PhotoDetailActivity.this, SpaceGalleryActivity.class);
        PhotoDetailActivity.this.startActivity(myIntent);
    }

}
