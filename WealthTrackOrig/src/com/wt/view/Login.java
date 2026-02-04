package com.wt.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;

import com.wt.control.ControlLogin;

public class Login extends JFrame {

    public static final int ANCHO = 850;
    public static final int ALTO = 650;
    private static final int ALTO_PNL_LOGO = 90;
    public static final int ALTO_PNL_CONTENIDO = ALTO - ALTO_PNL_LOGO - 35;
    public static final String ACT_COM_BTN_INGRESAR = "Ingresar";
    public static final String ACT_COM_BTN_CREAR_PARTICULAR = "Crear cuenta particular";
    public static final String ACT_COM_BTN_CREAR_EMPRESA = "Crear cuenta empresa";
    
    private JLabel lblImagen;
    private JLabel lblFondo;
    private JPanel pnlFondo;
    private JPanel pnlLogo;
    private JLabel lblFondo3;
    private JTextField textEmail;
    private JPasswordField passContrasenia;
    private JButton btnCrearCuentaEmp;
    private JRadioButton rdbtnParticular;
    private JRadioButton rdbtnEmpresa;
    private JButton btnCrearCuentaPar;
    private final ButtonGroup buttonGroup = new ButtonGroup();
	private AbstractButton btnIngresar;

    public Login() {
        super("WealthTrack");
        initComponents();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout(0, 0));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ANCHO, ALTO);

        Dimension dimPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dimVentana = new Dimension(ANCHO, ALTO);
        setLocation((dimPantalla.width - dimVentana.width) / 2, (dimPantalla.height - dimVentana.height) / 2);

        pnlLogo = new JPanel();
        pnlLogo.setPreferredSize(new Dimension(ANCHO, ALTO_PNL_LOGO));
        pnlLogo.setLayout(null);
        getContentPane().add(pnlLogo, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("WealthTrack");
        lblNewLabel.setBounds(287, 24, 179, 40);
        pnlLogo.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Palatino Linotype", Font.PLAIN, 29));

        lblImagen = new JLabel("");
        lblImagen.setBounds(461, 0, 97, 90);
        pnlLogo.add(lblImagen);
        lblImagen.setIcon(new ImageIcon(new ImageIcon(Login.class.getResource("/img/logo.png")).getImage()
                .getScaledInstance(83, 68, Image.SCALE_SMOOTH)));

        JLabel lblNewLabel_1 = new JLabel("Tu socio Financiero en un Click");
        lblNewLabel_1.setBounds(287, 50, 345, 14);
        pnlLogo.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(new Color(0, 0, 139));

        lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
        lblFondo.setBounds(0, 0, 850, 600);
        pnlLogo.add(lblFondo);

        pnlFondo = new JPanel();
        pnlFondo.setPreferredSize(new Dimension(ANCHO, ALTO_PNL_CONTENIDO));
        pnlFondo.setLayout(null);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(258, 87, 46, 25);
        pnlFondo.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(248, 140, 373, 31);
        pnlFondo.add(textEmail);

        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblContrasenia.setBounds(258, 218, 90, 25);
        pnlFondo.add(lblContrasenia);

        passContrasenia = new JPasswordField();
        passContrasenia.setBounds(248, 243, 373, 31);
        pnlFondo.add(passContrasenia);

        btnCrearCuentaPar = new JButton(ACT_COM_BTN_CREAR_PARTICULAR);
        btnCrearCuentaPar.setBounds(240, 332, 186, 21);
        btnCrearCuentaPar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCrearCuentaPar.setContentAreaFilled(false);
        btnCrearCuentaPar.setBorderPainted(false);
        pnlFondo.add(btnCrearCuentaPar);

        btnCrearCuentaEmp = new JButton(ACT_COM_BTN_CREAR_EMPRESA);
        btnCrearCuentaEmp.setBounds(240, 363, 179, 21);
        btnCrearCuentaEmp.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCrearCuentaEmp.setContentAreaFilled(false);
        btnCrearCuentaEmp.setBorderPainted(false);
        pnlFondo.add(btnCrearCuentaEmp);

        btnIngresar = new JButton(ACT_COM_BTN_INGRESAR);
        btnIngresar.setBounds(368, 419, 114, 37);
        btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIngresar.setForeground(Color.BLACK);
        btnIngresar.setBackground(Color.WHITE);
        pnlFondo.add(btnIngresar);

        rdbtnParticular = new JRadioButton("Particular");
        buttonGroup.add(rdbtnParticular);
        rdbtnParticular.setBounds(252, 300, 103, 21);
        rdbtnParticular.setOpaque(false);
        rdbtnParticular.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnlFondo.add(rdbtnParticular);

        rdbtnEmpresa = new JRadioButton("Empresa");
        buttonGroup.add(rdbtnEmpresa);
        rdbtnEmpresa.setBounds(415, 300, 103, 21);
        rdbtnEmpresa.setOpaque(false);
        rdbtnEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnlFondo.add(rdbtnEmpresa);

        lblFondo3 = new JLabel("");
        lblFondo3.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
        lblFondo3.setBounds(-10, -88, 860, 613);
        pnlFondo.add(lblFondo3);

        getContentPane().add(pnlFondo, BorderLayout.CENTER);
    }

    public void setControlador(ControlLogin cl) {
        btnCrearCuentaEmp.addActionListener(cl);
        rdbtnParticular.addActionListener(cl);
        rdbtnEmpresa.addActionListener(cl);
        btnCrearCuentaPar.addActionListener(cl);
        btnIngresar.addActionListener(cl);
    }
    
    

    public JButton getBtnCrearCuentaEmp() {
		return btnCrearCuentaEmp;
	}

	public JButton getBtnCrearCuentaPar() {
		return btnCrearCuentaPar;
	}

	public AbstractButton getBtnIngresar() {
		return btnIngresar;
	}

	public String obtenerEmail() {
        return textEmail.getText().trim();
    }

    public String obtenerContrasenia() {
        return new String(passContrasenia.getPassword());
    }

    public boolean esParticularSeleccionado() {
        return rdbtnParticular.isSelected();
    }
    
    public boolean esEmpresaSeleccionado() {
        return rdbtnEmpresa.isSelected();
    }

    public void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

	public void limpiarDatos() {
		textEmail.setText("");
		passContrasenia.setText("");
		rdbtnParticular.setSelected(true);
	}
}
