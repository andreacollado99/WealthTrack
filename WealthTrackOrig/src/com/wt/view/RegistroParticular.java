package com.wt.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.wt.control.ControlLogin;
import com.wt.module.particular.Particular;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;

public class RegistroParticular extends JFrame {
	public static final String ACT_COM_BTN_TENGO_CUENTA = "Ya tengo una cuenta";
	public static final String ACT_COM_BTN_REGISTRARME_PAR = "Registrar Particular";
	private static final int MAX_CARAC = 9;

	private JTextField textEmail;
	private JButton btnTengoCuenta;
	private JButton btnRegistrarme;
	private JPasswordField passContrasenia;
	private JLabel lblImagen;
	private final JLabel lblFondo = new JLabel("");
	private JTextField textDni;
	private JTextField textApellido;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblFechaNac;
	private JTextField textTelefono;
	private JComboBox<String> cmbDia;
	private JComboBox<String> cmbMes;
	private JComboBox<String> cmbAnio;
	private JLabel lblApellido;

	public RegistroParticular() {
		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(null);
		ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/img/logo.png"));
		Image img = originalIcon.getImage();
		Image imgRedimensionada = img.getScaledInstance(83, 68, Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imgRedimensionada);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDni.setBounds(234, 188, 46, 25);
		getContentPane().add(lblDni);

		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBackground(Color.WHITE);
		textDni.setBounds(224, 217, 373, 31);
		getContentPane().add(textDni);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(234, 258, 46, 25);
		getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBackground(Color.WHITE);
		textEmail.setBounds(224, 283, 373, 31);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasenia.setBounds(234, 324, 90, 25);
		getContentPane().add(lblContrasenia);

		passContrasenia = new JPasswordField();
		passContrasenia.setBounds(224, 348, 373, 31);
		passContrasenia.setBackground(Color.WHITE);
		getContentPane().add(passContrasenia);

		btnTengoCuenta = new JButton(ACT_COM_BTN_TENGO_CUENTA);
		btnTengoCuenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTengoCuenta.setHorizontalAlignment(SwingConstants.LEFT);
		btnTengoCuenta.setBounds(223, 534, 179, 21);
		btnTengoCuenta.setOpaque(false);
		btnTengoCuenta.setContentAreaFilled(false);
		btnTengoCuenta.setBorderPainted(false);
		btnTengoCuenta.setForeground(Color.BLACK);
		getContentPane().add(btnTengoCuenta);

		btnRegistrarme = new JButton(ACT_COM_BTN_REGISTRARME_PAR);
		btnRegistrarme.setOpaque(true);
		btnRegistrarme.setForeground(Color.BLACK);
		btnRegistrarme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrarme.setFocusPainted(false);
		btnRegistrarme.setBackground(Color.WHITE);
		btnRegistrarme.setBounds(429, 536, 168, 37);
		getContentPane().add(btnRegistrarme);

		JLabel lblNewLabel = new JLabel("WealthTrack");
		lblNewLabel.setBounds(280, 10, 168, 40);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.PLAIN, 29));

		lblImagen = new JLabel("");
		lblImagen.setBounds(458, 0, 83, 68);
		getContentPane().add(lblImagen);
		lblImagen.setIcon(iconoRedimensionado);

		JLabel lblNewLabel_1 = new JLabel("Tu socio Financiero en un Click");
		lblNewLabel_1.setBounds(278, 36, 320, 14);
		getContentPane().add(lblNewLabel_1);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(234, 60, 75, 25);
		getContentPane().add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBackground(Color.WHITE);
		textNombre.setBounds(224, 85, 373, 31);
		getContentPane().add(textNombre);

		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(234, 122, 75, 25);
		getContentPane().add(lblApellido);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBackground(Color.WHITE);
		textApellido.setBounds(224, 147, 373, 31);
		getContentPane().add(textApellido);

		lblFechaNac = new JLabel("Fecha de Nacimiento");
		lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaNac.setBounds(234, 389, 168, 25);
		getContentPane().add(lblFechaNac);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(234, 457, 90, 25);
		getContentPane().add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBackground(Color.WHITE);
		textTelefono.setBounds(224, 482, 373, 31);
		getContentPane().add(textTelefono);

		cmbDia = new JComboBox<>();
		cmbDia.setModel(new DefaultComboBoxModel<>(new String[] { "DD", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31" }));
		cmbDia.setBounds(224, 424, 63, 31);
		getContentPane().add(cmbDia);

		cmbMes = new JComboBox<>();
		cmbMes.setModel(new DefaultComboBoxModel<>(
				new String[] { "MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cmbMes.setBounds(297, 424, 63, 31);
		getContentPane().add(cmbMes);

		cmbAnio = new JComboBox<>();
		cmbAnio.setModel(new DefaultComboBoxModel<>(new String[] { "AAAA", "2007", "2006", "2005", "2004", "2003",
				"2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990",
				"1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977",
				"1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964",
				"1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951",
				"1950" }));
		cmbAnio.setBounds(370, 424, 63, 31);
		getContentPane().add(cmbAnio);

		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblFondo.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
		lblFondo.setBounds(0, 0, 820, 620);
		getContentPane().add(lblFondo);

		setSize(Login.ANCHO - 30, Login.ALTO - 30);

		Dimension dimPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimVentana = new Dimension(Login.ANCHO - 30, Login.ALTO - 70);
		setLocation((dimPantalla.width - dimVentana.width) / 2, (dimPantalla.height - dimVentana.height) / 2);
	}

	public void setControlador(ControlLogin cl) {
		btnRegistrarme.addActionListener(cl);
		btnTengoCuenta.addActionListener(cl);
	}

	public Particular obtenerDatos() {
		Particular particular = null;

		int id = 0;
		String nombre = textNombre.getText().trim();
		String apellido = textApellido.getText().trim();
		String dni = textDni.getText().trim();
		String email = textEmail.getText().trim();
		String pwd = passContrasenia.getText().trim();
		String fechaNac = (String) cmbDia.getSelectedItem() + cmbMes.getSelectedItem() + cmbAnio.getSelectedItem();
		String tel = textTelefono.getText().trim();

		if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty() || pwd.isEmpty()
				|| fechaNac.isEmpty() || tel.isEmpty()) {
			mostrarMensajeError("Todos los campos son obligatorios");
		} else {

			if (dni.length() != MAX_CARAC) {
				mostrarMensajeError("\nEl DNI debe tener 9 caracteres");
			} else if (tel.length() != MAX_CARAC) {
				mostrarMensajeError("\nEl telefono debe tener 9 caracteres");
			} else {
				particular = new Particular(id, nombre, apellido, dni, email, pwd, fechaNac, tel);
			}
		}

		return particular;
	}

	public JButton getBtnTengoCuenta() {
		return btnTengoCuenta;
	}

	public JButton getBtnRegistrarme() {
		return btnRegistrarme;
	}

	public void mostrarMensajeError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
}
