package com.example.myinavx.event;

/**
 * Created by anna on 2017/4/21.
 */

public class Event {

    public static class MessageEvent{
        private String mMsg;

        public MessageEvent(String msg) {
            mMsg = msg;
        }

        public String getMsg() {
            return mMsg;
        }
    }

}
