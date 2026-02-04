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

public class JPInversiones extends JPanel {

	public static final String NOM_TABLA = "Criptomonedas";
	public static final String NOM_COL_ID_CRIPTO = "IdCripto";
	public static final String NOM_COL_NOMBRE = "NombreCripto";
	public static final String NOM_COL_IMPORTE_CRIPTO = "ImporteCripto";
	public static final String NOM_COL_IMPORTE_EURO = "ImporteEuro";

	// private TablaValorCripto tbc;
	// private ArrayList<ValorCripto> listaValoresTabla;
	private DefaultComboBoxModel<String> dtmCmb;
	private DefaultListModel<Cripto> dtmList;
	private DefaultTableModel dtmTblMdl;
	private JComboBox cmbListaCripto;
	private JTextField textImporte;
	private JButton btnAniadir;
	private JList listMisCriptos;
	private JLabel lblInversion;
	private JPanel panelFondo;
	private JLabel lblFondo2;
	private JButton btnEliminarCripto;
	private JScrollPane scrlpnTablaCripto;
	private JTable tblCriptos;
	private JButton btnGuardarModifCripto;
	private JButton btnCancelarModifCripo;
	private JLabel lblListaCripto;
	private JButton btnModificarCripto;

	public JPInversiones() {
		// Inicializar la conexión con la base de datos y cargar los datos
		// tbc = new TablaValorCripto();
		// listaValoresTabla = tbc.consultarValorCripto();
		// cargarLista();
		initComponents();
	}

	public void cargarLista(ArrayList<ValorCripto> listaValoresTabla) {
		dtmCmb = new DefaultComboBoxModel<String>();
		for (ValorCripto ValorCripto : listaValoresTabla) {
			dtmCmb.addElement(ValorCripto.getNombre());
		}
		cmbListaCripto.setModel(dtmCmb);
	}

	private void initComponents() {
		setLayout(null);
		setBackground(new Color(175, 238, 238));
		setSize(HomeP.ANCHO - 60, HomeP.ALTO - 120);

		JLabel lblNombCripto = new JLabel("Selecciona una Criptomoneda:");
		lblNombCripto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombCripto.setBounds(54, 35, 228, 28);
		add(lblNombCripto);

		cmbListaCripto = new JComboBox();
		cmbListaCripto.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmbListaCripto.setBounds(264, 35, 177, 28);
		add(cmbListaCripto);

		JLabel lblImporte = new JLabel("Importe €:");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImporte.setBounds(57, 90, 177, 28);
		add(lblImporte);

		textImporte = new JTextField();
		textImporte.setFont(new Font("Tahoma", Font.BOLD, 13));
		textImporte.setBounds(264, 90, 177, 28);
		add(textImporte);
		textImporte.setColumns(10);

		btnAniadir = new JButton("Añadir");
		btnAniadir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAniadir.setBounds(533, 55, 146, 28);
		add(btnAniadir);

		scrlpnTablaCripto = new JScrollPane();
		scrlpnTablaCripto.setBounds(10, 168, 497, 249);
		add(scrlpnTablaCripto);

		tblCriptos = new JTable();
		tblCriptos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlpnTablaCripto.setViewportView(tblCriptos);

		configurarTabla();

		/*
		 * listMisCriptos = new JList(); listMisCriptos.setBounds(30, 188, 479, 224);
		 * add(listMisCriptos);
		 */

		btnEliminarCripto = new JButton("Eliminar Cripto");
		btnEliminarCripto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminarCripto.setBounds(519, 272, 219, 28);
		add(btnEliminarCripto);

		lblInversion = new JLabel("");
		lblInversion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInversion.setBounds(30, 423, 750, 33);
		add(lblInversion);

		btnModificarCripto = new JButton("Modificar");
		btnModificarCripto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModificarCripto.setBounds(519, 207, 219, 28);
		add(btnModificarCripto);

		btnGuardarModifCripto = new JButton("Guardar");
		btnGuardarModifCripto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardarModifCripto.setBounds(33, 286, 219, 28);
		btnGuardarModifCripto.setVisible(false);
		add(btnGuardarModifCripto);

		btnCancelarModifCripo = new JButton("Cancelar");
		btnCancelarModifCripo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancelarModifCripo.setBounds(398, 286, 219, 28);
		btnCancelarModifCripo.setVisible(false);
		add(btnCancelarModifCripo);

		lblListaCripto = new JLabel("Lista Criptomonedas Registradas");
		lblListaCripto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListaCripto.setBounds(10, 144, 319, 28);
		add(lblListaCripto);

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

		dtmTblMdl.addColumn(NOM_COL_ID_CRIPTO);
		dtmTblMdl.addColumn(NOM_COL_NOMBRE);
		dtmTblMdl.addColumn(NOM_COL_IMPORTE_CRIPTO);
		dtmTblMdl.addColumn(NOM_COL_IMPORTE_EURO);

		tblCriptos.setModel(dtmTblMdl);

		tblCriptos.getColumn(NOM_COL_ID_CRIPTO).setPreferredWidth(60);
		tblCriptos.getColumn(NOM_COL_NOMBRE).setPreferredWidth(60);
		tblCriptos.getColumn(NOM_COL_IMPORTE_CRIPTO).setPreferredWidth(60);
		tblCriptos.getColumn(NOM_COL_IMPORTE_EURO).setPreferredWidth(60);

		/*
		 * DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 * centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 * 
		 * for (int i = 0; i < tblCriptos.getColumnCount(); i++) {
		 * tblCriptos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); }
		 */

	}

	public void ConsultarMisCriptos(ArrayList<Cripto> listaCriptos) {
		double totalInvertido = 0;
		dtmTblMdl.setRowCount(0); // limpia la tabla, vacia lo que tenga
		Object[] fila = new Object[4];

		for (Cripto cripto : listaCriptos) {
			fila[0] = cripto.getIdCripto();
			fila[1] = cripto.getNombreCripto();
			fila[2] = cripto.getImporteEnCripto();
			fila[3] = cripto.getImporteEnEuro() + " €";

			dtmTblMdl.addRow(fila);
			totalInvertido += cripto.getImporteEnEuro();

		}
		lblInversion.setText("Actualmente tienes " + totalInvertido + "€ invertidos");
		tblCriptos.revalidate();
		tblCriptos.repaint();

	}

	public JButton getBtnAniadir() {
		return btnAniadir;
	}

	public JButton getBtnEliminarCripto() {
		return btnEliminarCripto;
	}

	public JButton getBtnGuardarModifCripto() {
		return btnGuardarModifCripto;
	}

	public JButton getBtnCancelarModifCripo() {
		return btnCancelarModifCripo;
	}

	public JButton getBtnModificarCripto() {
		return btnModificarCripto;
	}

	public void setControlador(ControlLogin cl) {
		btnAniadir.addActionListener(cl);
		btnEliminarCripto.addActionListener(cl);
		btnGuardarModifCripto.addActionListener(cl);
		btnCancelarModifCripo.addActionListener(cl);
		btnModificarCripto.addActionListener(cl);
	}

	public Cripto obtenerDatos() {
		Cripto cripto = null;
		String nombre = (String) cmbListaCripto.getSelectedItem();
		String textoImporte = textImporte.getText().trim().replace(",", ".");
		; // Elimina espacios en blanco

		try {
			if (textoImporte.isEmpty()) {
				mostrarMensajeError("Debe ingresar un importe.");
			} else {
				double importe = Double.parseDouble(textoImporte);
				if (importe <= 0) {
					mostrarMensajeError("El importe debe ser un número válido y mayor a 0.");
				} else {
					cripto = new Cripto(nombre, importe);
				}
			}
		} catch (NumberFormatException e) {
			mostrarMensajeError("Ingrese un número válido en el importe.");
		}

		return cripto;
	}

	public Double obtenerTotalInvertido(ArrayList<Cripto> listaCriptos) {
		double totalInvertido = 0;

		for (Cripto cripto : listaCriptos) {

			totalInvertido += cripto.getImporteEnEuro();

		}

		return totalInvertido;
	}

	public void limpiarDatos() {
		cmbListaCripto.setSelectedIndex(0);
		textImporte.setText("");

	}

	public void mostrarMensajeError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);

	}

	public int eliminarCriptoTabla() {
		return tblCriptos.getSelectedRow();
	}

	public void vaciarTabla() {
		dtmTblMdl.setRowCount(0);

	}

	public void pedirModif(boolean activacion) {

		btnAniadir.setVisible(!activacion);

		btnEliminarCripto.setVisible(!activacion);

		scrlpnTablaCripto.setVisible(!activacion);

		btnModificarCripto.setVisible(!activacion);

		lblInversion.setVisible(!activacion);

		lblListaCripto.setVisible(!activacion);

		btnGuardarModifCripto.setVisible(activacion);

		btnCancelarModifCripo.setVisible(activacion);

		if (activacion == true) {
			btnAniadir.disable();
			btnEliminarCripto.disable();
			btnModificarCripto.disable();
			cmbListaCripto.disable();
		} else {
			btnAniadir.enable();
			btnEliminarCripto.enable();
			btnModificarCripto.enable();
			cmbListaCripto.enable();
		}

	}

	public DefaultTableModel getDtmTblMdl() {
		return dtmTblMdl;
	}

	public String getTextImporte() {
		return textImporte.getText();
	}

	public JTable getTblCriptos() {
		return tblCriptos;
	}

	public void setCmbListaCripto(String nomCripto) {
		cmbListaCripto.setSelectedItem(nomCripto);
	}

}
