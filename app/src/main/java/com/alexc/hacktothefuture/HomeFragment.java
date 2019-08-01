package com.alexc.hacktothefuture;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    ViewFlipper v_flipper;
    ViewFlipper t_flipper;
    Button favorites;
    private DatabaseReference mDatabase;
    private Typeface mytype;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, null);
        mDatabase = FirebaseDatabase.getInstance().getReference(getString(R.string.ref_concurs));
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //Raw data
        final ArrayList<String>images=new ArrayList<>();
        final ArrayList<String>texts=new ArrayList<>();

        v_flipper= view.findViewById(R.id.v_flipper);
        t_flipper= view.findViewById(R.id.t_flipper);
        favorites=view.findViewById(R.id.button4);

        final Random rand=new Random();
        final String[] img=new String[4];
        final String[] desc=new String[4];


     //Get contest to flip
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren() ) {
                    images.add((String) ds.child("url").getValue());
                    texts.add((String) ds.child("descriere").getValue());
                }
                for(int i=0;i<4;i++)
                    {
                        int n = rand.nextInt(texts.size());
                        img[i] = images.get(n);
                        desc[i]= texts.get(n);
                        images.remove(n);
                        texts.remove(n);
                        //Log.i(" Tests","Before call");
                        flipperImages(img[i]);
                        flipperText(desc[i]);
                    }
                //Log.i(" Tests","After For");

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    Fragment fr1 = new Favorites();
                    FragmentManager fm1 = getFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                    fragmentTransaction1.replace(R.id.fragment_container, fr1,"favorites");
                    fragmentTransaction1.commit();
                }
                else{
                    Toast.makeText(getContext(),"You need to be logged to access favorites.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        t_flipper.startFlipping();
        v_flipper.startFlipping();
        return view;
    }

    public void flipperText(String text)
    {

        TextView textView=new TextView(getContext());
        mytype= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Quicksand Bold.otf");
        textView.setTypeface(mytype);
        textView.setText(text);
        t_flipper.addView(textView);
        t_flipper.setFlipInterval(5000);
        t_flipper.setAutoStart(true);
    }
    public void flipperImages(String image)
    {
        ImageView imageView=new ImageView(getContext());
        Picasso.with(getContext()).load(image).resize(410,240).centerInside().into(imageView);
        //imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
}
