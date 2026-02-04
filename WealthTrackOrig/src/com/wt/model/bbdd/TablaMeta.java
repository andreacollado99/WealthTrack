package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.wt.module.Cripto;
import com.wt.module.Meta;

public class TablaMeta {


	static final String NOM_TABLA = "Meta";
	static final String NOM_COL_META_ID = "Id_Meta";
	static final String NOM_COL_ID_USUARIO = "Id_Usuario";
	static final String NOM_COL_CANTIDAD_OBJETIVO = "Cantidad_Objetivo";
	static final String NOM_COL_DESCRIPCION = "Descripcion";
	static final String NOM_COL_PROGRESO_ACTUAL = "Progreso_Actual";

    
	private ConexionBBDD conBBDD;
	 
	public TablaMeta( ) {
		conBBDD = new ConexionBBDD();
	}
	
	public ArrayList<Meta> obtenerTodasLasMetas() {
		ArrayList<Meta> listaMetas = new ArrayList<>();

	    String query = "SELECT " +
	            NOM_COL_META_ID + ", " +
	            NOM_COL_ID_USUARIO + ", " +
	            NOM_COL_CANTIDAD_OBJETIVO + ", " +
	            NOM_COL_DESCRIPCION + ", " +
	            NOM_COL_PROGRESO_ACTUAL +
	            " FROM " + NOM_TABLA;

	    Connection con = null;
	    Statement stmt = null;
	    ResultSet rslt = null;

	    try {
	        con = conBBDD.getConexion();
	        stmt = con.createStatement();
	        rslt = stmt.executeQuery(query);

	        while (rslt.next()) {
	            Meta meta = new Meta(
	                rslt.getInt(NOM_COL_META_ID),
	                rslt.getInt(NOM_COL_ID_USUARIO),
	                rslt.getDouble(NOM_COL_CANTIDAD_OBJETIVO),
	                rslt.getString(NOM_COL_PROGRESO_ACTUAL),
	                rslt.getString(NOM_COL_DESCRIPCION)
	            );
	            listaMetas.add(meta);
	        }
	  
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rslt != null) rslt.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return listaMetas;
	}
	
	public int registrarMeta(Meta metaReg) {
	    int resultado = 0;

	    String query = "INSERT INTO " + NOM_TABLA + " (" +
	            NOM_COL_DESCRIPCION + ", " +
	            NOM_COL_CANTIDAD_OBJETIVO + ", " +
	            NOM_COL_PROGRESO_ACTUAL + ", " +
	            NOM_COL_ID_USUARIO + ") VALUES (?, ?, ?, ?)";

	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = conBBDD.getConexion();
	        pstmt = con.prepareStatement(query);

	        // Asignar los valores
	        pstmt.setString(1, metaReg.getDescripcion());
	        pstmt.setDouble(2, metaReg.getCantidadObjetivo());

	        // Validar el estado de progreso actual
	        String progreso = metaReg.getProgresoActual();
	        if (progreso == null || 
	            (!progreso.equalsIgnoreCase("EN PROCESO") && !progreso.equalsIgnoreCase("FINALIZADO"))) {
	            progreso = "EN PROCESO"; // Valor por defecto
	        }
	        pstmt.setString(3, progreso);

	        pstmt.setInt(4, metaReg.getUsuarioId());

	        resultado = pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return resultado;
	}


	public ArrayList<Meta> obtenerMetasPorUsuario(int usuarioId) {
		ArrayList<Meta> listaMetas = new ArrayList<>();

	    String query = "SELECT " + NOM_COL_META_ID 
	    		+ ", " + NOM_COL_ID_USUARIO 
	    		+ ", " + NOM_COL_CANTIDAD_OBJETIVO 
	    		+ ", " + NOM_COL_DESCRIPCION 
	    		+ ", " + NOM_COL_PROGRESO_ACTUAL 
	    		+ " FROM " + NOM_TABLA + " WHERE " + NOM_COL_ID_USUARIO + " = ?";
	    
	    Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rslt = null;
        
	    try {
	    	
	    	con = conBBDD.getConexion();
	        pstmt = con.prepareStatement(query);
	        pstmt.setInt(1, usuarioId);
	        rslt = pstmt.executeQuery();
	        
            while (rslt.next()) {
                Meta meta = new Meta(
                        rslt.getInt(NOM_COL_META_ID),
                        rslt.getInt(NOM_COL_ID_USUARIO),
                        rslt.getDouble(NOM_COL_CANTIDAD_OBJETIVO),
                        rslt.getString(NOM_COL_PROGRESO_ACTUAL),
                        rslt.getString(NOM_COL_DESCRIPCION)
                );
                
                listaMetas.add(meta);
            }
       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listaMetas;
	}
	
	public int eliminarMeta(int metaId) {
		int resultado = 0;

		String query = "DELETE FROM " + NOM_TABLA + " WHERE " + NOM_COL_META_ID + " = " + metaId;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
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

	public int actualizarProgreso(int metaId, String progreso) {
		int resultado = 0;

		String query = "UPDATE " + NOM_TABLA + " SET " + NOM_COL_PROGRESO_ACTUAL + " = ? WHERE " + NOM_COL_META_ID + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, progreso);
			pstmt.setInt(2, metaId);
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

	public Meta obtenerMeta(int idMetaSel) {
		Meta meta = null;

		String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_META_ID + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null; // la sentencia esta completa
		ResultSet rslt = null;

		try {
			con = conBBDD.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idMetaSel);

			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				meta = new Meta(rslt.getInt(1), rslt.getInt(2), rslt.getDouble(3), rslt.getString(4), rslt.getString(5));
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

		return meta;
		}

	public int modificarMeta(int idMetaSel, double importe, String progreso) {
		int resultado = 0;

		String query = "UPDATE " + NOM_TABLA + " SET " + NOM_COL_CANTIDAD_OBJETIVO + " = ?, "+ NOM_COL_PROGRESO_ACTUAL + " = ? " + " WHERE " + NOM_COL_META_ID + " = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conBBDD.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, importe);
			pstmt.setString(2, progreso);
			pstmt.setInt(3, idMetaSel);
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
