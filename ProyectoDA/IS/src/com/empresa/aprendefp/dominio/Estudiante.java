/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.aprendefp.dominio;

import java.util.Date;
import java.util.Objects;

/**
 * Clase para el concepto estudiante
 * @author HEMC
 */
public class Estudiante {

    private long id;
    private String nombre;
    private Date fechaNacimiento;
    private String matricula;
    private String correo;
    private int idPerfil;
    // Un objeto  estudiante esta asociado con un objeto perfil
    private Perfil perfil;
    
    public Estudiante(){
        
    }
    
    public Estudiante(long id, String nombre, Date fecha_nacimiento, String matricula, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fecha_nacimiento;
        this.matricula = matricula;
        this.correo = correo;
    }

        
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public int getidPerfil() {
        return idPerfil;
    }

    public void setidPerfil(int idperfil) {
        this.idPerfil = idperfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Estudiante other = (Estudiante) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }

    
    
}
