package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.enotice.App.CHANNEL_1_ID;
import static com.example.enotice.App.CHANNEL_2_ID;

public class createnotice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText title, description;
    Button choosefile;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "";
    Button post;
    Uri imageuri;
    Spinner spinner;
    Toolbar toolbar;
    String text;
    DatabaseReference databaseReference;
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
        spinner=(Spinner)findViewById(R.id.spinner);
        storageReference = FirebaseStorage.getInstance().getReference("notice");
        databaseReference = FirebaseDatabase.getInstance().getReference("notice");
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
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
            final String email=incharge_home.getValue();
            //adding the file to reference
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
                            FileUpload upload = new FileUpload(downloadUrl.toString(), noticetitle, noticedes,text,email);
                            databaseReference.push().setValue(upload);
                            if (getIntent().hasExtra("ecategory")){
                                Intent intent = new Intent(createnotice.this,fileActivity.class);
                                intent.putExtra("ecategory",getIntent().getStringExtra("ecategory"));

                                startActivity(intent);
                            }
                            String cat="";
                            if(text.equals(settings.getGtun())||text.equals(settings.getGeneraln())||text.equals(settings.getLibraryn())||text.equals(settings.getComputern())||text.equals(settings.getCiviln())
                                    ||text.equals(settings.getMechanicaln())||text.equals(settings.getElectricaln())||text.equals(settings.getEcn())||text.equals(settings.getCddmn())) {
                                cat = text;

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

            extraData.put("ecategory",text);



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






