package com.example.myinavx.xml;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.example.myinavx.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anna on 2017/4/20.
 */

public class XmlManager {

    public static List<Map<String, String>> getData(Context context) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        XmlResourceParser xrp = context.getResources().getXml(R.xml.channels);

        try {
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();
                    if (tagName.equals("item")) {
                        Map<String, String> map = new HashMap<String, String>();
                        String id = xrp.getAttributeValue(null, "id");
                        map.put("id", id);
                        String url = xrp.getAttributeValue(1);
                        map.put("url", url);
                        map.put("name", xrp.nextText());
                        list.add(map);
                    }
                }
                xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
