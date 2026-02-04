package com.wt.module;

public class Cripto {
	
	private int idCripto;
	private String nombreCripto;
	private double importeEnCripto;
	private double importeEnEuro;
	private String emailUsuario;
	
	public Cripto(int idCripto, String nombreCripto, double importeEnCripto, double importeEnEuro, String emailUsuario) {
		this.idCripto = idCripto;
		this.nombreCripto = nombreCripto;
		this.importeEnCripto = importeEnCripto;
		this.importeEnEuro = importeEnEuro;
		this.emailUsuario = emailUsuario;
	}
	
	public Cripto(String nombreCripto,double importeEnEuro) {
		this.nombreCripto = nombreCripto;
		this.importeEnEuro = importeEnEuro;
		idCripto = 0;
		importeEnCripto = 0;
		emailUsuario = null;
	}

	public int getIdCripto() {
		return idCripto;
	}

	public void setIdCripto(int idCripto) {
		this.idCripto = idCripto;
	}
	
	public String getemailUsuario() {
		return emailUsuario;
	}
	

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getNombreCripto() {
		return nombreCripto;
	}

	public void setNombreCripto(String nombreCripto) {
		this.nombreCripto = nombreCripto;
	}

	public double getImporteEnCripto() {
		return importeEnCripto;
	}

	public void setImporteEnCripto(double importeEnCripto) {
		this.importeEnCripto = importeEnCripto;
	}

	public double getImporteEnEuro() {
		return importeEnEuro;
	}

	public void setImporteEnEuro(double importeEnEuro) {
		this.importeEnEuro = importeEnEuro;
	}

	@Override
	public String toString() {
		return "CRIPTO " + nombreCripto.toUpperCase()
		+ "**ID: " + idCripto
		+ "**Importe €: " + importeEnEuro
		+ "**Importe cripto: " + importeEnCripto;
	}
}
