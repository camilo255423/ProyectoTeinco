package com.proyecto.teinco;

import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class Conexion extends AsyncTask<String, Void, JSONObject>{
	MainActivity actividad;
	public Conexion(MainActivity actividad) {
		super();
		this.actividad = actividad;
	}
	@Override
	protected JSONObject doInBackground(String... arg0) {
		JSONParser jsonParser = new JSONParser();
	
		jsonParser.getJSONFromUrl(arg0[0]);
		return jsonParser.jObj;
	}
	
	protected void onPostExecute(JSONObject resultado) {
	
    		
	this.actividad.procesar(resultado)	;
     
	}

}
