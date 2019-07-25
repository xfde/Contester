package com.alexc.hacktothefuture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public  class ControlPanelFragment extends Fragment {

    Button back_button,settings,about,account,logout,themes,add_contest;

    TextView logmessage,userId;
    private FirebaseAuth firebaseAuth;
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
        themes=view.findViewById(R.id.themes);
        add_contest=view.findViewById(R.id.addcontest);
        logmessage=view.findViewById(R.id.logmessage);
        userId=view.findViewById(R.id.username);
        final DatabaseReference users;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        users=database.getReference("Users");
        if(user!=null)
        {
            userId.setText(user.getDisplayName());
        }
        else{
            userId.setText("Guest");
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
        return view;
    }
}
