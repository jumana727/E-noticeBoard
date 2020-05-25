package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.enotice.App.CHANNEL_1_ID;
import static com.example.enotice.App.CHANNEL_2_ID;

public class createnotice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText title, description;
    Button choosefile;
    private static final int PICK_IMAGE_REQUEST = 1;
    final static int PICK_PDF_CODE = 2342;
    private static final String TAG = "";
    Button post;
    Uri imageuri;

    Toolbar toolbar;
    String text,category;
    DatabaseReference databaseReference;
    TextView test;
    private StorageReference storageReference;
    private NotificationManagerCompat notificationManager;
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnotice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Create Notice");
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        post = (Button) findViewById(R.id.post);
        choosefile = (Button) findViewById(R.id.choosefile);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        test=(TextView)findViewById(R.id.category);
        storageReference = FirebaseStorage.getInstance().getReference("notice");
        databaseReference = FirebaseDatabase.getInstance().getReference("notice");

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        progressBar.setVisibility(View.INVISIBLE);

        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotice();
            }
        });


    }


    private void openFileChooser() {
        /*Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);*/


        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /*super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
        }*/
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                imageuri=data.getData();
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addNotice() {
        uploadFile();


    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {
        if (imageuri != null) {
            progressBar.setVisibility(View.VISIBLE);
            final StorageReference filereference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
            final String noticetitle = title.getText().toString();
            final String noticedes = description.getText().toString();
            final String email=homepage.getValue();
            Query query= FirebaseDatabase.getInstance().getReference("student").orderByChild("email").equalTo(email);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children =dataSnapshot.getChildren();
                    for(DataSnapshot child : children){
                        incharge upload= child.getValue(incharge.class);
                       category=upload.phone;
                        test.setText(settings.getComputern());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
           /* Date date = new Date();
            Date newDate = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");*/
            //Calendar calendar=Calendar.getInstance();
            //final String stringdate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
            //adding the file to reference
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/mm/dd  hh:mm aa", Locale.getDefault());
            final String stringdate=sdf.format(new Date());
            filereference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressBar.setVisibility(View.INVISIBLE);
                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                            //creating the upload object to store uploaded image details
                            //adding an upload to firebase database
                            Task<Uri> urlTask= taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl=urlTask.getResult();
                            Upload upload = new Upload(noticedes,downloadUrl.toString(),category, noticetitle,email,stringdate);
                            databaseReference.push().setValue(upload);
                            if (getIntent().hasExtra("ecategory")){
                                Intent intent = new Intent(createnotice.this,fileActivity.class);
                                intent.putExtra("ecategory",getIntent().getStringExtra("ecategory"));

                                startActivity(intent);
                            }
                            String cat="";
                            if(category.equals(settings.getGtun())||category.equals(settings.getGeneraln())||category.equals(settings.getLibraryn())||category.equals(settings.getComputern())||category.equals(settings.getCiviln())
                                    ||category.equals(settings.getMechanicaln())||category.equals(settings.getElectricaln())||category.equals(settings.getEcn())||category.equals(settings.getCddmn())) {
                                cat = category;

                                mRequestQue = Volley.newRequestQueue(getApplicationContext());
                                FirebaseMessaging.getInstance().subscribeToTopic(cat);
                                sendNotification();
                            }

                        }

                        })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress

                        }
                    });
        } else {
            //display an error if no file is selected

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      text=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "select category", Toast.LENGTH_SHORT).show();
    }
    public void sendNotification(){
      /*  notificationManager = NotificationManagerCompat.from(this);
            String titlee,mess;
            titlee=title.getText().toString();
            mess=description.getText().toString();


            Intent activityIntent = new Intent(this, fileActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    0, activityIntent, 0);

            Intent broadcastIntent = new Intent(this, NotificationReciever.class);
            broadcastIntent.putExtra("toastMessage", mess);
            PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                    0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_icon_24dp)
                    .setContentTitle(titlee)
                    .setContentText(mess)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.BLUE)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                    .build();

            notificationManager.notify(1, notification);*/
        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","notice");
            notificationObj.put("body","new notice");

            JSONObject extraData = new JSONObject();

            extraData.put("ecategory",category);



            json.put("notification",notificationObj);
            json.put("data",extraData);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR", "onResponse: ");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","KEY=AAAAZjBwfiw:APA91bFI0bZh2TpPta4YTD6F0XKn0eIqduMOiVZwUP8e4q0ggcP9Fo9qfqgTmKRizJwJw1sDc743XgXo3-IlNwhtS_eCTIthQI5FGjsqgwhcKYvB2tp391sCKs4P9GIraPcfkZN-u2Xs");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            e.printStackTrace();
        }
    }



}






