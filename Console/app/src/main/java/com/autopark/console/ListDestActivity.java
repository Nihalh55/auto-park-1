package com.autopark.console;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListDestActivity extends AppCompatActivity {

    private ArrayList<Destination> arrayOfdests = null;
    private TextView mTextView;
    private CountDownTimer oneMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dest);

        mTextView = (TextView) findViewById(R.id.timeView);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        final String id = settings.getString(SettingsActivity.KEY_SLOT_ID, "Console");
        final String port = settings.getString(SettingsActivity.KEY_SERVER_PORT, "6060");
        final String serverAddress = settings.getString(SettingsActivity.KEY_SERVER_ADDR, "192.168.12.1");


        oneMinute = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                mTextView.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish() {
                mTextView.setText("Time's Up");
                Client myClient = new Client(serverAddress, Integer.parseInt(port), String.valueOf(1), ListDestActivity.this);
                myClient.execute();
                finish();
            }
        };
        oneMinute.start();

        int N = 10;

        arrayOfdests = Destination.getList(N);
        DestAdapter adapter = new DestAdapter(this, arrayOfdests);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        oneMinute.cancel();
        super.onDestroy();
    }

}
