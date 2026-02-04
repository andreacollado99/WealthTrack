package com.wt.module;

public class Meta {
	
	public static final String[] PROGRESO = {"Pendiente", "Listo para finalizar", "Finalizado"};
	
	private int metaId;
	private int usuarioId;
	private double cantidadObjetivo;
	private String progresoActual;
	private String descripcion;
	
	public Meta() {
		
	}
	
	public Meta(int metaId, int usuarioId, double cantidadObjetivo, String progresoActual, String descripcion) {
		this.metaId = metaId;
		this.usuarioId = usuarioId;
		this.cantidadObjetivo = cantidadObjetivo;
		this.progresoActual = progresoActual;
		this.descripcion = descripcion;
	}
	
	public Meta(double cantidadObjetivo, String descripcion) {

		this.cantidadObjetivo = cantidadObjetivo;
		this.descripcion = descripcion;
		metaId = 0;
		usuarioId = 0;
		progresoActual = PROGRESO[0];
	}

	public int getMetaId() {
		return metaId;
	}

	public void setMetaId(int metaId) {
		this.metaId = metaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public double getCantidadObjetivo() {
		return cantidadObjetivo;
	}

	public void setCantidadObjetivo(double cantidadObjetivo) {
		this.cantidadObjetivo = cantidadObjetivo;
	}

	public String getProgresoActual() {
		return progresoActual;
	}

	public void setProgresoActual(String progresoActual) {
		this.progresoActual = progresoActual;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
