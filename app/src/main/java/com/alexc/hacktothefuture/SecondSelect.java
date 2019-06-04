package com.alexc.hacktothefuture;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


public class SecondSelect extends Fragment {
    ToggleButton toggleButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second_select, null);
        toggleButton =  view.findViewById(R.id.myToggleButton);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.img_star_grey));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.img_star_yellow));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.img_star_grey));
            }
        });
        return view;
    }
}
