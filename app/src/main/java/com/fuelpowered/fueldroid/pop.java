package com.fuelpowered.fueldroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.ArrayAdapter;


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

        Bundle bundle = intentExtras.getExtras();

        String title = bundle.getString("title");


        ListView listView ;
        listView = (ListView) findViewById(R.id.listViewData);

        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter ",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "Android Example List View"
        };




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
