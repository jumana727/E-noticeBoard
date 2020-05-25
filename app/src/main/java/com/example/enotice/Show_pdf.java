package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Show_pdf extends AppCompatActivity {
PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);
        pdfView=(PDFView)findViewById(R.id.pdfview1);
        Intent i=getIntent();
        final Upload uploadfile;
        uploadfile = (Upload) i.getSerializableExtra("uploadclass");
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
    }
}
