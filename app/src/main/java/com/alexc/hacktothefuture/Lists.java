package com.alexc.hacktothefuture;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.GenericTypeIndicator;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.security.Key;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class Lists extends Fragment {

    //Bundle bundle = this.getArguments();
    //String s=bundle.getString("Key");
    String tip;

    public Lists(String tip) {
        // Required empty public constructor
        this.tip = tip;
    }
    ListView listView;
    ArrayList<String>list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://contester-6c94d.firebaseio.com/");
        DatabaseReference ref = database.child(tip);



        View view= inflater.inflate(R.layout.fragment_lists, container, false);
        listView=(ListView)view.findViewById(R.id.listView);

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("test", "on change");
//                    List<String> list = new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                        Log.e("test", "done");
//                        Olimpiada olimpiada = ds.getValue(Olimpiada.class);
                    list.add((String) ds.child("name").getValue());
                }
//                    String value = dataSnapshot.getValue(String.class);
//                    list.add(value);
//                    Log.i("valoare",value.toString());
                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "on cancel");
            }
        };
        Log.e("test", "query firebase");
        ref.addListenerForSingleValueEvent(eventListener);



        return view;
    }

}
