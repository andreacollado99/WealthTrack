package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.wt.module.Meta;
import com.wt.module.ValorCripto;
import com.wt.module.particular.Particular;

public class TablaValorCripto {

	static final String NOM_TABLA = "ValorCripto";
	static final String NOM_COL_NOMBRE = "Nombre";
	static final String NOM_COL_IMPORTE = "Importe";

	private ConexionBBDD conBBDD;

	public TablaValorCripto() {
		conBBDD = new ConexionBBDD();
	}

	public int registrarValorCripto(ValorCripto valorCripto) {
		int resultado = 0;

		String query = "INSERT INTO " + NOM_TABLA + "(" + NOM_COL_NOMBRE + "," + NOM_COL_IMPORTE + ") VALUES (?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, valorCripto.getNombre());
			pstmt.setDouble(2, valorCripto.getImporte());

			
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

	public ArrayList<ValorCripto> consultarValorCripto() {

		ArrayList<ValorCripto> listaValorCripto = new ArrayList<ValorCripto>();

		String query = "SELECT * FROM " + NOM_TABLA;

		Connection con = null;
		Statement stmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			stmt = con.createStatement();

			rslt = stmt.executeQuery(query);

			while (rslt.next()) {
				listaValorCripto.add(new ValorCripto(rslt.getString(1), rslt.getDouble(2)));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rslt != null) {
					rslt.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaValorCripto;
	}
	
	public double consultarImporteCripto(String nomCripto) {

		double importeCripto = 0;

		String query = "SELECT " + NOM_COL_IMPORTE + " FROM " + NOM_TABLA + " WHERE " + NOM_COL_NOMBRE + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nomCripto);

			rslt = pstmt.executeQuery();

			
			if (rslt.next()) {
				importeCripto = rslt.getDouble(1);
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

		return importeCripto;
	}

}
