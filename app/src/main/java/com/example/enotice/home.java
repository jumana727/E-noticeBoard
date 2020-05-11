package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
Button department,gtu,general,library;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        department=(Button)findViewById(R.id.department);
        general=(Button)findViewById(R.id.general);
        gtu=(Button)findViewById(R.id.gtu);
        library=(Button)findViewById(R.id.library);
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
                i.putExtra("ecategory", "general");
                startActivity(i);
            }
        });
        gtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),file3activity.class);
                i.putExtra("ecategory", "gtu");
                startActivity(i);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),file3activity.class);
                i.putExtra("ecategory", "library");
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
