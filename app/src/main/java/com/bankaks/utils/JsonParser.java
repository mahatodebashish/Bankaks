package com.bankaks.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    static int viewType;
    public static int showViewsAccordingtoType(String type, String response) throws JSONException {

        JSONObject jsonObject=new JSONObject(response);
        String result= jsonObject.optString("result");
        JSONObject jsonObject_result= new JSONObject(result);

        return viewType;
    }
}
