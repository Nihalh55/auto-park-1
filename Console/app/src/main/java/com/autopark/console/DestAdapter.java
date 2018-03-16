package com.autopark.console;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gurupunskill on 3/16/18.
 */


public class DestAdapter extends ArrayAdapter<Destination> {

    private Context mContext;

    public DestAdapter(Context context, ArrayList<Destination> destinations) {
        super(context, 0, destinations);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Destination dest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_btn, parent, false);
        }

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(((Activity)mContext));
        final String id = settings.getString(SettingsActivity.KEY_SLOT_ID, "Console");
        final String port = settings.getString(SettingsActivity.KEY_SERVER_PORT, "6060");
        final String serverAddress = settings.getString(SettingsActivity.KEY_SERVER_ADDR, "192.168.12.1");

        Button btn = (Button) convertView.findViewById(R.id.destBtn);
        btn.setText(dest.getname());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, dest.getname(), Toast.LENGTH_SHORT).show();
                Client myClient = new Client(serverAddress, Integer.parseInt(port), String.valueOf(dest.getID()) + ":" + dest.getname());
                myClient.execute();
                ((Activity)mContext).finish();
            }
        });

        return convertView;
    }
}
