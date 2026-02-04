package com.wt.main;

import java.awt.EventQueue;

import com.wt.control.ControlLogin;
import com.wt.view.HomeE;
import com.wt.view.HomeP;
import com.wt.view.JPAniadirMetas;
import com.wt.view.JPConsultarMetas;
import com.wt.view.JPGestiones;
import com.wt.view.JPInversiones;
import com.wt.view.JPModificarMeta;
import com.wt.view.JPMovimientos;
import com.wt.view.Login;
import com.wt.view.RegistroEmpresa;
import com.wt.view.RegistroParticular;

public class Inicio {

	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            Login lg = new Login();
	            RegistroParticular rp = new RegistroParticular();
	            RegistroEmpresa re = new RegistroEmpresa();
	            HomeP hmP = new HomeP();
	            JPMovimientos jpm = new JPMovimientos();
	            JPGestiones jpg = new JPGestiones();
	            JPAniadirMetas jpa = new JPAniadirMetas();
	            JPConsultarMetas jpc = new JPConsultarMetas();
	            JPInversiones jpi = new JPInversiones();
	            JPModificarMeta jpmm = new JPModificarMeta();
	            HomeE hmE = new HomeE();
	            
	            ControlLogin cl = new ControlLogin(lg, rp, re, hmP, jpm, jpg, jpa, jpc, jpi, jpmm, hmE);

	            lg.setControlador(cl);
	            rp.setControlador(cl);
	            re.setControlador(cl);
	            hmP.setControlador(cl);
	            jpm.setControlador(cl);
	            jpg.setControlador(cl);
	            jpa.setControlador(cl);
	            jpi.setControlador(cl);
	            jpc.setControlador(cl);
	            jpmm.setControlador(cl);
	            hmE.setControlador(cl);


	            lg.setVisible(true);
	        }
	    });
	}

}
