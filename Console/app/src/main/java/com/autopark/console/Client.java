package com.autopark.console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import android.os.AsyncTask;

public class Client extends AsyncTask<Void, Void, Void> {

	private String dstAddress;
	private int dstPort;
	private String message = "";

	Client(String addr, int port, String msg) {
		dstAddress = addr;
		dstPort = port;
		message = msg;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		Socket socket = null;

		//int exitFlag = 0;
			try {
				    socket = new Socket(dstAddress, dstPort);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(message);
                    socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

        return null;
    }

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
}
