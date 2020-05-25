package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
CardView department,gtu,general,library;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         toolbar=findViewById(R.id.tbhome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        department=(CardView) findViewById(R.id.department);
        general=(CardView) findViewById(R.id.general);
        gtu=(CardView) findViewById(R.id.gtu);
        library=(CardView) findViewById(R.id.library);
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),department_student.class);
                startActivity(i);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),file3activity.class);
                i.putExtra("ecategory", "General");
                startActivity(i);
            }
        });
        gtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),file3activity.class);
                i.putExtra("ecategory", "Gtu");
                startActivity(i);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),file3activity.class);
                i.putExtra("ecategory", "Library");
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.student_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent i = new Intent(getApplicationContext(), settings.class);
                startActivity(i);
                return true;
            case R.id.loginasfaculty:
                Intent j = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(j);
                return true;
            case R.id.aboutus:
                Intent k = new Intent(getApplicationContext(), about_us.class);
                startActivity(k);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
