package com.alexc.hacktothefuture;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ScrollMenu extends AppCompatActivity {

    Button back_button,settings,about,account,logout,themes,add_contest;
    TextView logmessage;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_scroll_menu);
        back_button=findViewById(R.id.back_button);
        settings=findViewById(R.id.settings);
        about=findViewById(R.id.about);
        account=findViewById(R.id.account);
        logout=findViewById(R.id.logout);
        themes=findViewById(R.id.themes);
        add_contest=findViewById(R.id.addcontest);
        logmessage=findViewById(R.id.logmessage);
        //back_button.setBackgroundResource(R.drawable.ic_backbutton);

        add_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://web-contester.herokuapp.com"));
                startActivity(viewIntent);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollMenu.this,MainActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(ScrollMenu.this,MainActivity.class));
                finish();

            }
        });
    }
}
