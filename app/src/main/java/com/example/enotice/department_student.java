package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class department_student extends AppCompatActivity {
    CardView computer,ec,electrical,mechanical,cddm,civil;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_student);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        computer=(CardView) findViewById(R.id.computer);
        civil=(CardView) findViewById(R.id.civil);
        ec=(CardView) findViewById(R.id.ec);
        electrical=(CardView) findViewById(R.id.electrical);
        mechanical=(CardView) findViewById(R.id.mechanical);
        cddm=(CardView) findViewById(R.id.cddm);
        tb=(Toolbar)findViewById(R.id.dept_tb);
        tb.setTitle("Select Department");
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Computer");
                startActivity(i);
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Civil");
                startActivity(i);
            }
        });
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Ec");
                startActivity(i);
            }
        });
        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Electrical");
                startActivity(i);
            }
        });
        mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Mechanical");
                startActivity(i);
            }
        });
        cddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(department_student.this,file3activity.class);
                i.putExtra("ecategory", "Cddm");
                startActivity(i);
            }
        });
    }
}
