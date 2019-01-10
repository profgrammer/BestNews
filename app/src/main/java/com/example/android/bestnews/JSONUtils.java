package com.example.android.bestnews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

    static String[] getStringArrayFromJSONResponse(String s) throws JSONException{
        JSONObject json = new JSONObject(s);
        JSONArray articles = json.getJSONArray("articles");
        String[] ret = new String[articles.length()];
        for(int i = 0;i < articles.length();i++){
            JSONObject article = articles.getJSONObject(i);
            ret[i] = article.getString("title");
        }
        return ret;
    }

}
