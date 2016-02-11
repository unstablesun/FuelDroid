package com.fuelpowered.fueldroid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import com.fuelpowered.lib.fuelsdk.fuel;
import com.fuelpowered.lib.fuelsdk.fuelbroadcastreceiver;
import com.fuelpowered.lib.fuelsdk.fuelcompete;
import com.fuelpowered.lib.fuelsdk.fuelcompeteui;
import com.fuelpowered.lib.fuelsdk.fuelnotificationtype;
import com.fuelpowered.lib.fuelsdk.fuelorientationtype;
import com.fuelpowered.lib.fuelsdk.fuelbroadcasttype;

import com.fuelpowered.lib.fuelsdk.fueldynamics;
import com.fuelpowered.lib.fuelsdk.fuelignite;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    int ironCount = 0;
    int steelCount = 0;

    private IntentFilter mIntentFilter;
    static final String SENDER_ID = "870194926634";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuel.onCreate(this);

        mIntentFilter = new IntentFilter();

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_VG_LIST.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_VG_ROLLBACK.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_NOTIFICATION_ENABLED.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_NOTIFICATION_DISABLED.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_LOGIN_REQUEST.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_INVITE_REQUEST.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_SHARE_REQUEST.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IMPLICIT_LAUNCH_REQUEST.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_USER_VALUES.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_CHALLENGE_COUNT.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_TOURNAMENT_INFO.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_EXIT.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_MATCH.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_FAIL.toString());

        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_EVENTS.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_LEADERBOARD.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_MISSION.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_QUEST.toString());
        mIntentFilter.addAction(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_JOIN_EVENT.toString());


        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver,
                mIntentFilter);

        //fuel.useSandbox();
        //Math Attack
        fuel.setup("56b3ce2550c68b7d57000ebc", "b0f94600-8c70-ffe5-f4f2-9e0103945ba7", true, true, true);

        //Tarek Test data
        //fuel.setup("5658726350c68b5b240069b8", "a8933de2-3258-2ff0-5041-05bf65a4b54e", true, true, true);

        fuel.instance().setLanguageCode("EN");
        fueldynamics.setup();
        fueldynamics.instance().syncUserValues();

        fuelcompete.setup();
        fuelcompeteui.setup();
        fuelcompeteui.instance().setOrientation(fuelorientationtype.portrait);

        fuelignite.setup();


        button = (Button) findViewById(R.id.buttonLaunch);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.buttonSync);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.buttonSendProgress);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.buttonPopup1);
        button.setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fuel.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fuel.onResume(this);
        //fuelcompeteui.onResume(this);  Deprecated?
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonLaunch:
                fuelcompeteui.instance().launch();
                break;
            case R.id.buttonSync:
                syncEvents();
                break;
            case R.id.buttonSendProgress:
                sendProgress();
                break;
            case R.id.buttonPopup1:
                launchPopup1();
                break;

            default:
                break;
        }
    }

    private void launchPopup1() {

        //pass in bundle
        Intent intentBundle = new Intent(MainActivity.this, pop.class);

        Bundle bundle = new Bundle();
        bundle.putString("title", "Mission Rule");
        bundle.putInt("amount", 1000);

        intentBundle.putExtras(bundle);

        startActivity(intentBundle);
    }

    private void sendProgress() {

        ironCount = 1;

        Map<String, Object> ironMap = new HashMap<String, Object>();
        ironMap.put("value", ironCount);

        Map<String, Object> steelMap = new HashMap<String, Object>();
        steelMap.put("value", ironCount);

        Map<String, Object> progress = new HashMap<String, Object>();
        progress.put("iron", ironMap);//note: the key is the variable name from the mission rule!
        progress.put("steel", steelMap);//note: the key is the variable name from the mission rule!

        List<Object> tags = new ArrayList<Object>();
        tags.add("IronFilter");
        tags.add("SteelFilter");

        fuelignite.instance().sendProgress(progress, tags);

    }


    private void syncEvents() {
        fuelcompete.instance().syncChallengeCounts();

        fuelcompete.instance().syncTournamentInfo();


        List<Object> filter = new ArrayList<Object>();
        filter.add("IronFilter");
        filter.add("SteelFilter");

        fuelignite.instance().getEvents(filter);

        Toast.makeText(getApplicationContext(), "Sync Events", Toast.LENGTH_SHORT).show();

    }

    private void displayMissionEvents(Map<String, Object> mission) {

        TextView textElement;

        Object _key = "id";
        String id = (String) mission.get(_key);

        Map<String, Object> metaData = (Map<String, Object>) mission.get("metadata");
        _key = "name";
        String missionName = (String) metaData.get(_key);
        textElement = (TextView) findViewById(R.id.textField1);
        textElement.setText("name = " + missionName + " : id = " + id);


        List<Map<String, Object>> missionRules = (List<Map<String, Object>>) mission.get("rules");


        for (Map<String, Object> rule : missionRules) {

            //target
            String key = "target";
            String value = (String) rule.get(key);
            textElement = (TextView) findViewById(R.id.textField2);
            textElement.setText("target = " + value);

            //id
            key = "id";
            value = (String) rule.get(key);
            textElement = (TextView) findViewById(R.id.textField3);
            textElement.setText("id = " + value);

            //score
            key = "score";
            int ivalue = (int) rule.get(key);
            textElement = (TextView) findViewById(R.id.textField4);
            textElement.setText("score = " + ivalue);

            //achieved
            key = "achieved";
            boolean bvalue = (boolean) rule.get(key);
            textElement = (TextView) findViewById(R.id.textField5);
            textElement.setText("achieved = " + bvalue);

            //variable
            key = "variable";
            value = (String) rule.get(key);
            textElement = (TextView) findViewById(R.id.textField6);
            textElement.setText("variable = " + value);

            //kind
            //metadata

            //break;//just print the first one
        }
    }


    private fuelbroadcastreceiver mMessageReceiver = new fuelbroadcastreceiver() {

        final Handler handler = new Handler();

        @SuppressWarnings("unchecked")
        @Override
        public void onReceive(Context context, String action, Map<String, Object> data) {
            // gonna get something
            if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_VG_LIST.toString())) {
                Toast.makeText(getApplicationContext(), "Virtual Goods List", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_VG_ROLLBACK.toString())) {
                Toast.makeText(getApplicationContext(), "Challenge Counts", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_NOTIFICATION_ENABLED.toString())) {
                Toast.makeText(getApplicationContext(), "Notification Enabled", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_NOTIFICATION_DISABLED.toString())) {
                Toast.makeText(getApplicationContext(), "Notification Disabled", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_LOGIN_REQUEST.toString())) {
                // call back to say we have done it
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Login Request", Toast.LENGTH_SHORT).show();
                        fuel.instance().sdkSocialLoginCompleted(null);
                    }
                }, 100);
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_INVITE_REQUEST.toString())) {
                // call back to say we have done it
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Invite Request", Toast.LENGTH_SHORT).show();
                        fuel.instance().sdkSocialInviteCompleted();
                    }
                }, 100);
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_SOCIAL_SHARE_REQUEST.toString())) {
                // call back to say we have done it
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Share Request", Toast.LENGTH_SHORT).show();
                        fuel.instance().sdkSocialShareCompleted();
                    }
                }, 100);
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_EXIT.toString())) {

                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();

            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_FAIL.toString())) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_MATCH.toString())) {
                if (data != null) {

                    Random rand = new Random();
                    long rScore = rand.nextInt(50) + 1;

                    // extract the info
                    final String tournamentId = (String) data.get("tournamentID");
                    final String matchId = (String) data.get("matchID");
                    final long score = rScore;
                    // we should play a game here then call the result
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Match Exit", Toast.LENGTH_SHORT).show();
                            Map<String, Object> matchResult = new HashMap<String, Object>();
                            matchResult.put("tournamentID", tournamentId);
                            matchResult.put("matchID", matchId);
                            matchResult.put("score", score);
                            matchResult.put("visualScore", Long.toString(score));

                            fuelcompete.instance().submitMatchResult(matchResult);
                            fuelcompeteui.instance().launch();
                        }
                    }, 100);
                }
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_CHALLENGE_COUNT.toString())) {

                Toast.makeText(getApplicationContext(), "Challenge Counts", Toast.LENGTH_SHORT).show();

            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_COMPETE_TOURNAMENT_INFO.toString())) {
                Toast.makeText(getApplicationContext(), "Tournament Info", Toast.LENGTH_SHORT).show();
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_EVENTS.toString())) {
                Toast.makeText(getApplicationContext(), "Broadcast Events", Toast.LENGTH_SHORT).show();
                // iterate the list and gather the events.
                List<Map<String, Object>> events = (List<Map<String, Object>>) data.get("events");
                Log.i("events", events.toString());
                for (Map<String, Object> event : events) {
                    String activityID = (String) event.get("id");
                    switch ((int) event.get("type")) {
                        case 0:
                            fuelignite.instance().getLeaderBoard(activityID);
                            break;
                        case 1:
                            fuelignite.instance().getMission(activityID);
                            Toast.makeText(getApplicationContext(), "getMission", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            fuelignite.instance().getQuest(activityID);
                            break;
                    }
                }
            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_LEADERBOARD.toString())) {
                Map<String, Object> leaderBoard = (Map<String, Object>) data.get("leaderBoard");
                Log.i("leaderBoard", leaderBoard.toString());
                Toast.makeText(getApplicationContext(), "LeaderBoard", Toast.LENGTH_SHORT).show();

            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_MISSION.toString())) {
                Map<String, Object> mission = (Map<String, Object>) data.get("mission");
                Log.i("broadcast mission", mission.toString());
                //Toast.makeText(getApplicationContext(), mission.toString(), Toast.LENGTH_LONG).show();

                displayMissionEvents(mission);

            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_QUEST.toString())) {
                Map<String, Object> quest = (Map<String, Object>) data.get("quest");
                Log.i("quest", quest.toString());
                Toast.makeText(getApplicationContext(), "Quest", Toast.LENGTH_SHORT).show();

            } else if (action.equals(fuelbroadcasttype.FSDK_BROADCAST_IGNITE_JOIN_EVENT.toString())) {
                Log.i("join event", data.toString());
            }

        }

    };


}