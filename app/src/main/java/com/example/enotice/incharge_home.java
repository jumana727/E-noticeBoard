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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class incharge_home extends AppCompatActivity {
FirebaseAuth firebaseAuth;

    private static String value;
    public static String getValue(){
        return value;
    }
    Button department,gtu,general,library;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incharge_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("E-Notice");
        department=(Button)findViewById(R.id.department);
        gtu=(Button)findViewById(R.id.gtu);
        general=(Button)findViewById(R.id.general);
        library=(Button)findViewById(R.id.library);
        Intent intent=getIntent();
        String facemail=intent.getStringExtra("facemail");
        value=facemail;

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),departments.class);
                startActivity(i);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),fileActivity.class);
                i.putExtra("ecategory", "general");
                startActivity(i);
            }
        });
        gtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),fileActivity.class);
                i.putExtra("ecategory", "gtu");
                startActivity(i);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),fileActivity.class);
                i.putExtra("ecategory", "library");
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.createnotice:
                Intent i=new Intent(getApplicationContext(),createnotice.class);
                startActivity(i);
                return true;

            case R.id.logout:
                firebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
                default:
                    return super.onOptionsItemSelected(item);


            case R.id.Mynotices:
                Intent v=new Intent(getApplicationContext(),myNotices.class);
                startActivity(v);
                return true;

            case R.id.settings:
                Intent p=new Intent(getApplicationContext(),settings.class);
                startActivity(p);
                return true;

        }

    }
}
