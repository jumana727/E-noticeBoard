package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.createnotice:
                        Intent i=new Intent(getApplicationContext(),createnotice.class);
                        startActivity(i);
                        return true;

                    case R.id.logout:
                        firebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        return true;

                    case R.id.Mynotices:
                        Intent v=new Intent(getApplicationContext(),myNotices.class);
                        startActivity(v);
                        return true;

                    case R.id.settings:
                        Intent p=new Intent(getApplicationContext(),settings.class);
                        startActivity(p);
                        return true;
                }
                return true;

            }
        });
    }
}
