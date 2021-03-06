package com.proyecto.teinco;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proyecto.datos.Asignatura;
import com.proyecto.datos.Estudiante;
import com.proyecto.datos.Horario;
import com.proyecto.datos.Nota;

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
	private final String TABLA_ASIGNATURA = "asignatura";
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
		private  final String HORARIO_ASIGNATURA = "nombre";
	//CAMPOS TABLA NOTAS	
		private  final String NOTAS_CODIGO_ASIGNATURA = "codigo_asignatura";
		private  final String NOTAS_ASIGNATURA = "nombre";
		private  final String NOTAS_CORTE = "corte";
		private  final String NOTAS_DESCRIPCION = "descripcion";
		private  final String NOTAS_NOTA = "nota";
		
		//CAMPOS TABLA ASIGNATURA	
				private  final String ASIGNATURA_CODIGO_ASIGNATURA = "codigo_asignatura";
				private  final String ASIGNATURA_ASIGNATURA = "nombre";
				private  final String ASIGNATURA_NOTA_CORTE1 ="nota_corte_1";
				private  final String ASIGNATURA_NOTA_CORTE2 ="nota_corte_2";
				private  final String ASIGNATURA_NOTA_CORTE3 ="nota_corte_3";
				private  final String ASIGNATURA_NOTA_FINAL ="nota_final";
				
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
		        
		        String CREATE_TABLE_ASIGNATURA = "CREATE TABLE " + this.TABLA_ASIGNATURA + "("
		                + this.ASIGNATURA_CODIGO_ASIGNATURA + " TEXT," 
					    + this.ASIGNATURA_ASIGNATURA + " TEXT,"
					    + this.ASIGNATURA_NOTA_CORTE1 + " REAL,"
					    + this.ASIGNATURA_NOTA_CORTE2 + " REAL,"
					    + this.ASIGNATURA_NOTA_CORTE3 + " REAL,"
					    + this.ASIGNATURA_NOTA_FINAL + " REAL"
		                + ")";
		        db.execSQL(CREATE_TABLE_ASIGNATURA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		 // BORRA TABLAS
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_ESTUDIANTE);
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_HORARIO);
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_NOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLA_ASIGNATURA);
        // CREA BASE DE DATOS NUEVAMENTE
        onCreate(db);
		
	}
	public void adicionarEstudiante(JSONObject objeto)
	{
		
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues valores = new ContentValues();
	    try {
			valores.put(this.ESTUDIANTE_APELLIDO1, objeto.getString(this.ESTUDIANTE_APELLIDO1));
			valores.put(this.ESTUDIANTE_APELLIDO2, objeto.getString(this.ESTUDIANTE_APELLIDO2)); 
		    valores.put(this.ESTUDIANTE_NOMBRES, objeto.getString(this.ESTUDIANTE_NOMBRES));
		    valores.put(this.ESTUDIANTE_SEMESTRE, objeto.getString(this.ESTUDIANTE_SEMESTRE));
		    valores.put(this.ESTUDIANTE_PROGRAMA, objeto.getString(this.ESTUDIANTE_PROGRAMA));
		    long filas=db.insert(this.TABLA_ESTUDIANTE, null, valores);
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
		    long filas=db.insert(this.TABLA_HORARIO, null, valores);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	    	Log.v("base",e.getMessage());
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
			    valores.put(this.NOTAS_CORTE, objeto.getString(this.NOTAS_CORTE));
			    valores.put(this.NOTAS_DESCRIPCION, objeto.getString(this.NOTAS_DESCRIPCION));
			    valores.put(this.NOTAS_NOTA, objeto.getString(this.NOTAS_NOTA));
			    long filas=db.insert(this.TABLA_NOTAS, null, valores);
			    
		    } catch (JSONException e) {
				// TODO Auto-generated catch block
		    	Log.v("base",e.getMessage());
			}
		    catch(Exception e){
		    	Log.v("base",e.getMessage());
		    }
	    
	    
	    
	}
	    public void adicionarAsignatura(JSONObject objeto)
			{
				SQLiteDatabase db = this.getWritableDatabase();
				 
			    ContentValues valores = new ContentValues();
			    try {
					valores.put(this.ASIGNATURA_ASIGNATURA, objeto.getString(this.ASIGNATURA_ASIGNATURA));
					valores.put(this.ASIGNATURA_CODIGO_ASIGNATURA, objeto.getString(this.ASIGNATURA_CODIGO_ASIGNATURA)); 
					valores.put(this.ASIGNATURA_NOTA_CORTE1, objeto.getString(this.ASIGNATURA_NOTA_CORTE1));
					valores.put(this.ASIGNATURA_NOTA_CORTE2, objeto.getString(this.ASIGNATURA_NOTA_CORTE2));
					valores.put(this.ASIGNATURA_NOTA_CORTE3, objeto.getString(this.ASIGNATURA_NOTA_CORTE3));
					valores.put(this.ASIGNATURA_NOTA_FINAL, objeto.getString(this.ASIGNATURA_NOTA_FINAL));
					long filas=db.insert(this.TABLA_ASIGNATURA, null, valores);
				    
			    } catch (JSONException e) {
					// TODO Auto-generated catch block
			    	Log.v("base",e.getMessage());
				}
			    catch(Exception e){
			    	Log.v("base",e.getMessage());
			    }
		    
		    
		    
		}
	public void cargar(JSONObject objeto)
	{
		  try {
			  JSONObject estudiante = objeto.getJSONObject("estudiante");
			  JSONArray horario = objeto.getJSONArray("horario");
			  JSONArray asignaturas = objeto.getJSONArray("asignaturas");
			  JSONArray notas = objeto.getJSONArray("notas");
			
			  for(int i=0;i<horario.length();i++ )
			  {
				  this.adicionarHorario(horario.getJSONObject(i));
			  }
			  for(int i=0;i<asignaturas.length();i++ )
			  {
				  this.adicionarAsignatura(asignaturas.getJSONObject(i));
			  }
			  for(int i=0;i<notas.length();i++ )
			  {
				  this.adicionarNotas(notas.getJSONObject(i));
			  }
			  
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
	public ArrayList<Horario> getHorario(int id_dia)
	{
		ArrayList<Horario> horarios = new ArrayList<Horario>();
		String selectQuery = "SELECT  * FROM " + this.TABLA_HORARIO+" WHERE "+this.HORARIO_ID_DIA
		+"="+id_dia+ " ORDER BY "+this.HORARIO_ID_HORA+ " ASC ";
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        
        if (cursor.moveToFirst()) {
            do {
                Horario horario = new Horario();
                horario.setAsignatura(cursor.getString(cursor.getColumnIndex(this.HORARIO_ASIGNATURA)));
                horario.setHora(cursor.getString(cursor.getColumnIndex(this.HORARIO_HORA)));
                horario.setIdDia(cursor.getString(cursor.getColumnIndex(this.HORARIO_ID_DIA)));
                horario.setSalon(cursor.getString(cursor.getColumnIndex(this.HORARIO_SALON)));
                horarios.add(horario);
            } while (cursor.moveToNext());
        }
		
		return horarios;
	}
	public ArrayList<Asignatura> getAsignaturas()
	{
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		String selectQuery = "SELECT  * FROM " + this.TABLA_ASIGNATURA
		+ " ORDER BY "+this.ASIGNATURA_ASIGNATURA+ " ASC ";
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        
        if (cursor.moveToFirst()) {
            do {
                Asignatura asignatura = new Asignatura();
                asignatura.setAsignatura(cursor.getString(cursor.getColumnIndex(this.ASIGNATURA_ASIGNATURA)));
                asignatura.setCodigoAsignatura(cursor.getString(cursor.getColumnIndex(this.ASIGNATURA_CODIGO_ASIGNATURA)));
                asignatura.setNotaCorte1(cursor.getDouble(cursor.getColumnIndex(this.ASIGNATURA_NOTA_CORTE1)));
                asignatura.setNotaCorte2(cursor.getDouble(cursor.getColumnIndex(this.ASIGNATURA_NOTA_CORTE2)));
                asignatura.setNotaCorte3(cursor.getDouble(cursor.getColumnIndex(this.ASIGNATURA_NOTA_CORTE3)));
                asignatura.setDefinitiva(cursor.getDouble(cursor.getColumnIndex(this.ASIGNATURA_NOTA_FINAL)));
                
                asignaturas.add(asignatura);
                
            } while (cursor.moveToNext());
        }
		return asignaturas;
	}
	public ArrayList<Nota> getNotas(String codigoAsignatura, String corte)
	{
		ArrayList<Nota> notas = new ArrayList<Nota>();
		String selectQuery = "SELECT  * FROM " + this.TABLA_NOTAS
		+ " WHERE "+this.NOTAS_CODIGO_ASIGNATURA+" = "+	codigoAsignatura	
		+ " AND "+this.NOTAS_CORTE + " = "+ corte;
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        
        if (cursor.moveToFirst()) {
            do {
                Nota nota = new Nota();
                nota.setAsignatura(cursor.getString(cursor.getColumnIndex(this.NOTAS_ASIGNATURA)));
                nota.setCodigoAsignatura(cursor.getString(cursor.getColumnIndex(this.NOTAS_CODIGO_ASIGNATURA)));
                nota.setCorte(cursor.getString(cursor.getColumnIndex(this.NOTAS_CORTE)));
                nota.setDescripcion(cursor.getString(cursor.getColumnIndex(this.NOTAS_DESCRIPCION)));
                nota.setNota(cursor.getDouble(cursor.getColumnIndex(this.NOTAS_NOTA)));
                notas.add(nota);
                
            } while (cursor.moveToNext());
        }
		
		return notas;
	}

}
