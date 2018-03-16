package com.autopark.console;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;
    private String id;
    private String port;
    private String serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListDestActivity.class);
                startActivity(intent);
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        id = settings.getString(SettingsActivity.KEY_SLOT_ID, "Console");
        port = settings.getString(SettingsActivity.KEY_SERVER_PORT, "6060");
        serverAddress = settings.getString(SettingsActivity.KEY_SERVER_ADDR, "192.168.12.1");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    protected void onResume() {
        super.onResume();
        settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        id = settings.getString (SettingsActivity.KEY_SLOT_ID, "Console");
        port = settings.getString(SettingsActivity.KEY_SERVER_PORT, "6060");
        serverAddress = settings.getString(SettingsActivity.KEY_SERVER_ADDR, "192.168.12.1");
    }

}
