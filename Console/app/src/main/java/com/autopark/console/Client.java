package com.autopark.console;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class Client extends AsyncTask<Void, Void, Void> {

	private String dstAddress;
	private int dstPort;
	private String message = "";
	private String slot;
	private Activity mContext;
	//private boolean wait;

	Client(String addr, int port, String msg, Activity context) {
		dstAddress = addr;
		dstPort = port;
		message = msg;
		mContext = context;
		//wait = true;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		Socket socket;

		//int exitFlag = 0;
			try {
				    socket = new Socket(dstAddress, dstPort);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                    dataOutputStream.writeUTF(message);
                    slot = dataInputStream.readUTF();

                    //wait = false;
                    socket.close();
                    dataInputStream.close();
                    dataOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

        return null;
    }

	@Override
	protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        //slot = "200";
		Intent i = new Intent(((Activity)mContext), DisplaySlot.class);
		i.putExtra("Slot", slot);
		mContext.startActivity(i);
		((Activity)mContext).finish();
	}
}
