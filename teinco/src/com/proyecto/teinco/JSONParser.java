package com.proyecto.teinco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
 
    public JSONObject getJSONFromUrl(String url) {
    Log.v("mensaje", "JSONFROMURL");
        // Making HTTP request
        try {
            // defaultHttpClient
        	
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            Log.v("mensaje", "POST"); 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.v("mensaje", "response");
            HttpEntity httpEntity = httpResponse.getEntity();
            Log.v("mensaje", "entity");
            is = httpEntity.getContent();  
            Log.v("mensaje", "is"); 
 
        } catch (UnsupportedEncodingException e) {
        	Log.v("mensaje", "ERROR");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
        	Log.v("mensaje", "ERROR");
            e.printStackTrace();
        } catch (IOException e) {
        	Log.v("mensaje", "ERROR");
            e.printStackTrace();
        }
         
        try {
        	Log.v("mensaje", "Buffered");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
            	Log.v("mensaje", "RECIBIENDO");
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
}