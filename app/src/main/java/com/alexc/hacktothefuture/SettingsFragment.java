package com.alexc.hacktothefuture;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public  class SettingsFragment extends Fragment {

    private ToggleButton email1,email2,phone1,phone2;
    Typeface myfont;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_settings, null);
        TextView settings=view.findViewById(R.id.settings_text);
        Toolbar toolbar =view.findViewById(R.id.toolbar);
        Button feedback=view.findViewById(R.id.feedback);
        phone2=view.findViewById(R.id.phone2);
        email2=view.findViewById(R.id.email2);
        phone1=view.findViewById(R.id.phone1);
        email1=view.findViewById(R.id.email1);
        myfont=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ProximaNova-Regular.otf");
        settings.setTypeface(myfont);
        phone1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    phone1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else{
                    phone1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_light)));
                }
            }
        });
        phone2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    phone2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else{
                    phone2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_light)));
                }
            }
        });
        email1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    email1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else{
                    email1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_light)));
                }
            }
        });
        email2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    email2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }
                else{
                    email2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_google_signin_btn_text_light)));
                }
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"info.contester@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("Settings_Fragment");

                SettingsFragment settingsFragment = (SettingsFragment)getFragmentManager().findFragmentByTag("Settings_Fragment");
                if(fragment!=null&&fragment.isVisible())
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentViewer,new ControlPanelFragment()).commit();
                }
                //getActivity().onBackPressed();
            }
        });

        return view;
    }



}
