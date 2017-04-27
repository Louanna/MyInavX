package com.example.myinavx.socket.udp;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Created by anna on 2017/4/20.
 */

public class UdpTask extends AsyncTask<Void, Void, Void> {

    private DatagramSocket datagramSocket;
    private int port;

    public UdpTask(int port) {
        this.port = port;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        new UdpMsgReceiverTask(datagramSocket).execute();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        if (null != datagramSocket) {
            datagramSocket.close();

        }
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        if (null != datagramSocket) {
            datagramSocket.close();
        }
        super.onCancelled();
    }
}
