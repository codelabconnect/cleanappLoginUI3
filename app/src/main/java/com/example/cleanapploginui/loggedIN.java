package com.example.cleanapploginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loggedIN extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView welcome;
    Button logout_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_i_n);

        mAuth=FirebaseAuth.getInstance();
        welcome=(TextView)findViewById(R.id.welcome_txt);
        logout_user=(Button)findViewById(R.id.btn_logout);
        FirebaseUser user =mAuth.getCurrentUser();
        String email=user.getEmail();
        welcome.setText("welcome :"+email);

        logout_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(loggedIN.this,LoginActivity.class));
                finish();
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Shared shared=new Shared(getApplicationContext());
//        shared.secondtime();
//    }
}