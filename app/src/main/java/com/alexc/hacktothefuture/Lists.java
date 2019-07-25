package com.alexc.hacktothefuture;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

        import java.util.ArrayList;


public class Lists extends Fragment {


    String tip;

    public Lists(String tip) {

        this.tip = tip;
    }

    //Variables
    String mUriS;
    private ListView listView;
    private CustomAdapter mAdapter;
    private  Uri mUri;
    private String name;
    private String key;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    final StorageReference imagesRef= storageRef.child("images");
    //declaring the arraylist
    final ArrayList<Item>itemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_lists, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        //Database Reference
        DatabaseReference database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://contester-6c94d.firebaseio.com/");
        DatabaseReference ref = database.child(tip);


        //importing data from db to array
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    name=(String) ds.child("name").getValue();
                    mUriS=(String)ds.child("url").getValue();
                    key=ds.getKey();
                    itemList.add(new Item(mUriS,name,key,tip));
                }

                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "on cancel");
            }
        };
        ref.addListenerForSingleValueEvent(eventListener);

        //setting the adapter
        mAdapter=new CustomAdapter(getContext(),itemList);
        listView.setAdapter(mAdapter);

        //setting onclicklsitener for each item in array
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0&&position<mAdapter.getCount())
                {

                    Item currentItem = itemList.get(position);
                    SecondSelect Select=new SecondSelect();
                    Bundle bundle = new Bundle();
                    Log.i("Tester",currentItem.getKey());
                    Bundle keyBundle= new Bundle();
                    keyBundle.putString("key",tip+"/"+currentItem.getKey());
                    Bundle parentBundle=new Bundle();
                    parentBundle.putString("parent",tip);
                    bundle.putBundle("keyBundle",keyBundle);
                    bundle.putBundle("parentBundle",parentBundle);
                    //bundle.putBundle("Key",tip+"/"+currentItem.getKey());

                    Select.setArguments(bundle);
                    FragmentManager fm =getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, Select,"selected");
                    fragmentTransaction.commit();

                }
            }
        });

        //return the view of fragment
        return view;
    }

}
