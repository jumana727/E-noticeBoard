package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class fileActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBlistener;
    private List<Upload> mUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);
        Intent i=getIntent();
        String cat=i.getStringExtra("ecategory");
       mUploads = new ArrayList<Upload>();
        mAdapter = new ImageAdapter(fileActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(fileActivity.this);
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
            Toast.makeText(fileActivity.this, "hsjhj", Toast.LENGTH_SHORT).show();
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
    public void onDeleteClick(int position) {
        Upload selectedItem=mUploads.get(position);
        final String selectedKey=selectedItem.getMkey();


                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(fileActivity.this, "deleted successfully", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBlistener);
    }
}
