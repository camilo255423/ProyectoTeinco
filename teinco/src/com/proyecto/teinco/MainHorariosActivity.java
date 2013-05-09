package com.proyecto.teinco;

import java.util.ArrayList;

import com.proyecto.datos.Horario;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainHorariosActivity extends Activity {
    LinearLayout linearLayout;
	TableLayout tabla;
    TableRow fila;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_horarios);
	    BD db = new BD(this);
	    ArrayList<Horario> horarios = db.getHorario(1);
	    tabla=(TableLayout) this.findViewById(R.id.tabla);
	    
	    for (Horario horario:horarios )
	    {
	    	TextView labelTV = new TextView(this);
	        
	        labelTV.setText(horario.getHora());
	        int sdk = android.os.Build.VERSION.SDK_INT;
	        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
	        	labelTV.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.celda_cuerpo));
	        } else {
	        	labelTV.setBackground(this.getResources().getDrawable(R.drawable.celda_cuerpo));
	        }
	        
	        labelTV.setLayoutParams(new LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.WRAP_CONTENT));
		
	    }
		
		tabla.addView(fila);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_horarios, menu);
		return true;
	}

}
