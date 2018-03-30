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
	private String slot;
	//private boolean wait;

	Client(String addr, int port, String msg) {
		dstAddress = addr;
		dstPort = port;
		message = msg;
		//wait = true;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		Socket socket;

		//int exitFlag = 0;
			try {
				    socket = new Socket(dstAddress, dstPort);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(message);
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    slot = dataInputStream.readUTF();
                    //wait = false;
                    socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

        return null;
    }

    public String getSlot() {
	    //while(wait){};
		return slot;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
}
