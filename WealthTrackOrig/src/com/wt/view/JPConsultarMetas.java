package com.wt.view;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.wt.control.ControlLogin;
import com.wt.module.Meta;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JPConsultarMetas extends JPanel {
	
	public static final int ANCHO_PNL = 640;
	public static final int ALTO_PNL = 275;
	private JTable tblMetas;
	private DefaultTableModel dtmTablaMetas;
	private JScrollPane scrollPane;
	private JButton btnModificarMeta;
	private JButton btnEliminarMeta;
	private JButton btnElim;
	private JButton btnGuar;
	
	public JPConsultarMetas() {
		initComponents();
	}

	private void initComponents() {
		setSize(640, 240);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setVisible(false);
		scrollPane.setBounds(33, 10, 574, 181);
		add(scrollPane);
		
		tblMetas = new JTable();
		tblMetas.setOpaque(false);
		scrollPane.setViewportView(tblMetas);
		
		btnModificarMeta = new JButton("Modificar");
		btnModificarMeta.setBounds(108, 201, 157, 21);
		add(btnModificarMeta);
		
		btnEliminarMeta = new JButton("Eliminar");
		btnEliminarMeta.setBounds(367, 201, 157, 21);
		add(btnEliminarMeta);
		
		
		JLabel lblFondo3 = new JLabel("");
		lblFondo3.setOpaque(true);
		lblFondo3.setIcon(new ImageIcon(JPConsultarMetas.class.getResource("/img/fondo.png")));
		lblFondo3.setBounds(-50, -121, 690, 408);
		add(lblFondo3);
		
		
		
		configurarTabla();

	}

	private void configurarTabla() {
		// creación del TableModel
		dtmTablaMetas = new DefaultTableModel() {
					
			// así indicamos que ninguna columna es editable
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
			
			// especificamos el nombre de las columnas	
			dtmTablaMetas.addColumn("NÚMERO DE META");
			dtmTablaMetas.addColumn("DESCRIPCIÓN");
			dtmTablaMetas.addColumn("CANTIDAD IMPORTE");
			dtmTablaMetas.addColumn("PROGRESO ACTUAL");

			
			// asignar el tablemodel a la tabla
			tblMetas.setModel(dtmTablaMetas);
			
			// configurar el tamaño de las columnas
			tblMetas.getColumn("NÚMERO DE META").setPreferredWidth(50);
			
			// aplicar centrado a las columnas
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	        for (int i = 0; i < tblMetas.getColumnCount(); i++) {
	            tblMetas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
	}
	
	public void rellenarTabla(ArrayList<Meta> listaMetas) {
		dtmTablaMetas.setRowCount(0); // limpia la tabla, vacia lo que tenga
		Object[] fila = new Object[4];
		
		for (Meta rest : listaMetas) {
			fila[0] = rest.getMetaId();
			fila[1] = rest.getDescripcion();
			fila[2] = rest.getCantidadObjetivo() + " €";
			fila[3] = rest.getProgresoActual();

			
			dtmTablaMetas.addRow(fila);
			
		}
		
		tblMetas.revalidate();
		tblMetas.repaint();
	}
	
	public void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
    }

	public void mostrarMensaje(String mensaje) {
		 JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void mostrarTabla(boolean ocultar) {
		scrollPane.setVisible(ocultar);
		revalidate();
        repaint();
	}

	public void vaciarTabla() {
		dtmTablaMetas.setRowCount(0);
		
	}

	public JButton getBtnModificarMeta() {
		return btnModificarMeta;
	}

	public JButton getBtnEliminarMeta() {
		return btnEliminarMeta;
	}

	public int eliminarMetaTabla() {
		return tblMetas.getSelectedRow();
	}

	public void setControlador(ControlLogin cl) {
		btnModificarMeta.addActionListener(cl);
		btnEliminarMeta.addActionListener(cl);
	}
	


	public JTable getTblMetas() {
		return tblMetas;
	}

	public DefaultTableModel getDtmTablaMetas() {
		return dtmTablaMetas;
	}

	public JButton getBtnElim() {
		return btnElim;
	}

	public JButton getBtnGuar() {
		return btnGuar;
	}
	
	
	
	
}


