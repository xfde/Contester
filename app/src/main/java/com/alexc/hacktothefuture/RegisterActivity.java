package com.alexc.hacktothefuture;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.alexc.hacktothefuture.Model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText text_email;
    private EditText text_pass;
    private EditText text_name;

    private  FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

    DatabaseReference users;
    ProgressBar progressBar;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database= FirebaseDatabase.getInstance();
        users=database.getReference("Users");
        text_email=findViewById(R.id.text_email);
        progressBar=findViewById(R.id.progressbar);
        text_pass=findViewById(R.id.edit_text_password);
        text_name=findViewById(R.id.text_name);
        btnSignUp=findViewById(R.id.button_register);
        firebaseAuth=FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final User user = new User(text_name.getText().toString(),
                        text_email.getText().toString());


                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists()) {
                            Toast.makeText(RegisterActivity.this, "The Account bound to that email already exist!", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            users.child(user.getUsername()).setValue(user);
                            String ref=user.getUsername()+"/favorites/1";
                            users.child(ref).setValue(1);
                            Toast.makeText(RegisterActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                firebaseAuth.createUserWithEmailAndPassword(text_email.getText().toString(),text_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(text_name.getText().toString()).build();
                        user.updateProfile(profileUpdates);
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                });

            }
        });
    }
}
