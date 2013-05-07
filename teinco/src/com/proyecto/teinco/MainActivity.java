package com.proyecto.teinco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proyecto.datos.Estudiante;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressDialog progressDialog;
    private TextView textView;
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
		this.textView = (TextView) this.findViewById(R.id.texto);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void clickLogin(View view)
	{
		this.progressDialog = ProgressDialog.show(this,"Conectando al servidor", "por favor espere...");
		Conexion c = new Conexion(this);
		c.execute(this.getResources().getString(R.string.servidor));
		
		
	}
	public void procesar(JSONObject objeto)
	{
		this.progressDialog.dismiss();
	    BD db = new BD(this);
		db.cargar(objeto);
		Bundle b = new Bundle();
		
		Estudiante e = db.getEstudiante();
		if(e!=null)
		this.textView.setText(e.toString());
		else	this.textView.setText("Estudiante NULL");
	}

}
