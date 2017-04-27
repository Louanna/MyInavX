package com.example.myinavx.socket.tcp;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by anna on 2017/4/20.
 */

public class TcpTask extends AsyncTask<Void, Void, Void> {

    private Socket socket;
    private String host;
    private int port;


    public TcpTask(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            socket = new Socket(host, port);
            socket.setKeepAlive(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        new TcpMsgReceiverTask(socket).execute();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        if (null != socket) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        if (null != socket) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onCancelled();
    }
}
