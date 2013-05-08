package com.proyecto.teinco;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proyecto.datos.Estudiante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD extends SQLiteOpenHelper {
	//BASE DE DATOS
	private static final String NOMBRE_BD = "teinco";
	private static final int VERSION_BD = 1;
	//TABLAS
	private final String TABLA_ESTUDIANTE = "estudiante";
	private final String TABLA_HORARIO = "horario";
	private final String TABLA_NOTAS = "notas";
	//CAMPOS TABLA ESTUDIANTE
	private final String ESTUDIANTE_NOMBRES = "nombres"; 
	private final String ESTUDIANTE_APELLIDO1 = "apellido1";
	private final String ESTUDIANTE_APELLIDO2 = "apellido2"; 
	private final String ESTUDIANTE_PROGRAMA = "programa";
	private final String ESTUDIANTE_SEMESTRE = "semestre";
	
	//CAMPOS TABLA HORARIO
		private  final String HORARIO_ID_DIA = "id_dia"; 
		private  final String HORARIO_DIA = "dia";
		private  final String HORARIO_ID_HORA = "id_hora"; 
		private  final String HORARIO_HORA = "hora";
		private  final String HORARIO_SALON = "salon";
		private  final String HORARIO_CODIGO_ASIGNATURA = "codigo_asignatura";
		private  final String HORARIO_ASIGNATURA = "asignatura";
	//CAMPOS TABLA NOTAS	
		private  final String NOTAS_CODIGO_ASIGNATURA = "codigo_asignatura";
		private  final String NOTAS_ASIGNATURA = "asignatura";
		private  final String NOTAS_CORTE = "corte";
		private  final String NOTAS_DESCRIPCION = "descripcion";
		private  final String NOTAS_NOTA = "nota";
	public BD(Context context) {
		super(context, NOMBRE_BD, null, VERSION_BD);
		// TODO Auto-generated constructor stub
		
	}
	public void crear()
	{
		this.onUpgrade(this.getReadableDatabase(), 1, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// CREAR TABLA ESTUDIANTE
		  String CREATE_TABLE_ESTUDIANTE = "CREATE TABLE " + this.TABLA_ESTUDIANTE + "("
	                + this.ESTUDIANTE_APELLIDO1 + " TEXT," 
				    + this.ESTUDIANTE_APELLIDO2 + " TEXT,"
	                + this.ESTUDIANTE_NOMBRES + " TEXT,"
				    + this.ESTUDIANTE_PROGRAMA + " TEXT,"
				    + this.ESTUDIANTE_SEMESTRE + " INTEGER"
	                + ")";
	        db.execSQL(CREATE_TABLE_ESTUDIANTE);
	     // CREAR TABLA HORARIO
			  String CREATE_TABLE_HORARIO = "CREATE TABLE " + this.TABLA_HORARIO + "("
		                + this.HORARIO_ID_DIA + " INTEGER," 
					    + this.HORARIO_DIA + " TEXT,"
		                + this.HORARIO_ID_HORA + " INTEGER,"
					    + this.HORARIO_HORA + " TEXT,"
					    + this.HORARIO_SALON + " TEXT,"
					    + this.HORARIO_CODIGO_ASIGNATURA + " TEXT,"
					    + this.HORARIO_ASIGNATURA + " TEXT"
		                + ")";
		        db.execSQL(CREATE_TABLE_HORARIO);
		        String CREATE_TABLE_NOTAS = "CREATE TABLE " + this.TABLA_NOTAS + "("
		                + this.NOTAS_CODIGO_ASIGNATURA + " TEXT," 
					    + this.NOTAS_ASIGNATURA + " TEXT,"
		                + this.NOTAS_CORTE + " INTEGER,"
					    + this.NOTAS_DESCRIPCION + " TEXT,"
					    + this.NOTAS_NOTA + " REAL"
		                + ")";
		        db.execSQL(CREATE_TABLE_NOTAS);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		 // BORRA TABLAS
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_ESTUDIANTE);
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_HORARIO);
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_NOTAS);
 
        // CREA BASE DE DATOS NUEVAMENTE
        onCreate(db);
		
	}
	public void adicionarEstudiante(JSONObject objeto)
	{
		Log.v("base","adicionar estudiante");
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues valores = new ContentValues();
	    try {
			valores.put(this.ESTUDIANTE_APELLIDO1, objeto.getString(this.ESTUDIANTE_APELLIDO1));
			valores.put(this.ESTUDIANTE_APELLIDO2, objeto.getString(this.ESTUDIANTE_APELLIDO2)); 
		    valores.put(this.ESTUDIANTE_NOMBRES, objeto.getString(this.ESTUDIANTE_NOMBRES));
		    valores.put(this.ESTUDIANTE_SEMESTRE, objeto.getString(this.ESTUDIANTE_SEMESTRE));
		    valores.put(this.ESTUDIANTE_PROGRAMA, objeto.getString(this.ESTUDIANTE_PROGRAMA));
		    Log.v("base","antes de insertar");
		    long filas=db.insert(this.TABLA_ESTUDIANTE, null, valores);
		    Log.v("base","despues de insertar");
		    Log.v("base", "numero filas"+filas);
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
	    	Log.v("base",e.getMessage());
		} 
	    catch(Exception e){
	    	Log.v("base",e.getMessage());
	    }
	    
	}
	public void adicionarHorario(JSONObject objeto)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues valores = new ContentValues();
	    try {
			valores.put(this.HORARIO_ASIGNATURA, objeto.getString(this.HORARIO_ASIGNATURA));
			valores.put(this.HORARIO_CODIGO_ASIGNATURA, objeto.getString(this.HORARIO_CODIGO_ASIGNATURA)); 
		    valores.put(this.HORARIO_DIA, objeto.getString(this.HORARIO_DIA));
		    valores.put(this.HORARIO_HORA, objeto.getString(this.HORARIO_HORA));
		    valores.put(this.HORARIO_ID_DIA, objeto.getString(this.HORARIO_ID_DIA));
		    valores.put(this.HORARIO_ID_HORA, objeto.getString(this.HORARIO_ID_HORA));
		    valores.put(this.HORARIO_SALON, objeto.getString(this.HORARIO_SALON));
		    db.insert(this.TABLA_HORARIO, null, valores);
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch(Exception e){
	    	Log.v("base",e.getMessage());
	    }
	}
	    public void adicionarNotas(JSONObject objeto)
		{
			SQLiteDatabase db = this.getWritableDatabase();
			 
		    ContentValues valores = new ContentValues();
		    try {
				valores.put(this.NOTAS_ASIGNATURA, objeto.getString(this.HORARIO_ASIGNATURA));
				valores.put(this.NOTAS_CODIGO_ASIGNATURA, objeto.getString(this.HORARIO_CODIGO_ASIGNATURA)); 
			    valores.put(this.NOTAS_CORTE, objeto.getString(this.HORARIO_DIA));
			    valores.put(this.NOTAS_DESCRIPCION, objeto.getString(this.HORARIO_HORA));
			    valores.put(this.NOTAS_NOTA, objeto.getString(this.HORARIO_ID_DIA));
			    long filas=db.insert(this.TABLA_NOTAS, null, valores);
			    Log.v("base", "numero filas"+filas);
			    
		    } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    catch(Exception e){
		    	Log.v("base",e.getMessage());
		    }
	    
	    
	    
	}
	public void cargar(JSONObject objeto)
	{
		Log.v("base","cargando json");
		  try {
			  JSONObject estudiante = objeto.getJSONObject("estudiante");
			  this.adicionarEstudiante(estudiante);
		    	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  catch (Exception e) {
				// TODO Auto-generated catch block
				Log.v("base",e.getMessage());
			}
		  
	}
	public Estudiante getEstudiante()
	{
		Estudiante estudiante = new Estudiante();
		String selectQuery = "SELECT  * FROM " + this.TABLA_ESTUDIANTE;
		 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                
                estudiante.setApellido1(cursor.getString(cursor.getColumnIndex(this.ESTUDIANTE_APELLIDO1))); 
                estudiante.setApellido2(cursor.getString(cursor.getColumnIndex(this.ESTUDIANTE_APELLIDO2)));
                estudiante.setNombres(cursor.getString(cursor.getColumnIndex(this.ESTUDIANTE_NOMBRES)));
                estudiante.setPrograma(cursor.getString(cursor.getColumnIndex(this.ESTUDIANTE_PROGRAMA)));
                estudiante.setSemestre(cursor.getString(cursor.getColumnIndex(this.ESTUDIANTE_SEMESTRE)));
                
            } while (cursor.moveToNext());
        }
		
		return estudiante;
	}

}
