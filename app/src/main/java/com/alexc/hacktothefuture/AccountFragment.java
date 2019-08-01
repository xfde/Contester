package com.alexc.hacktothefuture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AccountFragment extends Fragment {
    Toolbar toolbar;
    Typeface myty;
    TextView account;
    Button deleteUs,resetPass,changeUser;
    RadioButton rb1,rb2,rb3,rb4;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    RadioGroup rg1,rg2;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final DatabaseReference reference=database.getReference("Users");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_account, null);
        toolbar=view.findViewById(R.id.toolbar);
        account=view.findViewById(R.id.acc_text);
        resetPass=view.findViewById(R.id.resetPass);
        deleteUs=view.findViewById(R.id.deleteUser);
        changeUser=view.findViewById(R.id.chUsername);
        rg1=view.findViewById(R.id.rg1);
        rg2=view.findViewById(R.id.rg2);
        myty= Typeface.createFromAsset(getActivity().getAssets(),"fonts/ProximaNova-Regular.otf");
        account.setTypeface(myty);
        rb1=view.findViewById(R.id.First_theme);
        rb2=view.findViewById(R.id.Second_theme);
        rb3=view.findViewById(R.id.Third_theme);
        rb4=view.findViewById(R.id.Fourth_theme);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("Account_Fragment");

                if(fragment!=null&&fragment.isVisible())
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentViewer,new ControlPanelFragment()).commit();
                }
                //getActivity().onBackPressed();
            }
        });
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"We sent you an email to change your username.",Toast.LENGTH_SHORT).show();
            }
        });
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmailmail = user.getEmail();
                auth.sendPasswordResetEmail(userEmailmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(),"We sent you an email to rest your password.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        deleteUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(),"User account deleted.",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        });
            }
        });
        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
        final int chkId1 = rg1.getCheckedRadioButtonId();
        final int chkId2 = rg2.getCheckedRadioButtonId();




        return view;
    }
    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);
                final int chkId1 = rg1.getCheckedRadioButtonId();
                final int chkId2 = rg2.getCheckedRadioButtonId();
                final int realCheck = chkId1 == -1 ? chkId2 : chkId1;

                final DatabaseReference ref= reference.child(user.getDisplayName());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(realCheck==rb1.getId()){

                            ref.child("profile").setValue("1");
                            //Log.i("Tests",String.valueOf(rb1.getId()));
                        }
                        if(realCheck==rb2.getId()){
                            ref.child("profile").setValue("2");
                           // Log.i("Tests",String.valueOf(rb2.getId()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
                final int chkId1 = rg1.getCheckedRadioButtonId();
                final int chkId2 = rg2.getCheckedRadioButtonId();
                final int realCheck = chkId1 == -1 ? chkId2 : chkId1;
                final DatabaseReference ref= reference.child(user.getDisplayName());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(realCheck==rb3.getId()){

                            ref.child("profile").setValue("3");
                            //Log.i("Tests",String.valueOf(rb3.getId()));
                        }
                        if(realCheck==rb4.getId()){
                            ref.child("profile").setValue("4");
                           // Log.i("Tests",String.valueOf(rb4.getId()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
    };
}