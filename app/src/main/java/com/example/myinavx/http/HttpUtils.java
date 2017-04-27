package com.example.myinavx.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by anna on 2017/4/14.
 */

public class HttpUtils {

    //get请求
    public static void getRequest(String url, Map<String, String> heads, Map<String, String> params, ContentCallback contentCallback, Object tag) {

        GetRequest getRequest = OkGo.get(url).tag(tag);

        for (String headKey : heads.keySet()) {
            getRequest.headers(headKey, heads.get(headKey));
        }

        getRequest.params(params)
                .execute(contentCallback);
    }

    //post请求
    public static void postString(String url, Map<String, String> heads, String content, ContentCallback contentCallback, Object tag) {
        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.upString(content)
                .execute(contentCallback);
    }

    //post请求
    public static void postJson(String url, Map<String, String> heads, JSONObject jsonObject, ContentCallback contentCallback, Object tag) {
        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.upJson(jsonObject)
                .execute(contentCallback);
    }

    //post请求
    public static void postJsonArray(String url, Map<String, String> heads, JSONArray jsonArray, ContentCallback contentCallback, Object tag) {
        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.upJson(jsonArray)
                .execute(contentCallback);
    }

    //下载图片
    public static void downloadBitmap(String imageUrl, Map<String, String> heads, Map<String, String> params, ImageCallback imageCallback, Object tag) {
        GetRequest getRequest = OkGo.get(imageUrl).tag(tag);

        for (String headKey : heads.keySet()) {
            getRequest.headers(headKey, heads.get(headKey));
        }

        getRequest.params(params)
                .execute(imageCallback);
    }

    //下载文件
    public static void downloadFile(String fileUrl, Map<String, String> heads, Map<String, String> params, DownloadCallback downloadCallback, Object tag) {
        GetRequest getRequest = OkGo.get(fileUrl).tag(tag);

        for (String headKey : heads.keySet()) {
            getRequest.headers(headKey, heads.get(headKey));
        }

        getRequest.params(params)
                .execute(downloadCallback);
    }


    //上传单个文件
    public static void uploadFile(String url, Map<String, String> heads, Map<String, String> params, String fileKey, File file, UploadCallback uploadCallback, Object tag) {

        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.params(params)
                .params(fileKey, file)
                .execute(uploadCallback);
    }

    //上传多个文件(多个文件使用相同的fileKey)
    public static void multiFileUpload(String url, Map<String, String> heads, Map<String, String> params, String fileKey, List<File> files, UploadCallback uploadCallback, Object tag) {
        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.params(params)
                .addFileParams(fileKey, files)
                .execute(uploadCallback);
    }

    //上传多个文件(多个文件使用不同的fileKey)
    public static void multiFileUpload(String url, Map<String, String> heads, Map<String, String> params, Map<String, File> fileMap, UploadCallback uploadCallback, Object tag) {
        PostRequest postRequest = OkGo.post(url).tag(tag);

        for (String headKey : heads.keySet()) {
            postRequest.headers(headKey, heads.get(headKey));
        }

        postRequest.params(params);

        for (String fileKey : fileMap.keySet()) {
            postRequest.params(fileKey, fileMap.get(fileKey));
        }

        postRequest.execute(uploadCallback);
    }

}
