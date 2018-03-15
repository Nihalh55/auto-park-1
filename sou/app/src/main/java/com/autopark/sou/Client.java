package com.autopark.sou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
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

				int exitFlag = 0;
				while(exitFlag == 0) {
                    socket = new Socket(dstAddress, dstPort);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    dataOutputStream.writeUTF(message);

                    exitFlag = dataInputStream.readInt();
                    System.out.print(exitFlag);
                    socket.close();
                }

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
