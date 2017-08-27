package com.imperialsoupgmail.tesseractexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class SpacePhotoActivity extends AppCompatActivity {

    public static Bitmap bitmap_img ;
    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";


    private ImageView mImageView;
    public static Button btn_img_process;
    ImageProccessing imageProccessing;
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

         imageProccessing = new ImageProccessing();
        textView = (TextView) findViewById(R.id.textView3);

        mImageView = (ImageView) findViewById(R.id.image);
        btn_img_process = (Button)findViewById(R.id.btn_img_process);

        btn_img_process.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCaptureImage(v);
            }
        });

        SpacePhoto spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);


        Glide.with(this)
                .load(spacePhoto.getUrl())
                .asBitmap()
                .error(R.drawable.ic_cloud_off_red)
                .listener(new RequestListener<String, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        onPalette(Palette.from(resource).generate());
                        bitmap_img = resource;
                        mImageView.setImageBitmap(resource);

                        return false;
                    }

                    public void onPalette(Palette palette) {
                        if (null != palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
                        }
                    }


                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);

    }



    public void openCaptureImage(View view){

         String value = imageProccessing.processImage(view,bitmap_img,textView);




        Intent my = new Intent(this, SummaryViewActivity.class);
        my.putExtra("Total", value); //Optional parameters
        startActivity(my);
    }

   /* private SimpleTarget target = new SimpleTarget<Bitmap>() {

        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

           onPalette(Palette.from(bitmap).generate());
           mImageView.setImageBitmap(bitmap);
        }

        public void onPalette(Palette palette) {
            if (null != palette) {
                ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
            }
        }
    };*/
}
