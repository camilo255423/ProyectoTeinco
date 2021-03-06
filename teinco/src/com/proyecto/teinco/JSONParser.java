package com.proyecto.teinco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
 
    public JSONObject getJSONFromUrl(String url,String user, String password) {
    
        // Making HTTP request
        try {
          
        	
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("user", user));
            nameValuePair.add(new BasicNameValuePair("password",password));
     
            
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
           
            
            
            HttpResponse httpResponse = httpClient.execute(httpPost);
            
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();  
            
 
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
        catch (Exception e)
        {
        	Log.v("mensaje",e.getMessage());
        }
         
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
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
            Log.e("mensaje",e.toString());
        }
        catch(Exception e)
        {
        	Log.e("mensaje",e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
}