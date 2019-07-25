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
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AccountFragment extends Fragment {
    Toolbar toolbar;
    Typeface myty;
    TextView account;
    Button deleteUs,resetPass,changeUser;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_account, null);
        toolbar=view.findViewById(R.id.toolbar);
        account=view.findViewById(R.id.acc_text);
        resetPass=view.findViewById(R.id.resetPass);
        deleteUs=view.findViewById(R.id.deleteUser);
        changeUser=view.findViewById(R.id.chUsername);
        myty= Typeface.createFromAsset(getActivity().getAssets(),"fonts/ProximaNova-Regular.otf");
        account.setTypeface(myty);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
        return view;
    }
}