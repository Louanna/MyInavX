package com.example.myinavx.socket.tcp;

import android.os.AsyncTask;

import com.example.myinavx.event.Event;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by anna on 2017/4/21.
 */

public class TcpMsgReceiverTask extends AsyncTask<Void, Void, Void> {

    private Socket socket;
    private String response = "";

    public TcpMsgReceiverTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    protected Void doInBackground(Void... params) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];

        int bytesRead;
        try {
            InputStream inputStream = socket.getInputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

//        EventBus.getDefault().post(new Event(response));
        EventBus.getDefault().postSticky(new Event.MessageEvent(response));
    }
}
