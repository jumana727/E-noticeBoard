package com.example.enotice;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class FileUpload {
        public String mimageurl;
        public String title,des,etext,facname;

    public FileUpload() {
    }

    public FileUpload(String mimageurl, String title, String des,String etext,String facname) {
        this.mimageurl = mimageurl;
        this.title = title;
        this.des = des;
        this.etext=etext;
        this.facname=facname;

    }

    public String getEtext() {
        return etext;
    }

    public void setEtext(String text) {
        this.etext = etext;
    }

    public String getMimageurl() {
        return mimageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public void setMimageurl(String mimageurl) {
        this.mimageurl = mimageurl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFacname() {
        return facname;
    }

    public void setFacname(String facname) {
        this.facname = facname;
    }
}
