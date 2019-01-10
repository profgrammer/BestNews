package com.example.android.bestnews;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
//    param values
    static final String baseUrl = "https://newsapi.org/v2/top-headlines";
    static final String country = "in";
    static final String apikey = "0d383115fa814c959ada88c9360eb5f8";

//    param keys
    static final String countryParam = "country";
    static  final String apiKeyParam = "apiKey";

    public static URL buildUrl(){
        Uri builtUri = Uri.parse(baseUrl).buildUpon().appendQueryParameter(countryParam, country).appendQueryParameter(apiKeyParam, apikey).build();
        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getHTTPResponseFromUrl(URL url) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String ret;
        try{
            InputStream in = connection.getInputStream();

            Scanner s = new Scanner(in);
            s.useDelimiter("//A");
            if(s.hasNext()) return s.next();
            else return null;
        }
        finally {
            connection.disconnect();
        }
    }
}
