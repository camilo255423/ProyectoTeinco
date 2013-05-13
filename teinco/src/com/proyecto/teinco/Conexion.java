package com.proyecto.teinco;

import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import com.proyecto.seguridad.Codificador;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Conexion extends AsyncTask<String, Void, JSONObject>{
	MainActivity actividad;
	public Conexion(MainActivity actividad) {
		super();
		this.actividad = actividad;
	}
	@Override
	protected JSONObject doInBackground(String... arg0) {
		JSONParser jsonParser = new JSONParser();
	    
		try {
			jsonParser.getJSONFromUrl(arg0[0],this.actividad.getUser().getText().toString(), 
					Codificador.getEncoded(this.actividad.getPassword().getText().toString(),Codificador.SHA512));
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			Log.v("sha",e.getMessage());
		}
		return jsonParser.jObj;
	}
	
	protected void onPostExecute(JSONObject resultado) {
		this.actividad.getProgressDialog().dismiss();
		try {
			if (resultado.getInt("error")==1)
			{
				Toast toast = Toast.makeText(this.actividad, "Usuario o Contraseña no Válida", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
				toast.show();
				this.actividad.getPassword().setText("");
			}
			else
			{	
			this.actividad.procesar(resultado)	;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.v("JSON",e.getMessage());
		}
     
	}

}
