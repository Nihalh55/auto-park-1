package com.autopark.sou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_SERVER_ADDR = "edit_server_address";
    public static final String KEY_SERVER_PORT = "edit_server_port";
    public static final String KEY_SLOT_ID = "edit_slot_id";


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();
    }

}