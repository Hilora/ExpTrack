package com.imperialsoupgmail.tesseractexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";
    int[] p ;

    int count = 0;
     // R.drawable drawableResources = new R.drawable();
     //Class<R.drawable> c = R.drawable.class;
    // Field[] fields = c.getDeclaredFields();

    ImageView imageView;

    int imageKey=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        p = new int[]{
                R.drawable.test_image0

        };

        image = BitmapFactory.decodeResource(getResources(), p[0]);

        //initialize Tesseract API
        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);
    }

    public void processImage(View view){


        String url = "drawable/"+"test_image"+count;
        imageKey = getResources().getIdentifier(url, "drawable", getPackageName());
        System.out.println("ImageKey - "+imageKey);


        imageView =(ImageView) findViewById(R.id.imageView);
        image = BitmapFactory.decodeResource(getResources(), imageKey);


        //image = resize(image,400,300);
        //image = Bitmap.createScaledBitmap(image, 300, 500, false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
          image = setGrayscale(image);
          image = removeNoise(image);

        String OCRresult = null;
        //mTess.setImage(BitmapFactory.decodeResource(getResources(),imageKey));
        mTess.setImage(image);
        OCRresult = mTess.getUTF8Text();
        TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
        OCRTextView.setText(OCRresult);
        System.out.println("Scanned Text"+OCRresult);

        if (count>0){

          //  image = resize(image,300,300);
          //  image = setGrayscale(image);
          //  image = removeNoise(image);

            imageView.setImageBitmap(image);
        }
        ++count;

    }

    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
                copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }




    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }


            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //-----------

    // Resize
    public Bitmap resize(Bitmap img, int newWidth, int newHeight) {
        Bitmap bmap = img.copy(img.getConfig(), true);

        double nWidthFactor = (double) img.getWidth() / (double) newWidth;
        double nHeightFactor = (double) img.getHeight() / (double) newHeight;

        double fx, fy, nx, ny;
        int cx, cy, fr_x, fr_y;
        int color1;
        int color2;
        int color3;
        int color4;
        byte nRed, nGreen, nBlue;

        byte bp1, bp2;

        for (int x = 0; x < bmap.getWidth(); ++x) {
            for (int y = 0; y < bmap.getHeight(); ++y) {

                fr_x = (int) Math.floor(x * nWidthFactor);
                fr_y = (int) Math.floor(y * nHeightFactor);
                cx = fr_x + 1;
                if (cx >= img.getWidth())
                    cx = fr_x;
                cy = fr_y + 1;
                if (cy >= img.getHeight())
                    cy = fr_y;
                fx = x * nWidthFactor - fr_x;
                fy = y * nHeightFactor - fr_y;
                nx = 1.0 - fx;
                ny = 1.0 - fy;

                color1 = img.getPixel(fr_x, fr_y);
                color2 = img.getPixel(cx, fr_y);
                color3 = img.getPixel(fr_x, cy);
                color4 = img.getPixel(cx, cy);

                // Blue
                bp1 = (byte) (nx * Color.blue(color1) + fx * Color.blue(color2));
                bp2 = (byte) (nx * Color.blue(color3) + fx * Color.blue(color4));
                nBlue = (byte) (ny * (double) (bp1) + fy * (double) (bp2));

                // Green
                bp1 = (byte) (nx * Color.green(color1) + fx * Color.green(color2));
                bp2 = (byte) (nx * Color.green(color3) + fx * Color.green(color4));
                nGreen = (byte) (ny * (double) (bp1) + fy * (double) (bp2));

                // Red
                bp1 = (byte) (nx * Color.red(color1) + fx * Color.red(color2));
                bp2 = (byte) (nx * Color.red(color3) + fx * Color.red(color4));
                nRed = (byte) (ny * (double) (bp1) + fy * (double) (bp2));

                bmap.setPixel(x, y, Color.argb(255, nRed, nGreen, nBlue));
            }
        }

        bmap = setGrayscale(bmap);
        bmap = removeNoise(bmap);

        return bmap;
    }

    // SetGrayscale
    private Bitmap setGrayscale(Bitmap img) {
        Bitmap bmap = img.copy(img.getConfig(), true);
        int c;
        for (int i = 0; i < bmap.getWidth(); i++) {
            for (int j = 0; j < bmap.getHeight(); j++) {
                c = bmap.getPixel(i, j);
                byte gray = (byte) (.299 * Color.red(c) + .587 * Color.green(c)
                        + .114 * Color.blue(c));

                bmap.setPixel(i, j, Color.argb(255, gray, gray, gray));
            }
        }
        return bmap;
    }

    // RemoveNoise
    private Bitmap removeNoise(Bitmap bmap) {
        for (int x = 0; x < bmap.getWidth(); x++) {
            for (int y = 0; y < bmap.getHeight(); y++) {
                int pixel = bmap.getPixel(x, y);
                if (Color.red(pixel) < 162 && Color.green(pixel) < 162 && Color.blue(pixel) < 162) {
                    bmap.setPixel(x, y, Color.BLACK);
                }
            }
        }
        for (int x = 0; x < bmap.getWidth(); x++) {
            for (int y = 0; y < bmap.getHeight(); y++) {
                int pixel = bmap.getPixel(x, y);
                if (Color.red(pixel) > 162 && Color.green(pixel) > 162 && Color.blue(pixel) > 162) {
                    bmap.setPixel(x, y, Color.WHITE);
                }
            }
        }
        return bmap;
    }
}
