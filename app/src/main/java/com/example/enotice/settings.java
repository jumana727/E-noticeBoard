package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settings extends AppCompatActivity {
    private static String gtun,generaln,libraryn,computern,civiln,mechanicaln,electricaln,ecn,cddmn;
    public static String getGtun(){
        return gtun;
    }
    public static String getGeneraln(){
        return generaln;
    }
    public static String getLibraryn(){
        return libraryn;
    }
    public static String getComputern(){
        return computern;
    }
    public static String getCiviln(){
        return civiln;
    }
    public static String getMechanicaln(){
        return mechanicaln;
    }
    public static String getElectricaln(){
        return electricaln;
    }
    public static String getEcn(){
        return ecn;
    }
    public static String getCddmn(){
        return cddmn;
    }

    Switch gtu,general,library,computer,civil,mechanical,electrical,ec,cddm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        gtu=(Switch)findViewById(R.id.gtunotif);
        general=(Switch)findViewById(R.id.generalnotif);
        library=(Switch)findViewById(R.id.librarynotif);
        computer=(Switch)findViewById(R.id.computernotif);
        civil=(Switch)findViewById(R.id.civilnotif);
        mechanical=(Switch)findViewById(R.id.mechanicalnotif);
        electrical=(Switch)findViewById(R.id.electricalnotif);
        ec=(Switch)findViewById(R.id.ecnotif);
        cddm=(Switch)findViewById(R.id.cddmnotif);

        Boolean gtuvalue =false;
        final SharedPreferences sharedPreferences=getSharedPreferences("isChecked",0);
        gtuvalue= sharedPreferences.getBoolean("isChecked",gtuvalue);
        gtu.setChecked(gtuvalue);
        gtu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(gtu.isChecked()){
                        gtun="Gtu";
                        sharedPreferences.edit().putBoolean("isChecked",true).apply();
                    }
                    else{
                        sharedPreferences.edit().putBoolean("isChecked",false).apply();
                        gtun="";
                    }
                }
        });
        Boolean generalvalue =true;
        final SharedPreferences gensharedPreferences=getSharedPreferences("generalnm",0);
        generalvalue= gensharedPreferences.getBoolean("generalnm",generalvalue);
        general.setChecked(generalvalue);
        general.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(general.isChecked()){
                    gensharedPreferences.edit().putBoolean("generalnm",true).apply();
                    generaln="General";
                }
                else {
                    gensharedPreferences.edit().putBoolean("generalnm",false).apply();
                    generaln="";
                }
            }
        });
        Boolean libraryvalue =true;
        final SharedPreferences libsharedPreferences=getSharedPreferences("libr",0);
        libraryvalue= libsharedPreferences.getBoolean("libr",libraryvalue);
        library.setChecked(libraryvalue);
        library.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(library.isChecked()){
                    libraryn="Library";
                    libsharedPreferences.edit().putBoolean("libr",true).apply();
                }
                else {
                    libsharedPreferences.edit().putBoolean("libr",false).apply();
                    libraryn="";
                }
            }
        });
        Boolean computervalue =true;
        final SharedPreferences bsharedPreferences=getSharedPreferences("com",0);
        computervalue= bsharedPreferences.getBoolean("com",computervalue);
        computer.setChecked(computervalue);
        computer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(computer.isChecked()){

                    bsharedPreferences.edit().putBoolean("com",true).apply();
                    computern="Computer";
                }
                else {
                    computern="";
                    bsharedPreferences.edit().putBoolean("com",false).apply();
                }
            }
        });
        Boolean civilvalue =true;
        final SharedPreferences csharedPreferences=getSharedPreferences("civilv",0);
        civilvalue= csharedPreferences.getBoolean("civilv",civilvalue);
        civil.setChecked(civilvalue);
        civil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(civil.isChecked()){

                    csharedPreferences.edit().putBoolean("civilv",true).apply();
                    civiln="Civil";
                }
                else {
                    civiln="";
                    csharedPreferences.edit().putBoolean("civilv",false).apply();
                }
            }
        });
        Boolean mechanicalvalue =false;
        final SharedPreferences dsharedPreferences=getSharedPreferences("mech",0);
        mechanicalvalue= dsharedPreferences.getBoolean("mech",mechanicalvalue);
        mechanical.setChecked(mechanicalvalue);
        mechanical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mechanical.isChecked()){
                    mechanicaln="Mechanical";
                    dsharedPreferences.edit().putBoolean("mech",true).apply();
                }
                else {
                    mechanicaln="";
                    dsharedPreferences.edit().putBoolean("mech",false).apply();
                }
            }
        });
        Boolean electricalvalue =false;
        final SharedPreferences esharedPreferences=getSharedPreferences("elec",0);
        electricalvalue= esharedPreferences.getBoolean("elec",electricalvalue);
        electrical.setChecked(electricalvalue);
        electrical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(electrical.isChecked()){
                    electricaln="Electrical";
                    esharedPreferences.edit().putBoolean("elec",true).apply();
                }
                else {
                    electricaln="";
                    esharedPreferences.edit().putBoolean("elec",false).apply();
                }
            }
        });
        Boolean ecvalue =false;
        final SharedPreferences sharedPreferences1=getSharedPreferences("ec",0);
        ecvalue= sharedPreferences1.getBoolean("ec",ecvalue);
        ec.setChecked(ecvalue);
        ec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ec.isChecked()){
                    ecn="Ec";
                    sharedPreferences1.edit().putBoolean("ec",true).apply();
                }
                else {
                    ecn="";
                    sharedPreferences1.edit().putBoolean("ec",false).apply();
                }
            }
        });
        Boolean cddmvalue =false;
        final SharedPreferences sharedPreferences2=getSharedPreferences("cddm",0);
        cddmvalue= sharedPreferences2.getBoolean("cddm",cddmvalue);
        cddm.setChecked(cddmvalue);
        cddm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cddm.isChecked()){
                    cddmn="Cddm";
                    sharedPreferences2.edit().putBoolean("cddm",true).apply();
                }
                else {
                    cddmn="";
                    sharedPreferences2.edit().putBoolean("cddm",false).apply();
                }
            }
        });


    }
}
