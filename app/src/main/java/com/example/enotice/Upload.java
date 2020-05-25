

package com.example.enotice;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

@IgnoreExtraProperties
@SuppressWarnings("serial")
public class Upload implements Serializable {
    public String mimageurl;
    public String title,des,etext;
    public String mkey,facMail,date;

    public Upload() {
    }

    public Upload(String des, String mimageurl, String etext,String title,String facMail,String date) {
        this.des = des;
        this.mimageurl = mimageurl;
        this.etext = etext;
        this.title=title;
        this.facMail=facMail;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFacMail() {
        return facMail;
    }

    public void setFacMail(String facMail) {
        this.facMail = facMail;
    }

    public String getEtext() {
        return etext;
    }

    public void setEtext(String etext) {
        this.etext = etext;
    }

    public String getMimageurl() {
        return mimageurl;
    }

    public void setMimageurl(String mimageurl) {
        this.mimageurl = mimageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    @Exclude
    public String getMkey() {
        return mkey;
    }
    @Exclude
    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

}
