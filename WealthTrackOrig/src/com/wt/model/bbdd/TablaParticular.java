package com.wt.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wt.module.particular.Particular;

public class TablaParticular {

    static final String NOM_TABLA = "Particular";
    static final String NOM_COL_ID = "id";
    static final String NOM_COL_NOM = "Nombre";
    static final String NOM_COL_APE = "Apellido";
    static final String NOM_COL_DNI = "DNI";
    static final String NOM_COL_EMAIL = "Email";
    static final String NOM_COL_FECHA_NAC = "FechaNac";
    static final String NOM_COL_CONTRA = "Contraseña";
    static final String NOM_COL_TEL = "Telefono";

    private ConexionBBDD conBBDD;

    public TablaParticular() {
        conBBDD = new ConexionBBDD();
    }

    public Particular comprobarExiste(String email, String contrasenia) {
    	Particular particular = null;

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
                particular = new Particular(
                    rslt.getInt(NOM_COL_ID),
                    rslt.getString(NOM_COL_NOM),
                    rslt.getString(NOM_COL_APE),
                    rslt.getString(NOM_COL_DNI),
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
        return particular;
    }


    public int registrarParticular(Particular usuarioReg) {
        int resultado = 0;

        String query = "INSERT INTO " + NOM_TABLA + "(" + NOM_COL_NOM
                + "," + NOM_COL_APE + "," + NOM_COL_DNI
                + "," + NOM_COL_FECHA_NAC + "," + NOM_COL_EMAIL
                + "," + NOM_COL_CONTRA + "," + NOM_COL_TEL + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = conBBDD.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, usuarioReg.getNombre());
            pstmt.setString(2, usuarioReg.getApellido());
            pstmt.setString(3, usuarioReg.getDni());
            pstmt.setString(4, usuarioReg.getFechaNacimiento());
            pstmt.setString(5, usuarioReg.getEmail());
            pstmt.setString(6, usuarioReg.getContrasenia());
            pstmt.setString(7, usuarioReg.getTelefono());

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
