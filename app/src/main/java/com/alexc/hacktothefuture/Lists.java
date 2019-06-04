package com.alexc.hacktothefuture;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
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


    String tip;

    public Lists(String tip) {

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
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    list.add((String) ds.child("name").getValue());
                }
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "on cancel");
            }
        };
        ref.addListenerForSingleValueEvent(eventListener);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    FirstSelect Select=new FirstSelect();
                    FragmentManager fm =getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, Select);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                if(position==1)
                {
                    SecondSelect Select=new SecondSelect();
                    FragmentManager fm =getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, Select);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(position==2)
                {
                    ThirdSelect Select=new ThirdSelect();
                    FragmentManager fm =getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, Select);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

}
