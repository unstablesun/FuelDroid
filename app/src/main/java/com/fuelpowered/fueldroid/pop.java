package com.fuelpowered.fueldroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by davehards on 16-02-11.
 */
public class pop extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);


        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8));

        Intent intentExtras = getIntent();


        HashMap<String, Object> hMap = (HashMap<String, Object>) intentExtras.getSerializableExtra("hashMap");


        Map<String, Object> metaData = (Map<String, Object>) hMap.get("metadata");
        String _key = "name";
        String eventName = (String) metaData.get(_key);

        boolean achieved = (boolean)hMap.get("achieved");
        String eventId = (String)hMap.get("eventId");
        int score = (int)hMap.get("score");
        boolean authorized = (boolean)hMap.get("authorized");
        boolean joined = (boolean)hMap.get("joined");
        int startTime = (int)hMap.get("startTime");
        int endTime = (int)hMap.get("endTime");
        String state = (String)hMap.get("state");



        ListView listView ;
        listView = (ListView) findViewById(R.id.listViewData);

        String[] values = new String[9];

        int index = 0;

        values[index++] = "eventName = " + eventName;

        values[index++] = "achieved = " + achieved;
        values[index++] = "eventId = " + eventId;
        values[index++] = "score = " + score;
        values[index++] = "authorized = " + authorized;
        values[index++] = "joined = " + joined;
        values[index++] = "startTime = " + startTime;
        values[index++] = "endTime = " + endTime;
        values[index++] = "state = " + state;




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    /*
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
    */

    }


}
