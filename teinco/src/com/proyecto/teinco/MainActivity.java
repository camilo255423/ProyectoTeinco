package com.proyecto.teinco;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressDialog progressDialog;
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
		Log.v("mensaje","click");
	
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
		Log.v("mensaje","click");
		c.execute(this.getResources().getString(R.string.servidor));
		
		
	}
	public void procesar(String jsonObject)
	{
		this.progressDialog.dismiss();
		Toast.makeText(this, jsonObject, Toast.LENGTH_LONG).show();
	}

}
