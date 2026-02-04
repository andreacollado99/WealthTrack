package com.wt.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import com.wt.control.ControlLogin;
import com.wt.module.Meta;
import com.wt.module.SesionUsuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JPAniadirMetas extends JPanel {
	
	public static final int ANCHO_PNL = 640;
	public static final int ALTO_PNL = 275;
	public static final String ACT_COM_BTN_ANIADIR = "Añadir";
	private JTextField txtDescripcion;
	private JTextField txtImporte;
	private JButton btnAniadirMeta;
	private JLabel lblFondo3;

	
	public JPAniadirMetas() {
		initComponents();
	}

	private void initComponents() {
		setSize(640, 240);
		setLayout(null);
		
		JLabel lblDescripMeta = new JLabel("Descripción de la meta");
		lblDescripMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescripMeta.setBounds(107, 49, 161, 24);
		add(lblDescripMeta);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(309, 49, 213, 24);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblImporMeta = new JLabel("Importe de la meta (€)");
		lblImporMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImporMeta.setBounds(107, 106, 180, 24);
		add(lblImporMeta);
		
		txtImporte = new JTextField();
		txtImporte.setColumns(10);
		txtImporte.setBounds(309, 106, 213, 24);
		add(txtImporte);
		
		btnAniadirMeta = new JButton(ACT_COM_BTN_ANIADIR);
		btnAniadirMeta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAniadirMeta.setBounds(277, 172, 85, 21);
		add(btnAniadirMeta);
        
        lblFondo3 = new JLabel("");
        lblFondo3.setIcon(new ImageIcon(JPAniadirMetas.class.getResource("/img/fondo.png")));
        lblFondo3.setBounds(-50, -123, 690, 408);
        add(lblFondo3);
	}

	public void setControlador(ControlLogin cl) {
		btnAniadirMeta.addActionListener(cl);
	}

	public Meta obtenerDatos() {
		Meta meta = null;
		String descripcion = txtDescripcion.getText();
	    String importeTexto = txtImporte.getText().replace(",", ".");

	    if (descripcion.isEmpty() || importeTexto.isEmpty()) {
	        mostrarMensajeError("Todos los campos son obligatorios");
	    }
	    try {
	        double importe = Double.parseDouble(importeTexto);
	        meta = new Meta(importe, descripcion);

	    } catch (NumberFormatException e) {
	        mostrarMensajeError("El importe debe ser un número válido.");
	    }
	    return meta;
	}
	
	
	public JButton getBtnAniadirMeta() {
		return btnAniadirMeta;
	}

	public void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
    }

	public void mostrarMensaje(String mensaje) {
		 JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public String obtenerDescripcion() {
		return txtDescripcion.getText();
	}

	public String obtenerImporte() {
		return txtImporte.getText();
	}

	public void limpiarDatos() {
		txtDescripcion.setText("");
		txtImporte.setText("");
		
	}
}

