package com.example.textture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText rgUsername,rgEmailID,registerPasswordId,registerConfirmPasswordID;
    TextView signInRedirect;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        rgUsername = findViewById(R.id.registerUsername);
        rgEmailID = findViewById(R.id.registerEmailId);
        registerPasswordId = findViewById(R.id.registerPasswordId);
        registerConfirmPasswordID = findViewById(R.id.registerConfirmPasswordId);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInRedirect = findViewById(R.id.signInRedirect);
        signInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = rgUsername.getText().toString().trim();
                String emailId = rgEmailID.getText().toString().trim();
                String registerPID = registerPasswordId.getText().toString().trim();
                String registerCPID = registerConfirmPasswordID.getText().toString().trim();

                if(username.isEmpty()){
                    rgUsername.setError("Username is empty!");
                }

                if(emailId.isEmpty()){
                    rgEmailID.setError("Email is empty!");
                }

                if(!registerPID.equals(registerCPID)){
                    registerConfirmPasswordID.setError("Password doesn't match");
                }

                else{
                    auth.createUserWithEmailAndPassword(emailId,registerPID).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
//                                task.getException().getMessage();
//                                task.getException().getErrorCode();
                            }
                        }
                    });
                }
//                String registerConfirmPasswordID = registerConfirmPasswordID.get
            }
        });

//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                finish();
//            }
//        });




    }
}