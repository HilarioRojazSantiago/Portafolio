/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.aprendefp.dominio;

import java.util.Arrays;

/**
 * Clase para el concepto Perfil
 * @author HEMC
 */
public class Perfil {
     private long id;
     private String Usuario;
     private String Contraseña;
     private byte imagen[];
     private long idProgreso;
  // Un objeto perfil esta asociado con un objeto Progreso
     private Progreso ProgresoAlumno;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getidProgreso() {
        return idProgreso;
    }

    public void setidProgreso(long id) {
        this.idProgreso = id;
    }
    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }
    
    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        this.Contraseña = contraseña;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public Progreso getProgreso() {
        return ProgresoAlumno;
    }

    public void setProgresol(Progreso perfil) {
        this.ProgresoAlumno = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Perfil other = (Perfil) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Arrays.equals(this.imagen, other.imagen)) {
            return false;
        }
        return true;
    }
     
     
}
