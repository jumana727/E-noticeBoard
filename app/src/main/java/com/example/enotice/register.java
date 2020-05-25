package com.example.enotice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText name,eemail,epassword,code;
TextView login;
Button signup;
Spinner spinner2;
DatabaseReference databaseReference;
String text;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name=(EditText)findViewById(R.id.name);

        eemail=(EditText)findViewById(R.id.eemail);
        epassword =(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.esignup);
        login=(TextView)findViewById(R.id.elogin);
        code=(EditText)findViewById(R.id.code);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        databaseReference= FirebaseDatabase.getInstance().getReference("student");
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   registerUser();

                }

            });




            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            });


    }
    public void registerUser() {
        final String fullnmae = name.getText().toString();

        final String email = eemail.getText().toString();
        final String password = epassword.getText().toString();
        final String ecode=code.getText().toString();

        if (TextUtils.isEmpty(fullnmae)) {
            Toast.makeText(register.this, "please enter full name", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(register.this, "please enter email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(register.this, "please enter password", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(ecode)) {
            Toast.makeText(register.this, "please enter code", Toast.LENGTH_SHORT).show();
        }
        if(ecode.equals("enotice")) {
            try {

                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    /* incharge info = new incharge(fullnmae, phoneno, email, password);
                                       firebaseDatabase.getInstance().getReference("student").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(register.this, "registration complete", Toast.LENGTH_SHORT).show();*/
                                    incharge data = new incharge(fullnmae, text, email,
                                            password);
                                    FirebaseDatabase.getInstance().getReference("student")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(register.this, "Regestered Succesfully",
                                                    Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                                            startActivity(intent);


                                        }

                                    });

                                } else {
                                    Toast.makeText(register.this, "registration failed", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            } catch (Exception e) {
                Toast.makeText(register.this, "dcjh", Toast.LENGTH_SHORT).show();
            }
        }
            else{
                Toast.makeText(register.this, "enter correct code", Toast.LENGTH_SHORT).show();
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

}
