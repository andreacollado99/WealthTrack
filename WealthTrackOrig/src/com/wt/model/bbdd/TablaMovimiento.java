package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.wt.module.Cripto;
import com.wt.module.Meta;
import com.wt.module.Movimiento;
import com.wt.module.ValorCripto;
import com.wt.module.particular.Particular;

public class TablaMovimiento {

	public static final String NOM_TABLA = "Movimientos";
	public static final String NOM_COL_ID_MOV = "Id";
	public static final String NOM_COL_NOMBRE = "Nombre";
	public static final String NOM_COL_IMPORTE = "Importe";
	public static final String NOM_COL_TIPO = "Tipo";
	public static final String NOM_COL_EMAIL = "Email";

	private ConexionBBDD conBBDD;

	public TablaMovimiento() {
		conBBDD = new ConexionBBDD();
	}

	public int registrarMovimiento(Movimiento mov) {
		int resultado = 0;

		String query = "INSERT INTO " + NOM_TABLA + "(" + NOM_COL_TIPO + "," + NOM_COL_NOMBRE + "," + NOM_COL_IMPORTE + ","+ NOM_COL_EMAIL + ") VALUES (?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, mov.getTipo());
			pstmt.setString(2, mov.getNombreMovimiento());
			pstmt.setDouble(3, mov.getImporte());
			pstmt.setString(4, mov.getEmail());

			resultado = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<Movimiento> consultarMovimiento(String usuario) {

		ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();

		String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_EMAIL + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, usuario);

			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				listaMovimientos.add(new Movimiento(rslt.getInt(1), rslt.getString(4), rslt.getString(2), rslt.getDouble(3), rslt.getString(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rslt != null) {
					rslt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaMovimientos;
	}

	public int eliminarMov(int idMov) {
		int resultado = 0;

		String query = "DELETE FROM " + NOM_TABLA + " WHERE " + NOM_COL_ID_MOV + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idMov);
			resultado = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
	
	public Double consultarImporteTipo(String tipo, String email) {

		Double importe = 0.0;

		String query = "SELECT SUM( "+ NOM_COL_IMPORTE +" ) FROM " + NOM_TABLA + " WHERE " + NOM_COL_TIPO + " = ? AND " + NOM_COL_EMAIL + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, tipo);
			pstmt.setString(2, email);

			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				importe = rslt.getDouble(1);
				}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rslt != null) {
					rslt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return importe;
	}

	public  int modificarMovimiento(int idMovsel, double importe) {
		int resultado = 0;

		String query = "UPDATE " + NOM_TABLA + " SET " + NOM_COL_IMPORTE + " = ? " + " WHERE " + NOM_COL_ID_MOV + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, importe);
			pstmt.setInt(2, idMovsel);
			resultado = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}



}
