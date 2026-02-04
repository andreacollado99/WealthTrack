package com.wt.module;

public class SesionUsuario {

	private int id;
	private String email;
	private String contrasenia;
	private String nombre;

	public SesionUsuario(int id, String email, String contrasenia, String nombre) {
		this.id = id;
		this.email = email;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
