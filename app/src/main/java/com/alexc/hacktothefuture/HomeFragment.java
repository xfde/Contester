package com.alexc.hacktothefuture;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {

    ViewFlipper v_flipper;
    ViewFlipper t_flipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, null);
        int images[]={R.drawable.slide1,R.drawable.slide2, R.drawable.slide3};
        String texts[]={"24 Nov la 09:00 _ 25 Nov la 19:00\n\n2NHack se adresează pasionaților de Machine Learning si Neural Networks. Indiferent de limbajul favorit, sunteți invitați să contribuiți la soluții practice, bazate pe ML/NN.","8 Dec la 09:00 _ 9 Dec la 10:00\n\nPregateste-ti echipa si vino la Hackathon.Vei intampina 99 de probleme, dintre care sigur nu dataset-urile.","Astăzi la 10:00 - 15:00\n\nIn ultimii 30 de ani tehnologia WEB a fost revolutionata de mai multe ori. Cea mai importanta evolutie este dezvoltarea Inteligentei Artificiale. Acesta a ajuns in punctul de a putea crea o diferenta in scietatea noasta.Vino la Hackathon si alfa mai multe"};
        v_flipper=(ViewFlipper) view.findViewById(R.id.v_flipper);
        t_flipper=(ViewFlipper) view.findViewById(R.id.t_flipper);
        SpannableString ss= new SpannableString(texts[0]);
        ForegroundColorSpan fcsb=new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(fcsb,0,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        for(int i=0;i<images.length;i++)
        {
            flipperImages(images[i]);
            flipperText(texts[i]);
        }
        return view;
    }
    public void flipperText(String text)
    {
        TextView textView=new TextView(getContext());
        textView.setText(text);
        t_flipper.addView(textView);
        t_flipper.setFlipInterval(4000);
        t_flipper.setAutoStart(true);
    }
    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
}
