package com.example.cleanapploginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText txtemail,txtpassword;
    CheckBox log_checkBox;
    ImageButton log_signin;
    Button log_signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mAuth = FirebaseAuth.getInstance();
        txtemail = (EditText)findViewById(R.id.email);
        txtpassword = (EditText)findViewById(R.id.password);
        log_checkBox = (CheckBox)findViewById(R.id.checkbox);
        log_signin =(ImageButton)findViewById(R.id.login);
        log_signup = (Button) findViewById(R.id.signup);



        log_signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email=txtemail.getText().toString().trim();
                String password=txtpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(LoginActivity.this, "Password To Short", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(LoginActivity.this,loggedIN.class));

//                                    finish();
//                                    Shared shared=new Shared(getApplicationContext());
//                                    shared.secondtime();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Log In failed or User not available!!", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
        log_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(LoginActivity.this,loggedIN.class));
        }

    }
}

