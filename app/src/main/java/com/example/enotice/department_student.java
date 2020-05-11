package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class department_student extends AppCompatActivity {
    Button computer,ec,electrical,mechanical,cddm,civil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_student);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        computer=(Button)findViewById(R.id.computer);
        civil=(Button)findViewById(R.id.gtu);
        ec=(Button)findViewById(R.id.library);
        electrical=(Button)findViewById(R.id.general);
        mechanical=(Button)findViewById(R.id.mechanical);
        cddm=(Button)findViewById(R.id.cddm);
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "computer");
                startActivity(i);
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "civil");
                startActivity(i);
            }
        });
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "ec");
                startActivity(i);
            }
        });
        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "electrical");
                startActivity(i);
            }
        });
        mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "mechanical");
                startActivity(i);
            }
        });
        cddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "cddm");
                startActivity(i);
            }
        });
    }
}
