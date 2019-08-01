package com.alexc.hacktothefuture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public  class ControlPanelFragment extends Fragment {

    Button back_button,settings,about,account,logout,topics,add_contest;

    TextView logmessage,userId;
    private FirebaseAuth firebaseAuth;
    ImageView profilePic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_control_panel, null);
        firebaseAuth=FirebaseAuth.getInstance();
        back_button=view.findViewById(R.id.back_button);
        settings=view.findViewById(R.id.settings);
        about=view.findViewById(R.id.about);
        account=view.findViewById(R.id.account);
        logout=view.findViewById(R.id.logout);
        topics=view.findViewById(R.id.topics);
        add_contest=view.findViewById(R.id.addcontest);
        logmessage=view.findViewById(R.id.logmessage);
        userId=view.findViewById(R.id.username);
        profilePic= view.findViewById(R.id.imageProfile);

        final DatabaseReference users;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null)
        {
            final String displayName=user.getDisplayName();
            userId.setText(displayName);
            users=database.getReference("Users");

            users.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String key=(String) dataSnapshot.child(displayName).child("profile").getValue();
                    Log.i("Tests",key);
                    if(key.equals("1"))
                    {
                        profilePic.setImageResource(R.drawable.ic_bo2);
                    }
                    if(key.equals("2"))
                    {
                        Log.i("Tests","DONE");
                        profilePic.setImageResource(R.drawable.ic_boy);
                    }
                    if(key.equals("3"))
                    {
                        profilePic.setImageResource(R.drawable.ic_girl1);
                    }
                    if(key.equals("4"))
                    {
                        profilePic.setImageResource(R.drawable.ic_girl2);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment fragment=new SettingsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contentViewer,fragment,"Settings_Fragment");

                transaction.commit();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountFragment fragment=new AccountFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contentViewer,fragment,"Account_Fragment");
                transaction.commit();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment fragment=new AboutFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contentViewer,fragment,"About_Fragment");
                transaction.commit();
            }
        });
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        topics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Coming soon!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
