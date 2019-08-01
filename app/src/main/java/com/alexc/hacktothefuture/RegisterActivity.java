package com.alexc.hacktothefuture;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    TextView login;
    private String error;
    String reg;
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
        login =findViewById(R.id.text_view_login);
        reg="Already have an account?\nLogin here";

        login.setText(reg);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(text_email.getText().toString(),text_pass.getText(),text_name.getText())){
                    progressBar.setVisibility(View.VISIBLE);
                    final User user = new User(text_name.getText().toString().trim(),
                            text_email.getText().toString());


                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getUsername()).exists()) {
                                Toast.makeText(RegisterActivity.this, "The Account bound to that email already exist!", Toast.LENGTH_SHORT).show();
                            } else {

                                users.child(user.getUsername()).setValue(user);
                                String reff=user.getUsername()+"profile";
                                users.child(reff).setValue(1);
                                String ref = user.getUsername() + "/favorites/1";
                                users.child(ref).setValue(1);
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    firebaseAuth.createUserWithEmailAndPassword(text_email.getText().toString(), text_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Signed up Failed. Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                userProfile();
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(RegisterActivity.this,LoginActivity.class);

                    finish();
                    startActivity(i);
                }
        });
    }
    private void userProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(text_name.getText().toString().trim()).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }

    }
    boolean check(String email,android.text.Editable pass, android.text.Editable name) {

        if (!isEmailValid(email)){
            error=getString(R.string.error_invalid_email);
            return false;
        }
        if(pass==null){
            error=getString(R.string.error_field_required);
            return false;
        }
        if(pass.toString().length()<6)
        {
            error=getString(R.string.error_invalid_password);
            return false;
        }
        if(name==null){
            error=getString(R.string.error_field_required);
            return false;
        }
        return true;
    }
    boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
