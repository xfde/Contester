package com.alexc.hacktothefuture;


import android.net.Uri;

public class Item {
    private String mName;
    private Uri mImageUri;
    private String mImageUriS;
    private String mParent;
    private  String key;
    public Item(String mImageUriS, String mName,String key,String mParent)
    {
        this.mParent=mParent;
        this.mImageUriS=mImageUriS;
        this.mName=mName;
        this.key=key;
    }

    public String getKey() {
        return key;
    }
    public String getmParent(){
        return mParent;
    }

    public String getmImageUriS(){
        return mImageUriS;
    }

    public void setmImageUri(Uri mImageUri)
    {
        this.mImageUri=mImageUri;
    }
    public String getmName(){
        return mName;
    }
    public void setmName(String mName){
        this.mName=mName;
    }
}
