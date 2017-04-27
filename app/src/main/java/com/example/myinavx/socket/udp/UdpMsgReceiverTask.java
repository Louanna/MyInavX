package com.example.myinavx.socket.udp;

import android.os.AsyncTask;

import com.example.myinavx.event.Event;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by anna on 2017/4/21.
 */

public class UdpMsgReceiverTask extends AsyncTask<Void, Void, Void> {

    private DatagramSocket socket;
    private String response = "";

    public UdpMsgReceiverTask(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    protected Void doInBackground(Void... params) {

        byte[] buffer = new byte[1024];

        try {
            DatagramPacket datagramPacket = new DatagramPacket(buffer,0,socket.getReceiveBufferSize());
            socket.receive(datagramPacket);

            response = String.valueOf(buffer);

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
