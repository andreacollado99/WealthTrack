package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wt.module.empresa.Empresa;

public class TablaEmpresa {

    static final String NOM_TABLA = "Empresa";
    static final String NOM_COL_ID = "id";
    static final String NOM_COL_NIF = "Nif";
    static final String NOM_COL_NOM = "Nombre";
    static final String NOM_COL_APE = "Apellido";
    static final String NOM_COL_EMAIL = "Email";
    static final String NOM_COL_FECHA_NAC = "FechaNac";
    static final String NOM_COL_CONTRA = "Contraseña";
    static final String NOM_COL_TEL = "Telefono";

    private ConexionBBDD conBBDD;

    public TablaEmpresa() {
        conBBDD = new ConexionBBDD();
    }

    public Empresa comprobarExiste(String email, String contrasenia) {
        Empresa empresa = null;

        String query = "SELECT * FROM " + NOM_TABLA + " WHERE " + NOM_COL_EMAIL + " = ? AND " + NOM_COL_CONTRA + " = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rslt = null;

        try {
            con = conBBDD.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, contrasenia);
            rslt = pstmt.executeQuery();

            if (rslt.next()) {
                empresa = new Empresa(
                	rslt.getInt(NOM_COL_ID),
                    rslt.getString(NOM_COL_NIF),
                    rslt.getString(NOM_COL_NOM),
                    rslt.getString(NOM_COL_APE),
                    rslt.getString(NOM_COL_EMAIL),
                    rslt.getString(NOM_COL_CONTRA),
                    rslt.getString(NOM_COL_FECHA_NAC),
                    rslt.getString(NOM_COL_TEL)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rslt != null) rslt.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empresa;
    }


    public int registrarEmpresa(Empresa empresaReg) {
        int resultado = 0;

        String query = "INSERT INTO " + NOM_TABLA + "(" + NOM_COL_NIF 
        		+ "," + NOM_COL_NOM + "," + NOM_COL_APE
                + "," + NOM_COL_FECHA_NAC + "," + NOM_COL_EMAIL
                + "," + NOM_COL_CONTRA + "," + NOM_COL_TEL + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = conBBDD.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empresaReg.getNif());
            pstmt.setString(2, empresaReg.getNombre());
            pstmt.setString(3, empresaReg.getApellido());
            pstmt.setString(4, empresaReg.getFechaNacimiento());
            pstmt.setString(5, empresaReg.getEmail());
            pstmt.setString(6, empresaReg.getContrasenia());
            pstmt.setString(7, empresaReg.getTelefono());

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

}
