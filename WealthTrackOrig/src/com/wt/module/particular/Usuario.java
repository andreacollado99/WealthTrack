package com.wt.module.particular;

public class Usuario {
    private String email;
    private String contrasenia;

    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
