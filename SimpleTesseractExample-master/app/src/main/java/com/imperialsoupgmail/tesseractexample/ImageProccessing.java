package com.imperialsoupgmail.tesseractexample;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lightway on 8/23/17.
 */

public class ImageProccessing {

    private TessBaseAPI mTess;
    boolean validValue = false;
    String datapath = "";

//    public void init(){
//
//        //initialize Tesseract API
//        String language = "eng";
//        datapath = getFilesDir()+ "/tesseract/";
//        mTess = new TessBaseAPI();
//
//        checkFile(new File(datapath + "tessdata/"));
//
//        mTess.init(datapath, language);
//
//
//    }

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

    public String processImage(View view,Bitmap imageBitmap,TextView OCRView){

        String OCRresult = null;
        String value = null;
        //mTess.setImage(rotateImage(imageBitmap, 90));
        mTess.setImage(imageBitmap);//testing code
        OCRresult = mTess.getUTF8Text();

        //TextView OCRTextView = (TextView) findViewById(R.id.textView3);
        TextView OCRTextView = (TextView) OCRView;
        OCRTextView.setText(OCRresult);
        System.out.println("Extracted Text "+ OCRresult);
        value = extractTotal(OCRresult);
        System.out.println("Extracted Text -----"+ OCRresult);
//        Intent myIntent = new Intent(SpacePhotoActivity.this, SummaryViewActivity.class);
//        myIntent.putExtra("Total", value); //Optional parameters
//        this.startActivity(myIntent);
        return value;
    }


    public String extractTotal(String value){

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





}
