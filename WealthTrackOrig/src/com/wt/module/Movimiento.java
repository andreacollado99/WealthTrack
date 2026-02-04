package com.wt.module;

public class Movimiento {
	public static String[] TIPOS = {"Ingreso","Gasto"};
	
	private int idMovimiento;
    private String tipo;
    private String nombreMovimiento;
    private double importe;
    private String email;


    public Movimiento(int idMovimiento, String tipo, String nombreMovimiento, double importe, String email) {
		this.idMovimiento = idMovimiento;
		this.tipo = tipo;
		this.nombreMovimiento = nombreMovimiento;
		this.importe = importe;
		this.email = email;
	}
    public Movimiento(String tipo, String nombreMovimiento, double importe, String email) {
		
		this.tipo = tipo;
		this.nombreMovimiento = nombreMovimiento;
		this.importe = importe;
		this.email = email;
		idMovimiento = 0;
	}
    
    public Movimiento(String tipo, String nombreMovimiento, double importe) {
		this.tipo = tipo;
		this.nombreMovimiento = nombreMovimiento;
		this.importe = importe;
		email = null;
		idMovimiento = 0;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreMovimiento() {
		return nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


    
}
