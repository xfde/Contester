package com.alexc.hacktothefuture;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ConcursuriFragment extends Fragment implements View.OnClickListener {

    Button concursuri,olimpiade,altele;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_concursuri, null);
        concursuri= (Button)view.findViewById(R.id.buttonConcursuri);
        concursuri.setOnClickListener(this);
        altele=view.findViewById(R.id.buttonAltele);
        altele.setOnClickListener(this);
        olimpiade=view.findViewById(R.id.buttonOlimpiade);
        olimpiade.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAltele:

                Fragment fr = new Lists("altele");
                Bundle bundle = new Bundle();
                bundle.putString("Key","altele");
                fr.setArguments(bundle);
                FragmentManager fm =getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fr);
                fragmentTransaction.commit();
                break;

            case R.id.buttonConcursuri:

                Fragment fr1 = new Lists("concursuri");
                Bundle bundle1=new Bundle();
                bundle1.putString("Key","concursuri");
                fr1.setArguments(bundle1);
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                fragmentTransaction1.replace(R.id.fragment_container, fr1);
                fragmentTransaction1.commit();
                break;

            case R.id.buttonOlimpiade:


                Fragment fr2=new Lists("olimpiade");
                Bundle bundle2=new Bundle();
                bundle2.putString("Key","Olimpiade");
                fr2.setArguments(bundle2);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fr2).commit();

                break;

        }
    }

}
