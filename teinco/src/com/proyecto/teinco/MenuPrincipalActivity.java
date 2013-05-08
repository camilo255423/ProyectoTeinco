package com.proyecto.teinco;

import com.proyecto.datos.Estudiante;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MenuPrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		TextView tvNombre = (TextView) this.findViewById(R.id.nombre_estudiante);
		TextView tvPrograma = (TextView) this.findViewById(R.id.nombre_programa);
		BD db = new BD(this);
		Estudiante estudiante = db.getEstudiante();
		tvNombre.setText(estudiante.getNombres()+" "+estudiante.getApellido1()+" "+estudiante.getApellido2());
		tvPrograma.setText(estudiante.getPrograma());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
		return true;
	}

}
