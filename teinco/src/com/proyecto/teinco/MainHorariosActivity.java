package com.proyecto.teinco;

import java.util.ArrayList;

import com.proyecto.datos.Estudiante;
import com.proyecto.datos.Horario;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TableRow.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainHorariosActivity extends Activity implements OnItemSelectedListener{
    LinearLayout linearLayout;
	TableLayout tabla;
    TableRow fila;
    Spinner spinner;
    BD db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_horarios);
		tabla=(TableLayout) this.findViewById(R.id.tabla);
		this.spinner = (Spinner) this.findViewById(R.id.spinner_dias);
		this.spinner.setOnItemSelectedListener(this);
		
	    
		db = new BD(this);
		TextView tvNombre = (TextView) this.findViewById(R.id.nombre_estudiante);
		TextView tvPrograma = (TextView) this.findViewById(R.id.nombre_programa);
		TextView tvSemestre = (TextView) this.findViewById(R.id.semestre);
		
		Estudiante estudiante = db.getEstudiante();
		tvNombre.setText(estudiante.getNombres()+" "+estudiante.getApellido1()+" "+estudiante.getApellido2());
		tvPrograma.setText(estudiante.getPrograma());
		tvSemestre.setText("Semestre "+estudiante.getSemestre());
	    

		
	}
	public void cargarHorario (int dia)
	{
		
		tabla.removeViews(1, tabla.getChildCount()-1);
	    ArrayList<Horario> horarios = db.getHorario(dia);
	     
	    for (Horario horario:horarios )
	    {
	    	fila = new TableRow(this);
	    	fila.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
	    	
	    	TextView labelTV = new TextView(this);
	        labelTV.setText(horario.getHora());
	        this.setCeldaBackground(labelTV);
	        fila.addView(labelTV);	
			
	        labelTV = new TextView(this);
		        labelTV.setText(horario.getAsignatura());
		        this.setCeldaBackground(labelTV);
		        fila.addView(labelTV);	
				
		        
		        labelTV = new TextView(this);
			        labelTV.setText(horario.getSalon());
			        this.setCeldaBackground(labelTV);
			        fila.addView(labelTV);	
					
			        tabla.addView(fila, new TableLayout.LayoutParams(
		                    LayoutParams.WRAP_CONTENT,
		                    LayoutParams.WRAP_CONTENT));
	    }
	    
	}
    public void setCeldaBackground(TextView labelTV)
    {
    	int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        	labelTV.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.celda_cuerpo));
        	
        } else {
        	labelTV.setBackground(this.getResources().getDrawable(R.drawable.celda_cuerpo));
        }
        labelTV.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        
        
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_horarios, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int dia,
			long arg3) {
		Log.v("spinner","selected");
		this.cargarHorario(dia+1);
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
