package com.example.gijsdewert.smartcare;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Fields
    private static final String TAG = "USER";
    private Button btnInloggen;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private EditText tbEmail;
    private EditText tbPassword;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI Controls
        tbEmail = findViewById(R.id.tbEmail);
        tbPassword = findViewById(R.id.tbPassword);
        btnInloggen = findViewById(R.id.btnLogin);
        btnInloggen.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);


        //Firebase instance
        mAuth = FirebaseAuth.getInstance();

        //Context
        context = getApplicationContext();
    }

    @Override
    public void onClick(View view) {
        System.out.println("Click");
        switch (view.getId()) {
            case R.id.btnLogin:
                if (!TextUtils.isEmpty(tbEmail.getText().toString()) && !TextUtils.isEmpty(tbPassword.getText().toString())) {
                    mAuth.signInWithEmailAndPassword(tbEmail.getText().toString(), tbPassword.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                        intent.putExtra("email", user.getEmail());
                                        MainActivity.this.startActivity(intent);
                                        Toast toast = Toast.makeText(context, "Welkom " + user.getEmail(), Toast.LENGTH_SHORT);
                                        toast.show();
                                    } else {
                                        Toast toast = Toast.makeText(context, "Verkeerde inloggegevens.", Toast.LENGTH_SHORT);
                                        toast.show();
                                        updateUI(null);
                                    }
                                }
                            });
                }
                else {
                    Toast toast = Toast.makeText(context, "Graag alle velden invullen", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.btnRegister:
                if (!TextUtils.isEmpty(tbEmail.getText().toString()) && !TextUtils.isEmpty(tbPassword.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(tbEmail.getText().toString(), tbPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast toast = Toast.makeText(context, "Uw account is geregistreerd.", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                Toast toast = Toast.makeText(context, "Registreren is niet gelukt.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });
                } else {
                    Toast toast = Toast.makeText(context, "Graag alle velden invullen", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    private void updateUI(Object o) {
    }


}
