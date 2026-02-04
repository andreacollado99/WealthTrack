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
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JPModificarMeta extends JPanel {
	
	public static final int ANCHO_PNL = 640;
	public static final int ALTO_PNL = 275;
	public static final String ACT_COM_BTN_ANIADIR = "Añadir";
	private JTextField txtDescripcion;
	private JTextField txtImporte;
	private JButton btnGuardarModifMeta;
	private JLabel lblFondo3;
	private JButton btnCancelarModifMeta;
	private JComboBox cmbProgreso;

	
	public JPModificarMeta() {
		initComponents();
	}

	private void initComponents() {
		setSize(640, 240);
		setLayout(null);
		
		JLabel lblDescripMeta = new JLabel("Descripción de la meta");
		lblDescripMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescripMeta.setBounds(106, 23, 161, 24);
		add(lblDescripMeta);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(308, 23, 213, 24);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);
		txtDescripcion.disable();
		
		JLabel lblImporMeta = new JLabel("Importe de la meta (€)");
		lblImporMeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImporMeta.setBounds(106, 80, 180, 24);
		add(lblImporMeta);
		
		txtImporte = new JTextField();
		txtImporte.setColumns(10);
		txtImporte.setBounds(308, 80, 213, 24);
		add(txtImporte);
		
		btnGuardarModifMeta = new JButton("Guardar");
		btnGuardarModifMeta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardarModifMeta.setBounds(158, 192, 110, 21);
		add(btnGuardarModifMeta);
        
        JLabel lblProgresoActual = new JLabel("Progreso Actual");
        lblProgresoActual.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblProgresoActual.setBounds(106, 135, 180, 24);
        add(lblProgresoActual);
        
        cmbProgreso = new JComboBox();
        cmbProgreso.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cmbProgreso.setModel(new DefaultComboBoxModel(Meta.PROGRESO));
        cmbProgreso.setBounds(308, 138, 117, 21);
        add(cmbProgreso);
        btnCancelarModifMeta = new JButton("Cancelar");
        btnCancelarModifMeta.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancelarModifMeta.setBounds(333, 193, 110, 21);
        add(btnCancelarModifMeta);
        
        lblFondo3 = new JLabel("");
        lblFondo3.setIcon(new ImageIcon(JPModificarMeta.class.getResource("/img/fondo.png")));
        lblFondo3.setBounds(-50, -123, 690, 408);
        add(lblFondo3);
        

	}

	public void setControlador(ControlLogin cl) {
		btnGuardarModifMeta.addActionListener(cl);
		btnCancelarModifMeta.addActionListener(cl);
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
	
	
	public JButton getBtnGuardarModifMeta() {
		return btnGuardarModifMeta;
	}
	
	

	public JButton getBtnCancelarModifMeta() {
		return btnCancelarModifMeta;
	}

	public void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
    }

	public void mostrarMensaje(String mensaje) {
		 JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		
	}

	
	public String obtenerProgreso() {
		return (String) cmbProgreso.getSelectedItem();
	}

	public String obtenerImporte() {
		return txtImporte.getText().trim();
	}

	public void limpiarDatos() {
		txtDescripcion.setText("");
		txtImporte.setText("");
		
	}

	public void cargarModificacion(String descripcion,double importe, String progreso) {
		txtDescripcion.setText(descripcion);
		txtImporte.setText(""+importe);
		cmbProgreso.setSelectedItem(progreso);
		
		
	}

	public void activacionProgreso(boolean b) {
		if (b) {
			cmbProgreso.enable();
		}else {
			cmbProgreso.disable();
		}
		
	}
}

