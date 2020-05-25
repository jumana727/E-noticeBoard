package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class file3activity extends AppCompatActivity implements FileAdapter.OnItemClickListener  {
    private RecyclerView mnRecyclerView;
    private FileAdapter mAdapter;
    private Toolbar toolbar;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBlistener;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file3activity);
        mnRecyclerView = findViewById(R.id.mrecycler_view);
        mnRecyclerView.setHasFixedSize(true);
        mnRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar=(Toolbar)findViewById(R.id.tb2);
        mProgressCircle = findViewById(R.id.mprogress_circle);
        Intent i=getIntent();
        String cat=i.getStringExtra("ecategory");
        toolbar.setTitle(cat+" Notices");
        mUploads = new ArrayList<Upload>();
        mAdapter = new FileAdapter(file3activity.this, mUploads);

        mnRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(file3activity.this);
        mStorage=FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("notice");

        Query query= FirebaseDatabase.getInstance().getReference("notice").orderByChild("etext").equalTo(cat);
        mDBlistener= query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
          /*  if (dataSnapshot.exists()) {
                HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for (String key : dataMap.keySet()) {

                        Object data = dataMap.get(key);

                        try {
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;

                            Upload mupload = new Upload((String) userData.get("des"),(String) userData.get("etext") ,(String) userData.get("mimageurl"), (String) userData.get("title"));
                            //  addTextToView(mUser.getName() + " - " + Integer.toString(mUser.getAge()));
                            mUploads.add(mupload);
                            mAdapter = new ImageAdapter(fileActivity.this, mUploads) {
                            };

                            mRecyclerView.setAdapter(mAdapter);
                            mProgressCircle.setVisibility(View.INVISIBLE);

                        } catch (ClassCastException cce) {

// If the object can’t be casted into HashMap, it means that it is of type String.


                        }

                    }
                }
            }


*/       Iterable<DataSnapshot> children =dataSnapshot.getChildren();
                for(DataSnapshot child : children){
                    Upload upload= child.getValue(Upload.class);
                    upload.setMkey(child.getKey());
                    mUploads.add(upload);

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(file3activity.this, "hsjhj", Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Upload selectedItem=mUploads.get(position);
        Intent i=new Intent(getApplicationContext(),showNotice.class);
        i.putExtra("uploadclass",selectedItem);
        startActivity(i);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBlistener);
    }

}
