package com.imperialsoupgmail.tesseractexample;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CaptureImageActivity extends AppCompatActivity {

    ImageView result;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private TessBaseAPI mTess;
    String datapath = "";
    Bitmap imageBitmap ;
    Button btnGallery ;
    Button btnProcess ;
    int count = 0;

    private static final String TAG = "LDSS";
    private EditText mNameField;
    private TextView mSearchResult;
    private List<String> mNames;



    public void init() {
        btnProcess = (Button)findViewById(R.id.btnProcess);
        btnProcess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                processImage(v);
            }
        });

        btnGallery = (Button)findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadImage();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button click = (Button)findViewById(R.id.button4);
        result = (ImageView)findViewById(R.id.imageView2);
        btnProcess = (Button)findViewById(R.id.btnProcess);

        init();
        //initialize Tesseract API
        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);

    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            imageBitmap = (Bitmap) extras.get("data");


            //--
            try{
                //testing code
                result.setImageBitmap(imageBitmap);
                //result.setImageBitmap(rotateImage(imageBitmap, 90));
            }catch(Exception e){

            }

            imageBitmap = Bitmap.createScaledBitmap(imageBitmap,(int)(imageBitmap.getWidth()*0.2), (int)(imageBitmap.getHeight()*0.2), true);

            result.setImageBitmap(imageBitmap);



        }
    }


    public void loadImage() {

        //--
        try{
            //testing code
            String url = "drawable/"+"test_image"+count;
            ++count;
            int imageKey = getResources().getIdentifier(url, "drawable", getPackageName());

            imageBitmap = BitmapFactory.decodeResource(getResources(), imageKey);

            //result.setImageBitmap(imageBitmap);
            //result.setImageBitmap(rotateImage(imageBitmap, 90));
        }catch(Exception e){

        }

        result.setImageBitmap(imageBitmap);

    }
    public String extractTotal(String value){

        String firstToken ;
        String secondToken = null;

        try{
            String[] tokens = value.split(" ");
            if(value.contains("TOTAL DUE")){

                tokens = value.split("TOTAL DUE");

            }else if(value.contains("TOTAL")){

                tokens = value.split("TOTAL");

            }else if(value.contains("TOTAL:LKR")){

                tokens = value.split("TOTAL:LKR");

            }else if(value.contains("Total")){

                tokens = value.split("Total");

            }else if(value.contains("SUB IOIAL")){

                tokens = value.split("SUB IOIAL");

            }else if(value.contains("SUB TUTAL")){

                tokens = value.split("SUB TUTAL");

            }else if(value.contains("Het T003;")){

                tokens = value.split("Het T003;");

            }else if(value.contains("Amount")){

                tokens = value.split("Amount");

            }else if(value.contains("AMOUNT")){

                tokens = value.split("AMOUNT");

            }else if(value.contains("Net Total")){

                tokens = value.split("Net Total");

            }else if(value.contains("|ToTnL=LKR")){

                tokens = value.split("|ToTnL=LKR");

            }else if(value.contains(".ToTnL=LKR")){

                tokens = value.split(".ToTnL=LKR");

            }else if(value.contains("Amount Due:")){

                tokens = value.split("Amount Due:");

            }



            firstToken = tokens[0];
            secondToken = tokens[1];

            System.out.println("--- FirstToken ---- "+ firstToken);
            System.out.println("--- SecondToken ---- "+ secondToken);

        }catch(Exception e){

        }

        return secondToken;
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

    public void processImage(View view){

        String OCRresult = null;
        String value = null;
        //mTess.setImage(rotateImage(imageBitmap, 90));
        mTess.setImage(imageBitmap);//testing code
        OCRresult = mTess.getUTF8Text();

        TextView OCRTextView = (TextView) findViewById(R.id.textView3);
        //OCRTextView.setText(OCRresult);

        //System.out.println("Extracted Text "+ OCRresult);
        value = extractTotal(OCRresult);
        System.out.println("Extracted Text "+ OCRresult);
        Intent myIntent = new Intent(this, SummaryViewActivity.class);
        myIntent.putExtra("Total", value); //Optional parameters
        this.startActivity(myIntent);
    }



    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

//    private List<String> loadNames() {
//        List<String> result = new ArrayList<String>();
//        InputStream inputStream = getResources().openRawResource(R.raw.names);
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        try {
//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                result.add(s);
//            }
//        } catch (IOException e) {
//            //Log.d(TAG, "io error", e);
//        }
//        return result;
//    }

//    private List<Result> search(String input) {
//        List<Result> results = new ArrayList<>();
//        Queue<Result> minHeap = new PriorityQueue<>();
//        for (String name : mNames) {
//            int levenshteinDistance = StringUtils.getLevenshteinDistance(name, input);
//            Result r = new Result();
//            r.name = name;
//            r.distance = levenshteinDistance;
//            minHeap.add(r);
//        }
//        for (int i = 0; i < 5 && !minHeap.isEmpty(); i++) {
//            results.add(minHeap.poll());
//        }
//        return results;
//    }

    private class Result implements Comparable<Result> {
        int distance;
        String name;

        @Override
        public int compareTo(Result another) {
            return this.distance - another.distance;
        }

        @Override
        public String toString() {
            return "name=" + name + ", distance=" + distance + "\n";
        }
    }


}

