package com.proyecto.datos;

public class Estudiante {
	private String nombres;
	private String apellido1;
	private String apellido2;
	private String semestre;
	private String programa;
	public Estudiante()
	{
		this.nombres = "NN";
		this.apellido1 = "";
		this.apellido2 = "";
		this.semestre = "";
		this.programa = "";
		
	}
	public Estudiante(String nombres, String apellido1, String apellido2,
			String semestre, String programa) {
		super();
		this.nombres = nombres;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.semestre = semestre;
		this.programa = programa;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Override
	public String toString() {
		return "Estudiante [nombres=" + nombres + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", semestre=" + semestre
				+ ", programa=" + programa + "]";
	}
	

}
