package com.wt.view;

import javax.swing.*;
import java.awt.*;
import com.wt.control.ControlLogin;
import com.wt.module.SesionUsuario;

public class JPGestiones extends JPanel {

    public static final String ACT_COM_BTN_ANIADIR_META = "Añadir Meta";
    public static final String ACT_COM_BTN_CONSULTAR_META = "Consultar Meta";

    private JButton btnAniadirMeta, btnConMeta;
    private JScrollPane contenedor;
    private JLabel lblUsuario;
    private JPanel panelFondo;
	private JLabel lblFondo2;
	private JLabel lblSaldoDisponible;

    public JPGestiones() {
        setLayout(null);
        setBackground(new Color(175, 238, 238));
        setSize(900, 600);

        JLabel lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblBienvenido.setBounds(10, 10, 122, 38);
        add(lblBienvenido);

        lblUsuario = new JLabel("");
        lblUsuario.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblUsuario.setBounds(113, 10, 200, 38);
        add(lblUsuario);

        lblSaldoDisponible = new JLabel("");
        lblSaldoDisponible.setFont(new Font("Cambria Math", Font.BOLD, 18));
        lblSaldoDisponible.setBounds(93, 83, 740, 38);
        add(lblSaldoDisponible);

        btnAniadirMeta = new JButton(ACT_COM_BTN_ANIADIR_META);
        btnAniadirMeta.setBounds(167, 154, 157, 21);
        add(btnAniadirMeta);

        btnConMeta = new JButton(ACT_COM_BTN_CONSULTAR_META);
        btnConMeta.setBounds(457, 154, 157, 21);
        add(btnConMeta);

        contenedor = new JScrollPane();
        contenedor.setBounds(77, 203, 640, 240);
        contenedor.setVisible(false);
        contenedor.setBorder(null);
        add(contenedor);
        
        panelFondo = new JPanel();
        panelFondo.setBounds(0, 0, 900, 600);
        add(panelFondo);
        panelFondo.setLayout(null);
        
        lblFondo2 = new JLabel("");
        lblFondo2.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
        lblFondo2.setBounds(0, -50, 900, 650);
        panelFondo.add(lblFondo2);

    }
    

    public JButton getBtnAniadirMeta() {
		return btnAniadirMeta;
	}


	public JButton getBtnConMeta() {
		return btnConMeta;
	}


	public void setControlador(ControlLogin cl) {
        btnAniadirMeta.addActionListener(cl);
        btnConMeta.addActionListener(cl);
    }

    public void cargarPanel(JPanel panel) {
    	contenedor.setViewportView(panel);
    	contenedor.setVisible(true);
    	contenedor.revalidate();
    	contenedor.repaint();
    }

    public void actualizarDatosUsuario(String nombreUsuario,double ingresos, double gastos, double criptoInvertido) {
        lblUsuario.setText(nombreUsuario);
        lblSaldoDisponible.setText("Saldo disponible en la cuenta : ................................................."+Double.toString(ingresos-(gastos + criptoInvertido)) + " €");
        
    }
    
    
}
