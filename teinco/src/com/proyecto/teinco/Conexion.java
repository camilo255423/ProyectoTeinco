package com.proyecto.teinco;

import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class Conexion extends AsyncTask<String, Void, String>{
	MainActivity actividad;
	public Conexion(MainActivity actividad) {
		super();
		this.actividad = actividad;
	}
	@Override
	protected String doInBackground(String... arg0) {
		JSONParser jsonParser = new JSONParser();
		Log.v("mensaje","doInBack");
		jsonParser.getJSONFromUrl(arg0[0]);
		return jsonParser.jObj.toString();
	}
	
	protected void onPostExecute(String result) {
	this.actividad.getProgressDialog().dismiss();	
	this.actividad.procesar(result)	;
     
	}

}
