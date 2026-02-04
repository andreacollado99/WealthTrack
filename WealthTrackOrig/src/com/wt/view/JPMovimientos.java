package com.wt.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.wt.control.ControlLogin;
import com.wt.model.bbdd.TablaCriptomonedas;
import com.wt.model.bbdd.TablaValorCripto;
import com.wt.module.Cripto;
import com.wt.module.Meta;
import com.wt.module.Movimiento;
import com.wt.module.ValorCripto;

import javax.swing.JButton;
import javax.swing.JTable;

public class JPMovimientos extends JPanel {

	public static final String NOM_TABLA = "Movimientos";
	public static final String NOM_COL_ID_MOV = "Id";
	public static final String NOM_COL_NOMBRE = "Nombre";
	public static final String NOM_COL_IMPORTE = "Importe";
	public static final String NOM_COL_TIPO = "Tipo";
	public static final String NOM_COL_EMAIL = "Email";

	private DefaultTableModel dtmTblMdl;
	private JComboBox<String> cmbTipos;
	private JTextField textImporte;
	private JButton btnAniadir;
	private JPanel panelFondo;
	private JLabel lblFondo2;
	private JButton btnEliminarMov;
	private JScrollPane scrlpnTablaMov;
	private JTable tblMov;
	private JTextField textNombreMov;
	private JButton btnModificarMov;
	private JButton btnGuardarModif;
	private JButton btnCancelar;
	private JLabel lblListaMov;

	public JPMovimientos() {

		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		setBackground(new Color(175, 238, 238));
		setSize(HomeP.ANCHO - 60, HomeP.ALTO - 120);

		btnAniadir = new JButton("Añadir");
		btnAniadir.setBounds(553, 55, 146, 28);
		add(btnAniadir);
		btnAniadir.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnEliminarMov = new JButton("Eliminar Movimiento");
		btnEliminarMov.setBounds(517, 331, 219, 28);
		add(btnEliminarMov);
		btnEliminarMov.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNombreMov = new JLabel("Nombre de Movimiento");
		lblNombreMov.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreMov.setBounds(54, 76, 177, 28);
		add(lblNombreMov);

		textNombreMov = new JTextField();
		textNombreMov.setFont(new Font("Tahoma", Font.BOLD, 13));
		textNombreMov.setColumns(10);
		textNombreMov.setBounds(264, 76, 177, 28);
		add(textNombreMov);

		JLabel lblImporte = new JLabel("Importe €:");
		lblImporte.setBounds(54, 117, 177, 28);
		add(lblImporte);
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 13));

		textImporte = new JTextField();
		textImporte.setBounds(264, 117, 177, 28);
		add(textImporte);
		textImporte.setFont(new Font("Tahoma", Font.BOLD, 13));
		textImporte.setColumns(10);

		JLabel lblTipo = new JLabel("tipo de movimiento:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipo.setBounds(54, 35, 228, 28);
		add(lblTipo);

		cmbTipos = new JComboBox<String>();
		cmbTipos.setModel(new DefaultComboBoxModel<String>(Movimiento.TIPOS));
		cmbTipos.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmbTipos.setBounds(264, 35, 177, 28);
		add(cmbTipos);

		scrlpnTablaMov = new JScrollPane();
		scrlpnTablaMov.setBounds(10, 200, 497, 264);
		add(scrlpnTablaMov);

		tblMov = new JTable();
		tblMov.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlpnTablaMov.setViewportView(tblMov);

		configurarTabla();

		btnModificarMov = new JButton("Modificar");
		btnModificarMov.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModificarMov.setBounds(517, 274, 219, 28);
		add(btnModificarMov);

		lblListaMov = new JLabel("Lista de Movimientos");
		lblListaMov.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListaMov.setBounds(10, 175, 201, 28);
		add(lblListaMov);

		btnGuardarModif = new JButton("Guardar");
		btnGuardarModif.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardarModif.setBounds(110, 286, 219, 28);
		btnGuardarModif.setVisible(false);
		add(btnGuardarModif);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancelar.setBounds(384, 286, 219, 28);
		btnCancelar.setVisible(false);
		add(btnCancelar);

		lblFondo2 = new JLabel("");
		lblFondo2.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
		lblFondo2.setBounds(0, -50, 900, 650);
		add(lblFondo2);

		panelFondo = new JPanel();
		panelFondo.setBounds(0, 0, 900, 600);
		add(panelFondo);
		panelFondo.setLayout(null);

	}

	private void configurarTabla() {
		dtmTblMdl = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtmTblMdl.addColumn(NOM_COL_ID_MOV);
		dtmTblMdl.addColumn(NOM_COL_NOMBRE);
		dtmTblMdl.addColumn(NOM_COL_IMPORTE);
		dtmTblMdl.addColumn(NOM_COL_TIPO);
		dtmTblMdl.addColumn(NOM_COL_EMAIL);

		tblMov.setModel(dtmTblMdl);

		tblMov.getColumn(NOM_COL_ID_MOV).setPreferredWidth(60);
		tblMov.getColumn(NOM_COL_NOMBRE).setPreferredWidth(60);
		tblMov.getColumn(NOM_COL_IMPORTE).setPreferredWidth(60);
		tblMov.getColumn(NOM_COL_TIPO).setPreferredWidth(60);
		tblMov.getColumn(NOM_COL_EMAIL).setPreferredWidth(60);

	}

	public void ConsultarMisMovimientos(ArrayList<Movimiento> listaMovimientos) {

		dtmTblMdl.setRowCount(0); // limpia la tabla, vacia lo que tenga
		Object[] fila = new Object[5];

		for (Movimiento mov : listaMovimientos) {
			fila[0] = mov.getIdMovimiento();
			fila[1] = mov.getNombreMovimiento();
			fila[2] = mov.getImporte() + " €";
			fila[3] = mov.getTipo();
			fila[4] = mov.getEmail();

			dtmTblMdl.addRow(fila);

		}
		tblMov.revalidate();
		tblMov.repaint();

	}

	public JButton getBtnAniadir() {
		return btnAniadir;
	}

	public JButton getBtnEliminarMov() {
		return btnEliminarMov;
	}

	public JButton getBtnModificarMov() {
		return btnModificarMov;
	}

	public JButton getBtnGuardarModif() {
		return btnGuardarModif;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setControlador(ControlLogin cl) {
		btnAniadir.addActionListener(cl);
		btnEliminarMov.addActionListener(cl);
		btnModificarMov.addActionListener(cl);
		btnGuardarModif.addActionListener(cl);
		btnCancelar.addActionListener(cl);

	}

	public Movimiento obtenerDatos() {
		Movimiento mov = null;
		String Tipo = (String) cmbTipos.getSelectedItem();
		String textoNombreMov = textNombreMov.getText(); // Elimina espacios en blanco

		try {
			if (textoNombreMov.isEmpty()) {
				mostrarMensajeError("Debe ingresar un Concepto del movimiento");
			} else {
				if (textImporte.getText().trim().isEmpty()) {
					mostrarMensajeError("Debe ingresar un importe");
				} else {
					double importe = Double.parseDouble(textImporte.getText().trim().replace(",", "."));
					if (importe <= 0) {
						mostrarMensajeError("El importe debe ser un número válido y mayor a 0.");
					} else {
						mov = new Movimiento(Tipo, textoNombreMov, importe);
					}
				}
			}
		} catch (NumberFormatException e) {
			mostrarMensajeError("Ingrese un número válido en el importe.");
		}

		return mov;
	}

	public void limpiarDatos() {
		cmbTipos.setSelectedIndex(0);
		textNombreMov.setText("");
		textImporte.setText("");

	}

	public void mostrarMensajeError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);

	}

	public void vaciarTabla() {
		dtmTblMdl.setRowCount(0);

	}

	public void pedirModif(boolean activacion) {

		btnAniadir.setVisible(!activacion);

		btnEliminarMov.setVisible(!activacion);

		scrlpnTablaMov.setVisible(!activacion);

		btnModificarMov.setVisible(!activacion);

		lblListaMov.setVisible(!activacion);

		btnGuardarModif.setVisible(activacion);

		btnCancelar.setVisible(activacion);

		if (activacion == true) {
			btnAniadir.disable();
			btnEliminarMov.disable();
			btnModificarMov.disable();
			textNombreMov.disable();
			cmbTipos.disable();
		} else {
			btnAniadir.enable();
			btnEliminarMov.enable();
			btnModificarMov.enable();
			textNombreMov.enable();
			cmbTipos.enable();
		}

	}

	public JTable getTblMov() {
		return tblMov;
	}

	public DefaultTableModel getDtmTblMdl() {
		return dtmTblMdl;
	}

	public String getTextImporte() {
		return textImporte.getText();
	}

	public void setTextImporte(String importe) {
		textImporte.setText(importe);
	}

	public void setTextNombreMov(String nombre) {
		textNombreMov.setText(nombre);
	}

	public String getCmbTipos() {
		return (String) cmbTipos.getSelectedItem();
	}

	public void setCmbTipos(String tipo) {
		cmbTipos.setSelectedItem(tipo);;
	}
	
	

	/*public int obtenerModificacion(Movimiento movEditable) {
		textNombreMov.setText(movEditable.getNombreMovimiento());
		return 0;
	}*/
}
