package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class showNotice extends AppCompatActivity {
     TextView  ntitle,ndes;
     ImageView image;
     Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ntitle=(TextView)findViewById(R.id.ntitle);
        ndes=(TextView)findViewById(R.id.ndes);
        image=(ImageView)findViewById(R.id.image);
        mcontext=getApplicationContext();
        Intent i=getIntent();
        final Upload uploadfile;
        uploadfile = (Upload) i.getSerializableExtra("uploadclass");
        ntitle.setText(uploadfile.getTitle());
        ndes.setText(uploadfile.getDes());
        Picasso.with(mcontext)
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
        });
    }
}
