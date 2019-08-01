package com.alexc.hacktothefuture;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.squareup.picasso.Picasso;


public class SecondSelect extends Fragment {
    ToggleButton toggleButton;
    Button moreinfo;
    String tip,name,description,uri,link,parent,org;
    TextView descript,organizator,nameC;
    ImageView img;
    Typeface mytype,myty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        tip=getArguments().getBundle("keyBundle").getString("key");
        parent=getArguments().getBundle("parentBundle").getString("parent");



        View view= inflater.inflate(R.layout.fragment_second_select, null);

        moreinfo=view.findViewById(R.id.moreinfo);
        toggleButton =  view.findViewById(R.id.myToggleButton);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.img_star_grey));
        Toolbar toolbar =view.findViewById(R.id.toolbar);
        img=view.findViewById(R.id.imageViewI);
    //TextView name_text =view.findViewById(R.id.text_title);
         descript= view.findViewById(R.id.text_title);
         nameC=view.findViewById(R.id.contest_text);
         organizator=view.findViewById(R.id.text_organizator);
    //Database Ref
        final DatabaseReference users;
        final  FirebaseAuth firebaseAuth;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        users=database.getReference("Users");
        firebaseAuth= FirebaseAuth.getInstance();
        final DatabaseReference reference= database.getReference(tip);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myty=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ProximaNova-Regular.otf");
                mytype= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quicksand Bold.otf");
                description=dataSnapshot.child("descriere").getValue().toString();
                descript.setText(description);
                descript.setTypeface(myty);
                name=dataSnapshot.child("name").getValue().toString();
                nameC.setText(name);
                nameC.setTypeface(mytype);
                org=dataSnapshot.child("organizator").getValue().toString();
                organizator.setText(org);
                uri=dataSnapshot.child("url").getValue().toString();
                link= dataSnapshot.child("link").getValue().toString();
                Picasso.with(getContext()).load(uri).resize(410,200).centerInside().into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("selected");

                if(fragment!=null&&fragment.isVisible())
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Lists(parent)).commit();
                }

            }
        });



        moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent web=new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                startActivity(web);
            }
        });


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.img_star_yellow));
                }
                else
                {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.img_star_grey));
                }
            }
        });


        return view;
    }
}
