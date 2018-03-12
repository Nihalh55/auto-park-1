package com.autopark.sou;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class Client extends AsyncTask<Void, Void, Void> {

	private String dstAddress;
	private int dstPort;
	private String message = "";
	private static Context context;

	Client(String addr, int port, String msg, Context contxt) {
		dstAddress = addr;
		dstPort = port;
		message = msg;
		context = context;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		Socket socket = null;

		try {

			socket = new Socket(dstAddress, dstPort);
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
}
