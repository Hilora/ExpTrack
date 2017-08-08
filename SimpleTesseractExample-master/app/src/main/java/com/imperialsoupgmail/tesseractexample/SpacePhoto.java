package com.imperialsoupgmail.tesseractexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chike on 2/11/2017.
 */

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;
    SpacePhoto[] test ;

    public SpacePhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  SpacePhoto[] getSpacePhotos() {



        return new SpacePhoto[]{
                new SpacePhoto("http://i.imgur.com/CNmIkxo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZoxVBd7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/dofvmKv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/S9kQaOx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/4kStguh.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pWrbElx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eTCFbha.jpg?1", "Image"),
                new SpacePhoto("http://i.imgur.com/69SHrAr.jpg","Image"),

                new SpacePhoto("http://i.imgur.com/0xkpKTJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tWPvyye.jpg","Image"),
                new SpacePhoto("http://i.imgur.com/psk1CDF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BXivMQ6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5I6TJd8.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xx2sQCG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JsutWgT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/arAHohR.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/e3cQ0gF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/s2Ky5tS.jpg", "Image"),

        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
