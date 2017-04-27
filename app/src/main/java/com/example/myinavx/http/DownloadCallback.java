package com.example.myinavx.http;

import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.BaseRequest;

/**
 * Created by anna on 2017/4/14.
 */

public abstract class DownloadCallback extends FileCallback {

    /**
     * 请求网络开始前，UI线程
     */
    public void onBefore(BaseRequest request) {
    }

    /**
     * 请求网络结束后，UI线程
     */
    public void onAfter(String t, Exception e) {
    }

    /**
     * 执行下载过程中的进度回调，UI线程
     *
     * @param currentSize  当前下载的字节数
     * @param totalSize    总共需要下载的字节数
     * @param progress     当前下载的进度
     * @param networkSpeed 当前下载的速度 字节/秒
     */
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }
}
