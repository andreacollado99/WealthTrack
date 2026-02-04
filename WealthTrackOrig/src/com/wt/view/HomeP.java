package com.wt.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.wt.control.ControlLogin;

public class HomeP extends JFrame {

    public static final int ANCHO = 850;
    public static final int ALTO = 650;
    private static final int ALTO_PNL_BTN = 47;
    private static final int ALTO_PNL_LOGO = 90;
    public static final String ACT_COM_BTN_GESTIONES = "GESTIONES";
    public static final String ACT_COM_BTN_MOVIMIENTOS = "MOVIMIENTOS";
    public static final String ACT_COM_BTN_INVERSIONES = "INVERSIONES";
    public static final String ACT_COM_BTN_CERRAR_SESION = "Cerrar Sesión";

    private JLabel lblImagen;
    private JLabel lblFondo;
    private JButton btnGestiones;
    private JButton btnMovimientos;
    private JButton btnInversiones;
    private JScrollPane scrContenedorSeg;
    private JPanel pnlLogo;
    private JPanel pnlBotones;
    private JPanel pnlFondo;
    private JLabel lblFondo2;
	private JButton btnCerrarSesion;

    public HomeP() {
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

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(Color.RED);
        btnCerrarSesion.setBounds(10, 10, 122, 21);
        pnlLogo.add(btnCerrarSesion);
        
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
        lblFondo.setBounds(0, 0, 850, 609);
        pnlLogo.add(lblFondo);

        pnlBotones = new JPanel();
        pnlBotones.setLayout(null);
        pnlBotones.setPreferredSize(new Dimension(ANCHO, ALTO_PNL_BTN));
        getContentPane().add(pnlBotones, BorderLayout.CENTER);

        btnGestiones = new JButton(ACT_COM_BTN_GESTIONES);
        btnGestiones.setBounds(0, 0, 274, 47);
        btnGestiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pnlBotones.add(btnGestiones);

        btnMovimientos = new JButton(ACT_COM_BTN_MOVIMIENTOS);
        btnMovimientos.setBounds(273, 0, 295, 47);
        btnMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pnlBotones.add(btnMovimientos);

        btnInversiones = new JButton(ACT_COM_BTN_INVERSIONES);
        btnInversiones.setBounds(564, 0, 286, 47);
        btnInversiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pnlBotones.add(btnInversiones);

        scrContenedorSeg = new JScrollPane();
        scrContenedorSeg.setBounds(0, 45, 850, 478);
        pnlBotones.add(scrContenedorSeg);

        pnlFondo = new JPanel();
        pnlFondo.setPreferredSize(new Dimension(ANCHO, ALTO - ALTO_PNL_LOGO - ALTO_PNL_BTN - 35));
        pnlFondo.setLayout(null);

        lblFondo2 = new JLabel("");
        lblFondo2.setIcon(new ImageIcon(Login.class.getResource("/img/fondo.png")));
        lblFondo2.setBounds(0, 0, ANCHO, ALTO - ALTO_PNL_LOGO - ALTO_PNL_BTN - 35);
        pnlFondo.add(lblFondo2);

        cargarPanel(pnlFondo);
    }

    public void setControlador(ControlLogin cl) {
        btnGestiones.addActionListener(cl);
        btnMovimientos.addActionListener(cl);
        btnInversiones.addActionListener(cl);
        btnCerrarSesion.addActionListener(cl);
    }
    
    

    public JButton getBtnGestiones() {
		return btnGestiones;
	}

	public JButton getBtnMovimientos() {
		return btnMovimientos;
	}

	public JButton getBtnInversiones() {
		return btnInversiones;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public void cargarPanel(JPanel panelSeguimiento) {
        if (scrContenedorSeg != null) {
            scrContenedorSeg.setViewportView(panelSeguimiento);
        }

        /*scrContenedorSeg.setViewportView(panelSeguimiento);
        scrContenedorSeg.setVisible(true);*/
    }
}
