package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Query extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView myText;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        databaseReference = FirebaseDatabase.getInstance().getReference("message");
        btn=(Button)findViewById(R.id.send);
         myText = (TextView) findViewById(R.id.textView);
         try {
             databaseReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     Iterable<DataSnapshot> children =dataSnapshot.getChildren();
                     for(DataSnapshot child : children){
                         String newmessage=child.getValue(String.class);
                         String text=myText.getText().toString();
                         myText.setText(text+newmessage);

                     }

                 }
                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                     myText.setText("cancelled");

                 }
             });
         }
         catch (Exception e){
             Toast.makeText(getApplicationContext(), "sbbsk", Toast.LENGTH_SHORT).show();
         }
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 EditText myEditText = (EditText) findViewById(R.id.message);

                 databaseReference.push().setValue(myEditText.getText().toString());
                 myEditText.setText("");

             }
         });

    }


}
