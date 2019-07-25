package com.alexc.hacktothefuture;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private List<Item> itemlist=new ArrayList<>();
    public CustomAdapter(@NonNull Context context, @LayoutRes ArrayList<Item> list){
        super(context,0,list);
        mContext=context;
        itemlist=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);
        Item currentItem=itemlist.get(position);

        ImageView image=(ImageView)listItem.findViewById(R.id.imageC);


        Picasso.with(mContext).load(currentItem.getmImageUriS()).resize(135,85).centerInside().into(image);

        TextView name = (TextView) listItem.findViewById(R.id.title_text);
        name.setText(currentItem.getmName());

        return listItem;
    }
}
