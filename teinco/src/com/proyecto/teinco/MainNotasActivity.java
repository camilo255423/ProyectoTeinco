package com.proyecto.teinco;

import java.util.ArrayList;

import com.proyecto.datos.Asignatura;
import com.proyecto.datos.Estudiante;
import com.proyecto.datos.Horario;
import com.proyecto.datos.Nota;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

@SuppressLint("NewApi")
public class MainNotasActivity extends Activity implements  OnItemSelectedListener{
	ArrayAdapter adaptador;
	ArrayList<Asignatura> asignaturas;
	BD db;
	Spinner spinnerAsignatura;
	Spinner spinnerCorte;
	TableLayout tabla;
    TableRow fila;
    TextView tvDefinitiva;
    TextView tvCorte;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_notas);
		db = new BD(this);
		
		
		TextView tvNombre = (TextView) this.findViewById(R.id.nombre_estudiante);
		TextView tvPrograma = (TextView) this.findViewById(R.id.nombre_programa);
		TextView tvSemestre = (TextView) this.findViewById(R.id.semestre);
		
		Estudiante estudiante = db.getEstudiante();
		tvNombre.setText(estudiante.getNombres()+" "+estudiante.getApellido1()+" "+estudiante.getApellido2());
		tvPrograma.setText(estudiante.getPrograma());
		tvSemestre.setText("Semestre "+estudiante.getSemestre());
		
		asignaturas = db.getAsignaturas();
		// Verifica que haya asignaturas inscritas
		if(asignaturas.size()==0)
		{
			
			Toast toast = Toast.makeText(this, "El usuario no tiene Asignaturas inscritas", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			this.finish();
		}
		
		
		spinnerAsignatura = (Spinner) this.findViewById(R.id.spinner_asignaturas);
		spinnerCorte = (Spinner) this.findViewById(R.id.spinner_corte);
		tabla=(TableLayout) this.findViewById(R.id.tabla);
		tvDefinitiva = (TextView) this.findViewById(R.id.nota_definitiva);
		tvCorte = (TextView) this.findViewById(R.id.nota_corte);
		
		this.adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item, this.getNombresAsignaturas(asignaturas));
		
		spinnerAsignatura.setAdapter(adaptador);
		spinnerAsignatura.setOnItemSelectedListener(this);
		spinnerCorte.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_notas, menu);
		return true;
	}
	private ArrayList<String> getNombresAsignaturas(ArrayList<Asignatura> asignaturas)
	{
		ArrayList<String> nombres = new ArrayList<String>();
		for(Asignatura asignatura:asignaturas)
		{
			nombres.add(asignatura.getAsignatura());
		}
		return nombres;
	}
	private void cargarNotas()
	{
		double notacorte=0;
		double notadefinitiva=0;
		tabla.removeViews(1, tabla.getChildCount()-1);
		int indexAsignatura = this.spinnerAsignatura.getSelectedItemPosition();
		int indexCorte = this.spinnerCorte.getSelectedItemPosition();
	    ArrayList<Nota> notas = db.getNotas(this.asignaturas.get(indexAsignatura).getCodigoAsignatura(), (indexCorte+1)+"");
	 
	    for (Nota nota:notas )
	    {
	    	fila = new TableRow(this);
	    	fila.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
	    	
	    	TextView labelTV = new TextView(this);
	        labelTV.setText(nota.getDescripcion());
	        labelTV.setTextColor(Color.BLACK);
	        this.setCeldaBackground(labelTV);
	        fila.addView(labelTV);	
			
	        labelTV = new TextView(this);
		        labelTV.setText(nota.getNota()+"");
		        labelTV.setTextColor(Color.BLACK);
		        this.setCeldaBackground(labelTV);
		        fila.addView(labelTV);	
				
		        
	
			        tabla.addView(fila, new TableLayout.LayoutParams(
		                    LayoutParams.MATCH_PARENT,
		                    LayoutParams.WRAP_CONTENT));
	    }
	    
	    if((indexCorte+1)==1) 
	    {	
	    
	    notacorte = this.asignaturas.get(indexAsignatura).getNotaCorte1();
	    	
	    }
	    if((indexCorte+1)==2) 
	    {	
	    
	    notacorte = this.asignaturas.get(indexAsignatura).getNotaCorte2();
	    	
	    }
	    if((indexCorte+1)==3) 
	    {	
	    
	    notacorte = this.asignaturas.get(indexAsignatura).getNotaCorte3();
	    	
	    }
	    if((indexCorte+1)==3) 
	    {	
	    
	    notadefinitiva = this.asignaturas.get(indexAsignatura).getDefinitiva();
	    	
	    }
	    if(notacorte<3)
    	{
    		this.tvCorte.setTextColor(Color.RED);
    	}
    	else
    	{
    		this.tvCorte.setTextColor(Color.rgb(0, 129, 45));
    	}
	    if((indexCorte+1)==3) 
	    {	
	    
	    notadefinitiva = this.asignaturas.get(indexAsignatura).getDefinitiva();
	    if(notadefinitiva<3)
    	{
    		this.tvDefinitiva.setTextColor(Color.RED);
    	}
    	else
    	{
    		this.tvDefinitiva.setTextColor(Color.rgb(0, 129, 45));
    	}
	    this.tvDefinitiva.setText(" Definitiva: "+notadefinitiva+" ");	
	    }
	    else
	    {
	    	this.tvDefinitiva.setText("");		
	    }
	    
	    
    this.tvCorte.setText("Corte: "+notacorte+" ");		
	    
	    
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
                LayoutParams.WRAP_CONTENT,0.5f));
        
        
        
        
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		this.cargarNotas();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}





}


