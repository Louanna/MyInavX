package com.example.myinavx.http;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

/**
 * Created by anna on 2017/4/14.
 */

public abstract class UploadCallback extends StringCallback {

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
     * Post执行上传过程中的进度回调，get请求不回调，UI线程
     *
     * @param currentSize  当前上传的字节数
     * @param totalSize    总共需要上传的字节数
     * @param progress     当前上传的进度
     * @param networkSpeed 当前上传的速度 字节/秒
     */
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }

}
