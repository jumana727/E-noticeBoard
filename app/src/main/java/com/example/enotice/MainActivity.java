package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
EditText email,password;
TextView esignup,loginasstd,forgotpassword;
Button elogin;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        email=(EditText)findViewById(R.id.eemail);
        password=(EditText)findViewById(R.id.password);
        esignup=(TextView) findViewById(R.id.esignup);
        forgotpassword=(TextView)findViewById(R.id.forgotpassword);
        loginasstd=(TextView) findViewById(R.id.loginasstd);
        elogin=(Button) findViewById(R.id.elogin);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            String mail=user.getEmail();
            finish();
            Intent intent= new Intent(MainActivity.this,incharge_home.class);
            intent.putExtra("facemail",mail);
            startActivity(intent);
        }
        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginIncharge();

            }
        });
        esignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,register.class);
                startActivity(i);
            }
        });
        loginasstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,home.class);
                startActivity(in);

            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),changepass.class);
                startActivity(i);
            }
        });
    }
    public void LoginIncharge() {
        final String eemail, epass;
        eemail = email.getText().toString();
        epass = password.getText().toString();
        if (TextUtils.isEmpty(eemail)) {
            Toast.makeText(MainActivity.this, "enter email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(epass)) {
            Toast.makeText(MainActivity.this, "enter password", Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.signInWithEmailAndPassword(eemail, epass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, incharge_home.class);
                            startActivity(i);


                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
