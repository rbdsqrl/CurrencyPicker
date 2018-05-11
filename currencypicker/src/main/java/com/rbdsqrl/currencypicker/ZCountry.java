package com.rbdsqrl.currencypicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hbb20.CCPCountry;

public class ZCountry {

    String phoneCode;
    String countryName;
    Bitmap flagID;

    public ZCountry(String phoneCode, String countryName, Bitmap flagID) {
        this.phoneCode = phoneCode;
        this.countryName = countryName;
        this.flagID = flagID;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Bitmap getFlag() {
        return flagID;
    }

    public void setFlag(Bitmap flagID) {
        this.flagID = flagID;
    }

    public static ZCountry generateZCountry(CCPCountry ccpCountry, Context context){
        Bitmap flag = BitmapFactory.decodeResource(context.getResources(),ccpCountry.getFlagID());
        return new ZCountry(ccpCountry.getPhoneCode(), ccpCountry.getName(), flag);
    }

    @Override
    public String toString() {
        return "ZCountry{" +
                "phoneCode='" + phoneCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", flagID=" + flagID +
                '}';
    }
}
