package com.wt.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.wt.model.bbdd.TablaCriptomonedas;
import com.wt.model.bbdd.TablaEmpresa;
import com.wt.model.bbdd.TablaMeta;
import com.wt.model.bbdd.TablaMovimiento;
import com.wt.model.bbdd.TablaParticular;
import com.wt.model.bbdd.TablaValorCripto;
import com.wt.module.Cripto;
import com.wt.module.Meta;
import com.wt.module.Movimiento;
import com.wt.module.SesionUsuario;
import com.wt.module.empresa.Empresa;
import com.wt.module.particular.Particular;
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

public class ControlLogin implements ActionListener {

	private static final int NUM_INTENTOS = 3;

	private Login lg;
	private RegistroParticular rp;
	private RegistroEmpresa re;
	private HomeP hmP;
	private JPMovimientos jpm;
	private JPGestiones jpg;
	private JPAniadirMetas jpa;
	private JPConsultarMetas jpc;
	private JPModificarMeta jpmm;
	private JPInversiones jpi;
	private TablaParticular datosParticular;
	private TablaEmpresa datosEmpresa;
	private TablaMeta datosMeta;
	private TablaValorCripto tbvc;
	private TablaCriptomonedas tbc;
	private TablaMovimiento tbm;
	private HomeE hmE;
	SesionUsuario sesion;
	int idMovsel = -1;
	int idCriptoSel = -1;
	int idMetaSel = -1;

	private int contIntentos = 0;

	public ControlLogin(Login lg, RegistroParticular rp, RegistroEmpresa re, HomeP hmP, JPMovimientos jpm,
			JPGestiones jpg, JPAniadirMetas jpa, JPConsultarMetas jpc, JPInversiones jpi, JPModificarMeta jpmm, HomeE hmE) {
		this.lg = lg;
		this.rp = rp;
		this.re = re;
		this.hmP = hmP;
		this.jpm = jpm;
		this.jpg = jpg;
		this.jpa = jpa;
		this.jpc = jpc;
		this.jpi = jpi;
		this.jpmm = jpmm;
		this.hmE = hmE;
		this.datosParticular = new TablaParticular();
		this.datosEmpresa = new TablaEmpresa();
		this.datosMeta = new TablaMeta();
		this.tbvc = new TablaValorCripto();
		this.tbc = new TablaCriptomonedas();
		this.tbm = new TablaMovimiento();

		contIntentos = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {

			if (e.getSource().equals(lg.getBtnIngresar())) {
				System.out.println("Botón Ingresar presionado");

				String email = lg.obtenerEmail();
				String contrasenia = lg.obtenerContrasenia();
				boolean esParticular = lg.esParticularSeleccionado();
				boolean esEmpresa = lg.esEmpresaSeleccionado();

				if (email != null && contrasenia != null) {
					if (esParticular) {
						Particular particular = datosParticular.comprobarExiste(email, contrasenia);

						if (particular != null) {
							sesion = new SesionUsuario(particular.getId(), particular.getEmail(),
									particular.getContrasenia(), particular.getNombre());

							datosUsuario();

							lg.dispose();
							hmP.setVisible(true);
							hmP.cargarPanel(jpg);
						} else {
							lg.mostrarMensajeError("El usuario no está registrado como particular");
						}
					} else if (esEmpresa) {
						Empresa empresa = datosEmpresa.comprobarExiste(email, contrasenia);
						if (empresa != null) {
							sesion = new SesionUsuario(empresa.getId(), empresa.getEmail(), empresa.getContrasenia(),
									empresa.getNombre());

							datosUsuario();

							lg.dispose();
							hmE.setVisible(true);
							hmE.cargarPanel(jpg);

						} else {
							lg.mostrarMensajeError("El usuario no está registrado como empresa");
						}
					} else {
						lg.mostrarMensajeError("No se ha especificado si el usuario es PARTICULAR o EMPRESA");
					}
				}
			} else if (e.getSource().equals(lg.getBtnCrearCuentaPar())) {
				System.out.println("Botón Crear cuenta particular presionado");
				lg.dispose();
				rp.setVisible(true);

			} else if (e.getSource().equals(lg.getBtnCrearCuentaEmp())) {
				System.out.println("Botón Crear cuenta empresa presionado");
				lg.dispose();
				re.setVisible(true);

			} else if (e.getSource().equals(rp.getBtnRegistrarme())) {
				System.out.println("Botón Registrarme (Particular) presionado");
				Particular usuarioReg = rp.obtenerDatos();

				if (usuarioReg != null) {
					int numRegI = datosParticular.registrarParticular(usuarioReg);
					if (numRegI == 1) {
						rp.mostrarMensaje("Registrado con éxito");
						rp.dispose();
						lg.setVisible(true);
					}
				}

			} else if (e.getSource().equals(re.getBtnRegistrarme())) {
				System.out.println("Botón Registrarme (Empresa) presionado");
				Empresa empresaReg = re.obtenerDatos();

				if (empresaReg != null) {
					int numRegI = datosEmpresa.registrarEmpresa(empresaReg);
					if (numRegI == 1) {
						re.mostrarMensaje("Registrado con éxito");
						re.dispose();
						lg.setVisible(true);
					}
				}

			} else if (e.getSource().equals(rp.getBtnTengoCuenta())) {
				System.out.println("Botón Ya tengo una cuenta (Particular) presionado");
				rp.dispose();
				lg.setVisible(true);

			} else if (e.getSource().equals(re.getBtnTengoCuenta())) {
				System.out.println("Botón Ya tengo una cuenta (Empresa) presionado");
				re.dispose();
				lg.setVisible(true);

			} else if (e.getSource().equals(hmP.getBtnGestiones())) {
				System.out.println("Botón Gestiones presionado");
				hmP.cargarPanel(jpg);
				consultarMisMetas();
				datosUsuario();

			} else if (e.getSource().equals(hmP.getBtnMovimientos())) {
				System.out.println("Botón Movimientos presionado");
				hmP.cargarPanel(jpm);
				
					consultarMisMovimientos();
				

			} else if (e.getSource().equals(hmE.getBtnGestiones())) {
				System.out.println("Botón Gestiones presionado");
				hmE.cargarPanel(jpg);
				consultarMisMetas();
				datosUsuario();

			} else if (e.getSource().equals(hmE.getBtnMovimientos())) {
				System.out.println("Botón Movimientos presionado");
				hmE.cargarPanel(jpm);
				
					consultarMisMovimientos();
				

			} else if (e.getSource().equals(jpm.getBtnAniadir())) { // AÑADIR Movimiento
				Movimiento mov = jpm.obtenerDatos();
				if (mov == null) {
					jpm.mostrarMensajeError("no se ha podido registrar");
				} else {
					mov.setEmail(sesion.getEmail());
					int criptoAniadida = tbm.registrarMovimiento(mov);
					if (criptoAniadida == 1) {
						jpm.mostrarMensaje("El " + mov.getTipo() + " se ha agregado con éxito");
						jpm.limpiarDatos();
					}
					consultarMisMovimientos();

				}

			} else if (e.getSource().equals(jpm.getBtnModificarMov())) { // MODIFICAR MOVIMIENTO

				int filaSel = jpm.getTblMov().getSelectedRow();

				if (filaSel == -1) {

					jpm.mostrarMensaje("No se ha seleccionado ningun movimiento");

				} else {
					idMovsel = (int) jpm.getDtmTblMdl().getValueAt(filaSel, 0);
					String nomMovsel = (String) jpm.getDtmTblMdl().getValueAt(filaSel, 1);
					String nomTipoMovsel = (String) jpm.getDtmTblMdl().getValueAt(filaSel, 3);

					jpm.pedirModif(true);

					jpm.setTextNombreMov(nomMovsel);
					jpm.setCmbTipos(nomTipoMovsel);

				}

			} else if (e.getSource().equals(jpm.getBtnGuardarModif())) { // confirmar modificacion movimiento

				double importe = 0.0;

				try {

					importe = Double.parseDouble(jpm.getTextImporte());

					if (importe <= 0) {
						throw new NumberFormatException();
					}

					int respuesta = JOptionPane.showConfirmDialog(jpm,
							"Se va a modificar el movimiento " + "¿Desea continuar?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						int res = tbm.modificarMovimiento(idMovsel, importe);

						if (res == 1) {
							// modificado
							jpm.limpiarDatos();
							jpm.pedirModif(false);
							JOptionPane.showMessageDialog(jpm, "El movimiento ha sido modificado con éxito",
									"Resultado de operación", JOptionPane.INFORMATION_MESSAGE);
							consultarMisMovimientos();

						}
					}

				} catch (NumberFormatException e2) {
					jpm.mostrarMensajeError("El importe debe ser un valor entero mayor a 0");
				}

			} else if (e.getSource().equals(jpm.getBtnCancelar())) { // CANCELAR MODIFICACION

				jpm.limpiarDatos();
				jpm.pedirModif(false);

			} else if (e.getSource().equals(jpm.getBtnEliminarMov())) { // ELIMINAR MOVIMIENTO

				int elimMov = jpm.getTblMov().getSelectedRow();

				if (elimMov == -1) {

					jpi.mostrarMensaje("No se ha seleccionado ningun movimiento");

				} else {
					int respuesta = JOptionPane.showConfirmDialog(null, // Componente padre, null centra el diálogo
							"¿Estás seguro que quieres eliminarlo?", // Mensaje a mostrar
							"Confirmación", // Título de la ventana
							JOptionPane.YES_NO_OPTION // Opciones: Sí y No
					);

					if (respuesta == JOptionPane.YES_OPTION) {

						ArrayList<Movimiento> lista = tbm.consultarMovimiento(sesion.getEmail());
						Movimiento movElim = lista.get(elimMov);

						int confirmarEliminado = tbm.eliminarMov(movElim.getIdMovimiento());
						if (confirmarEliminado == 1) {
							jpm.mostrarMensaje("Se ha eliminado correctamente el movimiento");
						}
						consultarMisMovimientos();

					} else if (respuesta == JOptionPane.NO_OPTION) {
						System.out.println("Se ha cancelado la acción.");
					}
				}

			} else if (e.getSource().equals(hmE.getBtnTransacciones())) {
				System.out.println("Botón Inversiones presionado");
				hmE.cargarPanel(jpi);
				jpi.cargarLista(tbvc.consultarValorCripto());

				consultarMisCriptos();

			} else if (e.getSource().equals(hmP.getBtnInversiones())) {
				System.out.println("Botón Inversiones presionado");
				hmP.cargarPanel(jpi);
				jpi.cargarLista(tbvc.consultarValorCripto());

				consultarMisCriptos();

			} else if (e.getSource().equals(jpi.getBtnAniadir())) { // AÑADIR CRIPTO
				Cripto cripto = jpi.obtenerDatos();
				if (cripto == null) {
					jpi.mostrarMensajeError("No se ha podido registrar");
				} else {

					Cripto existe = tbc.comprobarExiste(cripto.getNombreCripto(), sesion.getEmail());

					if (existe == null) {
						cripto.setImporteEnCripto(
								(cripto.getImporteEnEuro() / tbvc.consultarImporteCripto(cripto.getNombreCripto())));
						cripto.setEmailUsuario(sesion.getEmail());
						int criptoAniadida = tbc.registrarCriptomoneda(cripto);
						if (criptoAniadida == 1) {
							jpi.mostrarMensaje("La criptomoneda se ha agregado con éxito");
							jpi.limpiarDatos();
							consultarMisCriptos();
						}
					} else {
						jpi.mostrarMensajeError("La criptomoneda ingresada ya existe");

					}

				}

			} else if (e.getSource().equals(jpi.getBtnGuardarModifCripto())) { // CONFIRMAR MOFIFICACION CRIPTO
				double importeEur = 0.0;
				double importeCript = 0.0;
				Cripto crip = tbc.consultarCriptomoneda(idCriptoSel);

				crip.setImporteEnCripto(
						(crip.getImporteEnEuro() / tbvc.consultarImporteCripto(crip.getNombreCripto())));

				try {

					importeEur = Double.parseDouble(jpi.getTextImporte());

					if (importeEur <= 0) {
						throw new NumberFormatException();
					}
					importeCript = importeEur / tbvc.consultarImporteCripto(crip.getNombreCripto());

					int respuesta = JOptionPane.showConfirmDialog(jpi,
							"Se va a modificar la criptomoneda " + "¿Desea continuar?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						int res = tbc.modificarMovimiento(idCriptoSel, importeEur, importeCript);

						if (res == 1) {
							// modificado
							jpi.limpiarDatos();
							jpi.pedirModif(false);
							JOptionPane.showMessageDialog(jpi, "La criptomoneda ha sido modificada con éxito",
									"Resultado de operación", JOptionPane.INFORMATION_MESSAGE);
							consultarMisCriptos();

						}
					}

				} catch (NumberFormatException e2) {
					jpi.mostrarMensajeError("El importe debe ser un valor entero mayor a 0");
				}

			} else if (e.getSource().equals(jpi.getBtnCancelarModifCripo())) {

				jpi.limpiarDatos();
				jpi.pedirModif(false);

			} else if (e.getSource().equals(jpi.getBtnModificarCripto())) { // MODIFICAR CRIPTO

				System.out.println("Se ha presionado el botón modificar");
				int filaSel = jpi.getTblCriptos().getSelectedRow();

				if (filaSel == -1) {

					jpm.mostrarMensaje("No se ha seleccionado ninguna Criptomoneda");

				} else {
					idCriptoSel = (int) jpi.getDtmTblMdl().getValueAt(filaSel, 0);
					String nomCriptSel = (String) jpi.getDtmTblMdl().getValueAt(filaSel, 1);

					jpi.setCmbListaCripto(nomCriptSel);
					jpi.pedirModif(true);

				}

			} else if (e.getSource().equals(jpi.getBtnEliminarCripto())) { // ELIMINAR CRIPTO

				int elimCripto = jpi.eliminarCriptoTabla();

				if (elimCripto == -1) {

					jpi.mostrarMensaje("No se ha seleccionado ningun elemento");

				} else {
					int respuesta = JOptionPane.showConfirmDialog(null, // Componente padre, null centra el diálogo
							"¿Estás seguro que quieres eliminarlo?", // Mensaje a mostrar
							"Confirmación", // Título de la ventana
							JOptionPane.YES_NO_OPTION // Opciones: Sí y No
					);

					if (respuesta == JOptionPane.YES_OPTION) {

						ArrayList<Cripto> lista = tbc.consultarCriptomonedas(sesion.getEmail());
						Cripto criptoElim = lista.get(elimCripto);

						int confirmarEliminado = tbc.eliminarCripto(criptoElim.getIdCripto());
						if (confirmarEliminado == 1) {
							jpi.mostrarMensaje("Se ha eliminado correctamente la criptomoneda");
						}
						consultarMisCriptos();

					} else if (respuesta == JOptionPane.NO_OPTION) {
						System.out.println("Se ha cancelado la acción.");
					}
				}

			}else if (e.getSource().equals(jpa.getBtnAniadirMeta())) {  //CONFIRMAR META AÑADIDA
				System.out.println("Meta añadida");
				Meta aniadirMeta = jpa.obtenerDatos();

				if (aniadirMeta != null) {
					// Asignar el usuario actual a la meta
					aniadirMeta.setUsuarioId(sesion.getId());

					int numRegM = datosMeta.registrarMeta(aniadirMeta);

					if (numRegM == 1) {
						jpa.mostrarMensaje("Meta registrada con éxito");
						jpa.limpiarDatos();
						consultarMisMetas();
					}
				}

			}else if (e.getSource().equals(jpc.getBtnModificarMeta())) {  //MODIFICAR META
				System.out.println("Botón MODIFICAR meta presionado");
				
				int fila = jpc.getTblMetas().getSelectedRow();
				
				
				
				if (fila == -1) {
					jpc.mostrarMensajeError("No has seleccionado ningún elemento");
				}else {
					idMetaSel = (int) jpc.getTblMetas().getValueAt(fila, 0);
					Meta met = datosMeta.obtenerMeta(idMetaSel);
					
					if (!met.getProgresoActual().equals(Meta.PROGRESO[2])) {
						double saldoDisp = tbm.consultarImporteTipo(Movimiento.TIPOS[0], sesion.getEmail()) -
								(tbm.consultarImporteTipo(Movimiento.TIPOS[1], sesion.getEmail()) +
								jpi.obtenerTotalInvertido(tbc.consultarCriptomonedas(sesion.getEmail()))); 
						
						
						jpg.cargarPanel(jpmm);
						jpmm.cargarModificacion(met.getDescripcion(),met.getCantidadObjetivo(),met.getProgresoActual());
						
						if (saldoDisp < met.getCantidadObjetivo()) {
							jpmm.activacionProgreso(false);
						}else if(saldoDisp >= met.getCantidadObjetivo()) {
							jpmm.activacionProgreso(true);
						}
					}else {
						jpc.mostrarMensaje("Esta meta ya se encuentra como finalizada y no se puede modificar");
					}
	
				}
				
			}else if (e.getSource().equals(jpmm.getBtnGuardarModifMeta())) {  //CONFIRMACION MODIFICAR META
				System.out.println("Botón GUARDAR MODIFICACION meta presionado");
				Meta met = datosMeta.obtenerMeta(idMetaSel);
				String progreso = jpmm.obtenerProgreso();
				double importe = 0.0;
				
				try {

					int respuesta = JOptionPane.showConfirmDialog(jpmm,
							"Se va a modificar la meta " + "¿Desea continuar?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						if (progreso.equals(Meta.PROGRESO[2])) {
							
							Movimiento mov = new Movimiento(Movimiento.TIPOS[1], met.getDescripcion(), met.getCantidadObjetivo(), sesion.getEmail());
							int res = datosMeta.modificarMeta(idMetaSel, met.getCantidadObjetivo(), Meta.PROGRESO[2]);
							tbm.registrarMovimiento(mov);
							//datosMeta.eliminarMeta(idMetaSel);
							jpc.mostrarMensaje("¡¡ENHORABUENA!! Se ha registrado el gasto de la meta finalizada");
							consultarMisMetas();
							
						}else {
							importe = Double.parseDouble(jpmm.obtenerImporte());
							
							/*if (importe <= 0) {
								throw new NumberFormatException();
							}*/
							
							int res = datosMeta.modificarMeta(idMetaSel, importe, progreso);

							if (res == 1) {
								// modificado
								jpmm.limpiarDatos();
								JOptionPane.showMessageDialog(jpmm, "La meta ha sido modificada con éxito",
										"Resultado de operación", JOptionPane.INFORMATION_MESSAGE);
								
								consultarMisMetas();
							}
							
						}
						
					}
					
				} catch (NumberFormatException e2) {
					jpmm.mostrarMensajeError("el importe debe ser un valor entero mayor a 0");
				}
				
			}else if(e.getSource().equals(jpmm.getBtnCancelarModifMeta())) {
				jpmm.limpiarDatos();
				consultarMisMetas();
				
			} else if (e.getSource().equals(jpg.getBtnAniadirMeta())) {  //AÑADIR META
				System.out.println("Botón añadir meta presionado");
				jpg.cargarPanel(jpa);

			} else if (e.getSource().equals(jpg.getBtnConMeta())) {		//CONSULTAR META
				consultarMisMetas();

			} else if (e.getSource().equals(jpc.getBtnEliminarMeta())) { // ELIMINAR META
				System.out.println("Botón eliminar presionado");
				
				int elimMeta = jpc.getTblMetas().getSelectedRow();
			
				if (elimMeta == -1) {

					jpc.mostrarMensaje("No se ha seleccionado ningún elemento");

				} else {
					idMetaSel = (int) jpc.getTblMetas().getValueAt(elimMeta, 0);
					Meta met = datosMeta.obtenerMeta(idMetaSel);
					if (!met.getProgresoActual().equals(Meta.PROGRESO[2])) {
						int respuesta = JOptionPane.showConfirmDialog(null, // Componente padre, null centra el diálogo
								"¿Estás seguro que quieres eliminarlo?", // Mensaje a mostrar
								"Confirmación", // Título de la ventana
								JOptionPane.YES_NO_OPTION // Opciones: Sí y No
						);

						if (respuesta == JOptionPane.YES_OPTION) {

							ArrayList<Meta> listaMetas = datosMeta.obtenerMetasPorUsuario(sesion.getId());
							Meta metaElim = listaMetas.get(elimMeta);

							int confirmarEliminado = datosMeta.eliminarMeta(metaElim.getMetaId());
							if (confirmarEliminado == 1) {
								jpc.mostrarMensaje("Se ha eliminado correctamente la meta");
							}
							jpc.rellenarTabla(datosMeta.obtenerMetasPorUsuario(sesion.getId()));

						} else if (respuesta == JOptionPane.NO_OPTION) {
							System.out.println("Se ha cancelado la acción.");
						}
					}else {
						jpc.mostrarMensajeError("No se puede eliminar una meta Finalizada");
					}

				}
			} else if (e.getSource().equals(hmP.getBtnCerrarSesion())) {
				System.out.println("Botón cerrar sesión presionado");

				int confirmacion = JOptionPane.showConfirmDialog(hmP, "¿Estás seguro que deseas cerrar sesión?",
						"Cerrar sesión", JOptionPane.YES_NO_OPTION);

				if (confirmacion == JOptionPane.YES_OPTION) {
					hmP.setVisible(false);
					jpi.vaciarTabla();
					jpc.vaciarTabla();
					lg.setVisible(true);
					lg.limpiarDatos();
				}
			} else if (e.getSource().equals(hmE.getBtnCerrarSesion())) {
				System.out.println("Botón cerrar sesión presionado");

				int confirmacion = JOptionPane.showConfirmDialog(hmP, "¿Estás seguro que deseas cerrar sesión?",
						"Cerrar sesión", JOptionPane.YES_NO_OPTION);

				if (confirmacion == JOptionPane.YES_OPTION) {
					hmE.setVisible(false);
					jpi.vaciarTabla();
					jpc.vaciarTabla();
					lg.setVisible(true);
					lg.limpiarDatos();
				}
			}
		}
	}

	private void consultarMisCriptos() {
		jpi.ConsultarMisCriptos(tbc.consultarCriptomonedas(sesion.getEmail()));
	}

	private void consultarMisMovimientos() {
		jpm.ConsultarMisMovimientos(tbm.consultarMovimiento(sesion.getEmail()));
		;
	}

	private void datosUsuario() {
		jpg.actualizarDatosUsuario(sesion.getNombre(), tbm.consultarImporteTipo(Movimiento.TIPOS[0], sesion.getEmail()),
				tbm.consultarImporteTipo(Movimiento.TIPOS[1], sesion.getEmail()),
				jpi.obtenerTotalInvertido(tbc.consultarCriptomonedas(sesion.getEmail())));
	}
	private void consultarMisMetas() {
		System.out.println("Botón consultar meta presionado");

		boolean esParticular = lg.esParticularSeleccionado();
		boolean esEmpresa = lg.esEmpresaSeleccionado();
		
		if (esParticular) {
			String descripcion = jpa.obtenerDescripcion();
			String importe = jpa.obtenerImporte();

			// Obtener solo las metas del usuario actual
			ArrayList<Meta> metasUsuario = datosMeta.obtenerMetasPorUsuario(sesion.getId());
			
			double saldoDisp = tbm.consultarImporteTipo(Movimiento.TIPOS[0], sesion.getEmail()) -
					(tbm.consultarImporteTipo(Movimiento.TIPOS[1], sesion.getEmail()) +
					jpi.obtenerTotalInvertido(tbc.consultarCriptomonedas(sesion.getEmail()))); 

			System.out.println("Metas recuperadas: " + metasUsuario.size());

			ArrayList<Meta> listaFiltrada = new ArrayList<>();

			for (Meta meta : metasUsuario) {
				boolean coincideDescripcion = descripcion == null || descripcion.isEmpty()
						|| meta.getDescripcion().contains(descripcion);
				boolean coincideImporte = true;

				
				if (importe != null && !importe.isEmpty()) {
					try {
						double importeFiltro = Double.parseDouble(importe);
						coincideImporte = meta.getCantidadObjetivo() == importeFiltro;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(jpc, "El importe introducido no es válido.",
								"Error de formato", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!meta.getProgresoActual().equals(Meta.PROGRESO[2])) {
					
					if (saldoDisp >= meta.getCantidadObjetivo()) {
						int resp = datosMeta.actualizarProgreso(meta.getMetaId(),Meta.PROGRESO[1]);
						meta.setProgresoActual(Meta.PROGRESO[1]);
					
					}else if(saldoDisp < meta.getCantidadObjetivo()){
						int resp = datosMeta.actualizarProgreso(meta.getMetaId(),Meta.PROGRESO[0]);
						meta.setProgresoActual(Meta.PROGRESO[0]);
					}
				}


				if (coincideDescripcion && coincideImporte) {
					listaFiltrada.add(meta);
				}
			}


				jpg.cargarPanel(jpc);
				jpc.rellenarTabla(listaFiltrada);
				jpc.mostrarTabla(true);
				hmP.cargarPanel(jpg);

		} else if (esEmpresa) {
			String descripcion = jpa.obtenerDescripcion();
			String importe = jpa.obtenerImporte();

			// Obtener solo las metas del usuario actual
			ArrayList<Meta> metasUsuario = datosMeta.obtenerMetasPorUsuario(sesion.getId());
			
			double saldoDisp = tbm.consultarImporteTipo(Movimiento.TIPOS[0], sesion.getEmail()) -
					(tbm.consultarImporteTipo(Movimiento.TIPOS[1], sesion.getEmail()) +
					jpi.obtenerTotalInvertido(tbc.consultarCriptomonedas(sesion.getEmail()))); 

			System.out.println("Metas recuperadas: " + metasUsuario.size());

			ArrayList<Meta> listaFiltrada = new ArrayList<>();

			for (Meta meta : metasUsuario) {
				boolean coincideDescripcion = descripcion == null || descripcion.isEmpty()
						|| meta.getDescripcion().contains(descripcion);
				boolean coincideImporte = true;

				
				if (importe != null && !importe.isEmpty()) {
					try {
						double importeFiltro = Double.parseDouble(importe);
						coincideImporte = meta.getCantidadObjetivo() == importeFiltro;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(jpc, "El importe introducido no es válido.",
								"Error de formato", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!meta.getProgresoActual().equals(Meta.PROGRESO[2])) {
					
					if (saldoDisp >= meta.getCantidadObjetivo()) {
						int resp = datosMeta.actualizarProgreso(meta.getMetaId(),Meta.PROGRESO[1]);
						meta.setProgresoActual(Meta.PROGRESO[1]);
					
					}else if(saldoDisp < meta.getCantidadObjetivo()){
						int resp = datosMeta.actualizarProgreso(meta.getMetaId(),Meta.PROGRESO[0]);
						meta.setProgresoActual(Meta.PROGRESO[0]);
					}
				}

				if (coincideDescripcion && coincideImporte) {
					listaFiltrada.add(meta);
				}
			
				jpg.cargarPanel(jpc);
				jpc.rellenarTabla(listaFiltrada);
				jpc.mostrarTabla(true);
				hmE.cargarPanel(jpg);
			}
		}
	}
}
