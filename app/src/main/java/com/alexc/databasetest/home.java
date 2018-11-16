package com.alexc.databasetest;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alexc.databasetest.adapter.MyArrayAdapter;
import com.alexc.databasetest.model.MyDataModel;
import com.alexc.databasetest.parser.JSONParser;
import com.alexc.databasetest.util.InternetConnection;
import com.alexc.databasetest.util.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class home extends android.support.v4.app.Fragment {

    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();

        adapter = new MyArrayAdapter(this.getContext(), list);


        // Getting List and Setting List Adapter
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("test", list.get(position).getName());
                Snackbar.make(view, list.get(position).getName() + " => " + list.get(position).getCountry(), Snackbar.LENGTH_LONG).show();
            }
        });

//        Toast toast = Toast.makeText(getApplicationContext(), "Click on FloatingActionButton to Load JSON", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
        if (InternetConnection.checkConnection(getContext())) {
            new home.GetDataTask().execute();
        } else {
            Toast.makeText(getContext(), "Internet Connection Not Available", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    //Data from web
    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jIndex=list.size();
            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Loading...");
            dialog.setMessage("Importing JSON");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JSONParser.getDataFromWeb();

            try {
                if (jsonObject != null) {

                    if(jsonObject.length() > 0) {

                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);



                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {


                                MyDataModel model = new MyDataModel();
                                //Import Data
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String name = innerObject.getString(Keys.KEY_NAME);
                                String country = innerObject.getString(Keys.KEY_COUNTRY);


                                model.setName(name);
                                model.setCountry(country);

                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if(list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                //Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

}
