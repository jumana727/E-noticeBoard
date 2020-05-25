package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class showNotice extends AppCompatActivity {
     TextView  ntitle,ndes,showdate;
     ImageView image;
     Context mcontext;
     Toolbar toolbar;
     PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ntitle=(TextView)findViewById(R.id.ntitle);
        ndes=(TextView)findViewById(R.id.ndes);
        pdfView=(PDFView)findViewById(R.id.pdfview);
        toolbar=(Toolbar) findViewById(R.id.toolbarshownotice);
        showdate=(TextView)findViewById(R.id.showdate);
        mcontext=getApplicationContext();
        Intent i=getIntent();
        final Upload uploadfile;
        uploadfile = (Upload) i.getSerializableExtra("uploadclass");
        toolbar.setTitle(uploadfile.getEtext()+" Department");
        ntitle.setText(uploadfile.getTitle());
        ndes.setText(uploadfile.getDes());
        showdate.setText(uploadfile.getDate());

       /* Picasso.with(mcontext)
                .load(uploadfile.getMimageurl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse(uploadfile.getMimageurl()),"image/*");
                startActivity(i);
            }
        });*/
        pdfView.setVisibility(View.VISIBLE);
        final InputStream[] input = new InputStream[1];
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint({"WrongThread", "StaticFieldLeak"})
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    input[0] = new URL(uploadfile.getMimageurl()).openStream();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                pdfView.fromStream(input[0])
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .enableDoubletap(false)

                        .load();
            }
        }.execute();
        pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Show_pdf.class);
                i.putExtra("uploadclass",uploadfile);
                startActivity(i);
            }
        });

    }

    }
