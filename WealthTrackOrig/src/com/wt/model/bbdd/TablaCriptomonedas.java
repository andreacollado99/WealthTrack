package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.wt.module.Cripto;
import com.wt.module.Meta;
import com.wt.module.ValorCripto;
import com.wt.module.particular.Particular;

public class TablaCriptomonedas {

	public static final String NOM_TABLA = "Criptomonedas";
	public static final String NOM_COL_ID_CRIPTO = "IdCripto";
	public static final String NOM_COL_NOMBRE = "NombreCripto";
	public static final String NOM_COL_IMPORTE_CRIPTO = "ImporteCripto";
	public static final String NOM_COL_IMPORTE_EURO = "ImporteEuro";
	public static final String NOM_COL_EMAIL_USU = "emailUsuario";

	private ConexionBBDD conBBDD;

	public TablaCriptomonedas() {
		conBBDD = new ConexionBBDD();
	}

	public int registrarCriptomoneda(Cripto cripto) {
		int resultado = 0;

		String query = "INSERT INTO " + NOM_TABLA + "(" + NOM_COL_NOMBRE + "," + NOM_COL_IMPORTE_CRIPTO + "," + NOM_COL_IMPORTE_EURO + "," + NOM_COL_EMAIL_USU + ") VALUES (?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, cripto.getNombreCripto());
			pstmt.setDouble(2, cripto.getImporteEnCripto());
			pstmt.setDouble(3, cripto.getImporteEnEuro());
			pstmt.setString(4, cripto.getemailUsuario());

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

	public ArrayList<Cripto> consultarCriptomonedas(String usuario) {

		ArrayList<Cripto> listaCriptos = new ArrayList<Cripto>();

		String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_EMAIL_USU + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, usuario);

			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				listaCriptos.add(new Cripto(rslt.getInt(1), rslt.getString(2), rslt.getDouble(3), rslt.getDouble(4), rslt.getString(5)));
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

		return listaCriptos;
	}

	public int eliminarCripto(int idCripto) {
		int resultado = 0;

		String query = "DELETE FROM " + NOM_TABLA + " WHERE " + NOM_COL_ID_CRIPTO + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idCripto);
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

	public Cripto comprobarExiste(String nombreCripto, String email) {

		Cripto cripto = null;

		String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_EMAIL_USU + " = ? AND " + NOM_COL_NOMBRE + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, nombreCripto);

			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				cripto = new Cripto(rslt.getInt(1), rslt.getString(2), rslt.getDouble(3), rslt.getDouble(4), rslt.getString(5));
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

		return cripto;
	}

	public Cripto consultarCriptomoneda(int idCriptoSel) {
		Cripto cripto = null;

		String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_ID_CRIPTO + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idCriptoSel);

			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				cripto = new Cripto(rslt.getInt(1), rslt.getString(2), rslt.getDouble(3), rslt.getDouble(4), rslt.getString(5));
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

		return cripto;
		}

	public int modificarMovimiento(int idCriptoSel, double importeEur, double importeCript) {
		int resultado = 0;

		String query = "UPDATE " + NOM_TABLA + " SET " + NOM_COL_IMPORTE_EURO + " = ?, "+ NOM_COL_IMPORTE_CRIPTO + " = ? " + " WHERE " + NOM_COL_ID_CRIPTO + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, importeEur);
			pstmt.setDouble(2, importeCript);
			pstmt.setInt(3, idCriptoSel);
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
