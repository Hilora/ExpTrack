package com.imperialsoupgmail.tesseractexample;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.googlecode.tesseract.android.TessBaseAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
//import com.algorithmia.*;
//import com.algorithmia.algo.*;


public class CaptureImageActivity extends AppCompatActivity {

    private static final int PIXEL_TOLERANCE_VALUE = 50;
    ImageView result;
    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public boolean isRemoteImage = false;


    //private static final String TAG = "CaptureImageActivity";


    private TessBaseAPI mTess;

    String datapath = "";
    Bitmap imageBitmap ;
    Button btnGallery ;
    Button btnProcess ;
    Button btnEnhance ;
    Button btnSharp ;
    Button btnRemove ;
    String jsonString;
    int count = 0;
    int position = 0;
    boolean validValue = false;

    private static final String TAG = "LDSS";
    private EditText mNameField;
    private TextView mSearchResult;
    private List<String> mNames;
    private SpacePhoto[] mSpacePhotos;
    SpacePhoto spacePhoto ;

    String date;
    String totalValue;


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

        btnEnhance = (Button)findViewById(R.id.btnEnhance);
        btnEnhance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    System.out.println("Enhancing Image...");
                    imageBitmap = setGrayscale(imageBitmap);
                    imageBitmap = setGrayscale(imageBitmap);
                    //imageBitmap = removeNoise(imageBitmap);

                    //imageBitmap = generateEdgeImage(imageBitmap,imageBitmap.getWidth(),imageBitmap.getHeight());
                    //imageBitmap = sharpen(imageBitmap,0.1);



                    result.setImageBitmap(imageBitmap);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        btnRemove = (Button)findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{

                    //imageBitmap = removeNoise(imageBitmap);
                    imageBitmap = RemoveNoise(imageBitmap);




                    result.setImageBitmap(imageBitmap);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        btnSharp = (Button)findViewById(R.id.btnSharp);
        btnSharp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    System.out.println("Enhancing Image...");
                    //imageBitmap = setGrayscale(imageBitmap);
                    //imageBitmap = setGrayscale(imageBitmap);
                    //imageBitmap = removeNoise(imageBitmap);

                    //imageBitmap = generateEdgeImage(imageBitmap,imageBitmap.getWidth(),imageBitmap.getHeight());
                    //imageBitmap = sharpen(imageBitmap,0.1);

                    float[] low = { -0.60f, -0.60f, -0.60f, -0.60f, 5.81f, -0.60f,
                            -0.60f, -0.60f, -0.60f };

                    float[] medium = { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f,
                            0.0f

                    };

                    float[] high = { -0.15f, -0.15f, -0.15f, -0.15f, 2.2f, -0.15f, -0.15f,
                            -0.15f, -0.15f
                    };
                    imageBitmap = doSharpen(imageBitmap, high);


                    result.setImageBitmap(imageBitmap);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSpacePhotos = SpacePhoto.getSpacePhotos();
        Button click = (Button)findViewById(R.id.button4);
        result = (ImageView)findViewById(R.id.imageView2);
        btnProcess = (Button)findViewById(R.id.btnProcess);
        spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);
        isRemoteImage = getIntent().getBooleanExtra("key",isRemoteImage);
        position = getIntent().getIntExtra("position",position);
        System.out.println("Is Remote image state : "+isRemoteImage);
        init();

        loadRemoteImage();

         jsonString = loadJSONFromAsset();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray contacts = json.getJSONArray("data");



            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);


                String key = c.getString("key");
                System.out.println("-----------------JSON read ---------------- "+key);

//                // adding each child node to HashMap key => value
//                contact.put("id", id);
//                contact.put("name", name);
//                contact.put("email", email);
//                contact.put("mobile", mobile);
//
//                // adding contact to contact list
//                contactList.add(contact);
            }





        } catch (Exception e) {
            e.printStackTrace();
        }


        //initialize Tesseract API
        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);



    }


    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("content.json");

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

           // imageBitmap = Bitmap.createScaledBitmap(imageBitmap,(int)(imageBitmap.getWidth()*0.2), (int)(imageBitmap.getHeight()*0.2), true);

            result.setImageBitmap(imageBitmap);



        }
    }

    void loadRemoteImage(){

        if(isRemoteImage == true){

            SpacePhoto spacePhoto = mSpacePhotos[position];


            Glide.with(this)
                    .load(spacePhoto.getUrl()).asBitmap()
                    .into(result);
            System.out.println("Loading image...");

//            try{
//                Bitmap theBitmap  = Glide.with(this)
//                 .load(spacePhoto.getUrl()).asBitmap().into(100,100).get();
//
//                imageBitmap = theBitmap;
//            }catch(Exception e){
//                e.printStackTrace();
//            }



        }

    }

    public void loadImage() {

        try{
                //testing code
                String url = "drawable/"+"test_image"+count;
                ++count;
                int imageKey = getResources().getIdentifier(url, "drawable", getPackageName());

                imageBitmap = BitmapFactory.decodeResource(getResources(), imageKey);

            //imageBitmap = setGrayscale(imageBitmap);


            }catch(Exception e){

            }

        result.setImageBitmap(imageBitmap);

    }

    public String extractTotal(String value){



        String firstToken ;
        String secondToken = null;
        validValue = false;

        String[] tokens = value.split(" ");

        //--

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray contacts = json.getJSONArray("data");


            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);


                String key = c.getString("key");
                System.out.println("-----------------JSON key compared ---------------- "+key);



                 if(value.contains(key)){

                        tokens = value.split(key);
                        System.out.println("****** Detected key -"+key+"******");
                        validateRead();
                        break;
                  }

//                // adding each child node to HashMap key => value
//                contact.put("id", id);
//                contact.put("name", name);
//                contact.put("email", email);
//                contact.put("mobile", mobile);
//
//                // adding contact to contact list
//                contactList.add(contact);
            }



//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //--
/*
       String firstToken ;
        String secondToken = null;
        validValue = false;

        //String thrirdToken = null;

        try{
            String[] tokens = value.split(" ");

            if(value.contains("TOTAL DUE")){

                tokens = value.split("TOTAL DUE");
                System.out.println("****** TOTAL DUE ******");
                validateRead();

            }else if(value.contains(" NET TOTAL I")){

                tokens = value.split(" NET TOTAL I");
                System.out.println("******  NET TOTAL I   ******");
                validateRead();

            }else if(value.contains("00000976 1")){

                tokens = value.split("00000976 1");
                System.out.println("****** 00000976 1 ******");
                validateRead();

            }else if(value.contains("TOTAL:LKR")){

                tokens = value.split("TOTAL:LKR");
                System.out.println("****** TOTAL:LKR ******");
                validateRead();

            }else if(value.contains("Total")){

                tokens = value.split("Total");
                System.out.println("****** Total ******");
                validateRead();

            }else if(value.contains("SUB IOIAL")){

                tokens = value.split("SUB IOIAL");
                System.out.println("****** SUB IOIAL ******");
                validateRead();

            }else if(value.contains("SUB TUTAL")){

                tokens = value.split("SUB TUTAL");
                System.out.println("****** SUB TUTAL ******");
                validateRead();

            }else if(value.contains("Het T003;")){

                tokens = value.split("Het T003;");
                System.out.println("****** Het T003; ******");
                validateRead();

            }else if(value.contains("Amount")){

                tokens = value.split("Amount");
                System.out.println("****** Amount ******");
                validateRead();

            }else if(value.contains("AMOUNT")){

                tokens = value.split("AMOUNT");
                System.out.println("****** AMOUNT ******");
                validateRead();

            }else if(value.contains("Net Total")){

                tokens = value.split("Net Total");
                System.out.println("****** Net Total ******");
                validateRead();

            }else if(value.contains("|ToTnL=LKR")){

                tokens = value.split("|ToTnL=LKR");
                System.out.println("****** |ToTnL=LKR ******");
                validateRead();

            }else if(value.contains(".ToTnL=LKR")){

                tokens = value.split(".ToTnL=LKR");
                System.out.println("****** TOTAL:LKR ******");
                validateRead();

            }else if(value.contains("Amount Due:")){

                tokens = value.split("Amount Due:");
                System.out.println("****** Amount Due:  ******");
                validateRead();

            }else if(value.contains("Amount Due: ")){

                tokens = value.split("Amount Due: ");
                System.out.println("****** Amount Due: ******");
                validateRead();

            }else if(value.contains("lToTnL=LKR")){

                tokens = value.split("lToTnL=LKR");
                System.out.println("****** lToTnL=LKR ******");
                validateRead();

            }else if(value.contains("|TOTRL.2LKR")){

                tokens = value.split("|TOTRL.2LKR");
                System.out.println("****** |TOTRL.2LKR ******");
                validateRead();

            }else if(value.contains("1T0TAL=LKR")){

                tokens = value.split("1T0TAL=LKR");
                System.out.println("****** 1T0TAL=LKR ******");
                validateRead();

            }else if(value.contains("Balame Due")){

                tokens = value.split("Balame Due");
                System.out.println("****** Balame Due ******");
                validateRead();

            }else if(value.contains("WELL:")){

                tokens = value.split("WELL:");
                System.out.println("****** WELL: ******");
                validateRead();

            }else if(value.contains("Net Amount 92 . 5")){

                tokens = value.split("Net Amount 92 . 5");
                System.out.println("****** hI . 5 ******");
                validateRead();

            }else if(value.contains("Balance Due")){

                tokens = value.split("Balance Due");
                System.out.println("****** Balance Due ******");
                validateRead();

            }else if(value.contains("Total l pieces")){

                tokens = value.split("Total l pieces");
                System.out.println("****** Total l pieces ******");
                validateRead();

            }else if(value.contains("Subtotai")){

                tokens = value.split("Subtotai");
                System.out.println("****** Subtotai ******");
                validateRead();

            }else if(value.contains(".N- : ")){

                tokens = value.split(".N- : ");
                System.out.println("****** .N- :   ******");
                validateRead();

            }else if(value.contains("N- =")){

                tokens = value.split("N- =");
                System.out.println("******.N- =  ******");
                validateRead();

            }else if(value.contains("TOTAL")){

                tokens = value.split("TOTAL");
                System.out.println("****** TOTAL ******");
                validateRead();

            }

*/

            firstToken = tokens[0];
            secondToken = tokens[1];
            //thrirdToken = tokens[2];


            System.out.println("--- FirstToken ---- "+ firstToken);
            System.out.println("--- SecondToken ---- "+ secondToken);
            System.out.println("--- Value ---- "+ secondToken.split("\n",2)[0]);
            secondToken = secondToken.split("\n",2)[0];


            int wordCount = secondToken.trim().isEmpty() ? 0 : secondToken.trim().split("\\s+").length;
            System.out.println("--- No of sets detected ---- "+ wordCount);

            if (wordCount>1){

                String words[] = secondToken.split(" ");
                for (String s: words) {
                    if(isNumeric(s)){

                        System.out.println("====== Words Detected ====== "+ s);
                    }

                }

            }


        }catch(Exception e){

        }

        if (validValue == false){
            secondToken = "Please Retake Image...";
            System.out.println("------------Calling algorithmia------------");
//            try{
//                //String input = "http://i.imgur.com/CNmIkxo.jpg";
//                String input = "\"data://lightway/images/test_image23.jpg\"";
//                AlgorithmiaClient client = Algorithmia.client("simGOeJ6EkwUyE2zMa8BfjUVlUz1");
//                Algorithm algo = client.algo("algo://ocr/ReceiptRecognition/0.1.6");
//                AlgoResponse result = algo.pipeJson(input);
//                System.out.println(result.asJsonString());
//
//                System.out.println("------------Calling algorithmia done------------");
//            }catch(Exception e){
//                e.printStackTrace();
//            }

            Intent myIntent = new Intent(this, LearnReceiptActivity.class);
//
            myIntent.putExtra("Total", value); //Optional parameters
            try {

                    myIntent.putExtra("Currency", ""); //Optional parameters

            }catch (Exception e){
                e.printStackTrace();
            }
          this.startActivity(myIntent);
        }else{
            validValue = false;
        }

        return secondToken;
    }

    public void validateRead(){
        validValue = true;
    }

    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
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

    public Bitmap RemoveNoise(Bitmap bmap) {
        for (int x = 0; x < bmap.getWidth(); x++) {
            for (int y = 0; y < bmap.getHeight(); y++) {
                int pixel = bmap.getPixel(x, y);
                int R = Color.red(pixel);
                int G = Color.green(pixel);
                int B = Color.blue(pixel);
                if (R < 162 && G < 162 && B < 162)
                    bmap.setPixel(x, y, Color.BLACK);
            }
        }
        for (int  x = 0; x < bmap.getWidth(); x++) {
            for (int y = 0; y < bmap.getHeight(); y++) {
                int pixel = bmap.getPixel(x, y);
                int R = Color.red(pixel);
                int G = Color.green(pixel);
                int B = Color.blue(pixel);
                if (R > 162 && G > 162 && B > 162)
                    bmap.setPixel(x, y, Color.WHITE);
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

        if(isRemoteImage == true){

            result.buildDrawingCache();
            Bitmap bmap = result.getDrawingCache();
//
            imageBitmap = bmap;
//            result.setDrawingCacheEnabled(false);
        }

        if(imageBitmap!=null){
            System.out.println("-----------Tess image loading----------");
            String OCRresult = null;
            String value = null;


            mTess.setImage(imageBitmap);//testing code
            OCRresult = mTess.getUTF8Text();

            TextView OCRTextView = (TextView) findViewById(R.id.textView3);

            value = extractTotal(OCRresult);
            System.out.println("Extracted Text -----"+ OCRresult);
                if (validValue == true) {
                    Intent myIntent = new Intent(this, SummaryViewActivity.class);
                    myIntent.putExtra("Total", value); //Optional parameters
                    try {
                        if (value.contains("$")) {
                            myIntent.putExtra("Currency", "Dollars"); //Optional parameters
                        } else if (value.contains("¥")) {
                            myIntent.putExtra("Currency", "Yen"); //Optional parameters
                        } else {
                            myIntent.putExtra("Currency", "Sri Lankan Rupees"); //Optional parameters
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.startActivity(myIntent);
                }
            }


    }

    public Bitmap doSharpen(Bitmap original, float[] radius) {
        Bitmap bitmap = Bitmap.createBitmap(
                original.getWidth(), original.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript rs = RenderScript.create(this);

        Allocation allocIn = Allocation.createFromBitmap(rs, original);
        Allocation allocOut = Allocation.createFromBitmap(rs, bitmap);

        ScriptIntrinsicConvolve3x3 convolution
                = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));
        convolution.setInput(allocIn);
        convolution.setCoefficients(radius);
        convolution.forEach(allocOut);

        allocOut.copyTo(bitmap);
        rs.destroy();

        return bitmap;

    }

    public static Bitmap sharpen(Bitmap src, double weight) {
        double[][] SharpConfig = new double[][] {
                { 0 , -2    , 0  },
                { -2, weight, -2 },
                { 0 , -2    , 0  }
        };
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        convMatrix.applyConfig(SharpConfig);
        convMatrix.Factor = weight - 8;
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public Bitmap generateEdgeImage(Bitmap highContrastGreyImage, int width, int height) {
        Bitmap edgeImg = Bitmap.createBitmap(width, height, highContrastGreyImage.getConfig());

        int x = 0, y = 0;
        int left = 0, upper = 0, rightUpper = 0;

        for(x = 0; x < width; x++) {
            for(y = 0; y < height; y++) {
                if(0 < x && x < width-1 && 0 < y && y < height) {
                    int pixel = Color.blue(highContrastGreyImage.getPixel(x, y));
                    int pixelLeft = Color.blue(highContrastGreyImage.getPixel(x - 1, y));
                    left = pixel - pixelLeft;

                    int pixelUp = Color.blue(highContrastGreyImage.getPixel(x, y - 1));
                    upper = pixel - pixelUp;

                    int pixelRU = Color.blue(highContrastGreyImage.getPixel(x + 1, y - 1));
                    rightUpper = pixel - pixelRU;

                    int pixelMax = Math.max(left, Math.max(upper, rightUpper));

                    if(pixelMax < PIXEL_TOLERANCE_VALUE) {
                        edgeImg.setPixel(x, y, Color.rgb(0, 0, 0));
                    } else {
                        edgeImg.setPixel(x, y, Color.rgb(pixelMax, pixelMax, pixelMax));
                    }
                }
            }
        }

        return edgeImg;
    }


    private void saveToDB() {
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Expense.COLUMN_DATE, date);
        values.put(SampleDBContract.Expense.COLUMN_TOTAL,totalValue);

        long newRowId = database.insert(SampleDBContract.Expense.TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

//    private void readFromDB() {
//        String firstname = binding.firstnameEditText.getText().toString();
//        String lastname = binding.lastnameEditText.getText().toString();
//
//        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();
//
//        String[] selectionArgs = {"%" + firstname + "%", "%" + lastname + "%"};
//
//        Cursor cursor = database.rawQuery(SampleDBContract.SELECT_Expense_WITH_EMPLOYER, selectionArgs);
//        binding.recycleView.setAdapter(new SampleJoinRecyclerViewCursorAdapter(this, cursor));
//    }

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

