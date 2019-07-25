package com.alexc.hacktothefuture;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {
    TextView cloudsync,theapp,flexible,app,about,finalT;
    Typeface mytype,typef,myty;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about,null);
        app=view.findViewById(R.id.app);
        flexible=view.findViewById(R.id.flexible);
        toolbar=view.findViewById(R.id.toolbar);
        theapp=view.findViewById(R.id.theapp);
        finalT=view.findViewById(R.id.final_text);
        cloudsync=view.findViewById(R.id.cloudsync);
        about=view.findViewById(R.id.about_text);
        mytype=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quicksand Bold.otf");
        typef=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quicksand Bold Oblique.otf");
        myty=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ProximaNova-Regular.otf");
        about.setTypeface(myty);
        finalT.setTypeface(typef);
        cloudsync.setTypeface(mytype);
        theapp.setTypeface(mytype);
        flexible.setTypeface(typef);
        app.setTypeface(typef);
        app.setBackgroundColor(Color.parseColor("#14D4F4"));
        flexible.setBackgroundColor(Color.parseColor("#14D4F4"));
        finalT.setBackgroundColor(Color.parseColor("#14D4F4"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("About_Fragment");

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
