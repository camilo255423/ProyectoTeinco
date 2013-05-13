package com.proyecto.teinco;

import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proyecto.datos.Estudiante;
import com.proyecto.seguridad.Codificador;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressDialog progressDialog;
    private EditText user;
    private EditText password;
    
 
	
	public EditText getUser() {
		return user;
	}

	public void setUser(EditText user) {
		this.user = user;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

	public ProgressDialog getProgressDialog() {
		
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.user = (EditText) this.findViewById(R.id.editUser);
		this.password = (EditText) this.findViewById(R.id.editPassword);
	 
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void clickLogin(View view)
	{
		if(this.isNetworkAvailable())
		{	
		this.progressDialog = ProgressDialog.show(this,"Conectando al servidor", "por favor espere...");
		Conexion c = new Conexion(this);
		c.execute(this.getResources().getString(R.string.servidor));
		}
		else
		{
		Toast toast = Toast.makeText(this, "No hay Conexión a internet", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
		
		}
		
		
	}
	public void procesar(JSONObject objeto)
	{
		
	    BD db = new BD(this);
	    this.password.setText("");
	    db.crear();
		db.cargar(objeto);
	    Intent intent = new Intent(this,MenuPrincipalActivity.class);	
	    this.startActivity(intent);
		
	}
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) 
	      getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	   
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	} 

}
