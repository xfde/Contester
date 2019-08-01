package com.alexc.hacktothefuture;

        import android.content.Intent;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private EditText text_email;
    private EditText text_pass;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    Button logButton;
    private String error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logButton = findViewById(R.id.button_sign_in);
        TextView text_view=findViewById(R.id.text_view_register);
        progressBar=findViewById(R.id.progressbar);

        text_email= findViewById(R.id.text_email);
        text_pass= findViewById(R.id.edit_text_password);
        firebaseAuth=FirebaseAuth.getInstance();

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(text_email.getText().toString(),text_pass.getText())){
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(text_email.getText().toString(),text_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();

                            }else{
                                Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                }

            }
        });



        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }


        });

    }
    boolean check(String email,android.text.Editable pass) {

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
        return true;
    }
    boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
