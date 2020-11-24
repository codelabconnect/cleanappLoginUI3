package com.example.cleanapploginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText txtname,txtphone,txtemail,txtpassword,txtconfirmPass;
    CheckBox reg_checkBox;
    ImageButton reg_signup;
    Button reg_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        txtname = (EditText)findViewById(R.id.name);
        txtphone=(EditText)findViewById(R.id.phone);
        txtemail = (EditText)findViewById(R.id.email);
        txtpassword = (EditText)findViewById(R.id.password);
        txtconfirmPass=(EditText)findViewById(R.id.confirmpassword);
        reg_checkBox = (CheckBox)findViewById(R.id.checkbox);
        reg_signup =(ImageButton)findViewById(R.id.signup);
        reg_login = (Button) findViewById(R.id.login);



        reg_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name=txtname.getText().toString().trim();
                final String phoneNo=txtphone.getText().toString().trim();
                String email=txtemail.getText().toString().trim();
                String password=txtpassword.getText().toString().trim();
                String confirmPassword=txtconfirmPass.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(SignUpActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phoneNo)){
                    Toast.makeText(SignUpActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "Please Enter password again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(SignUpActivity.this, "Password To Short", Toast.LENGTH_SHORT).show();
                }

              if(password.equals(confirmPassword)){
                  mAuth.createUserWithEmailAndPassword(email, password)
                          .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()) {
                                      Intent intent =new Intent(getApplicationContext(),verify_phone.class);
                                      intent.putExtra("phoneNo",phoneNo);
                                      startActivity(intent);
                                      Toast.makeText(SignUpActivity.this, "Registration Complete!!!", Toast.LENGTH_SHORT).show();
                                      finish();
                                  } else {
                                      Toast.makeText(SignUpActivity.this, "Authentication Failed!!!", Toast.LENGTH_SHORT).show();
                                  }

                                  // ...
                              }
                          });
              }
            }
        });

        reg_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
