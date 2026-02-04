package com.wt.module;

public class ValorCripto {
	
	private String nombre;
	private double importe;
	public ValorCripto(String nombre, double importe) {
		this.nombre = nombre;
		this.importe = importe;
	}
	
	public String getNombre() {
		return nombre;
	}

	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
