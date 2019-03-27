/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.aprendefp.dao;

import com.empresa.aprendefp.dominio.Perfil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;

/**
 * Clase que implementa operaciones CRUD con la base por medio de consultas SQL
 * @author HEMC
 */
public class PerfilDao extends ConceptoDao {

    /**
     * Función para obtener un objeto perfil por su id
     */
    public Perfil consultar(long id) {
        Perfil perfil = null;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID,USUARIO,IMAGEN FROM Usuarios.PERFIL WHERE id=?");
            // se inyecta un parámetro en la posición 1
            stmtConsulta.setLong(1, id);
            // se ejecuta la consulta;
            ResultSet rs = stmtConsulta.executeQuery();
            
            // si se obtiene un resultado se crea un objeto Perfil y se le inyectan los valores retornados
            // por la consulta
            if (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getLong("id"));
                // se transforma el resultado de tipo Blob a byte[]
                Blob imagen = rs.getBlob("imagen");
                if (imagen != null) {
                    perfil.setImagen(imagen.getBytes(1, (int) imagen.length()));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return perfil;
    }

    
    /**
     * Función para obtener una lista de objetos perfil 
     */
    public List<Perfil> consultar() {
        List<Perfil> perfiles = new ArrayList<Perfil>();
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID,USUARIO,CONTRASEÑA,IMAGEN,PROGRESO_ID FROM Usuarios.PERFIL");
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            Perfil perfil;
            // por cada registro de la respuesta se crea un objeto y se le inyectan los valores
            // del registro
            while (rs.next()) {
                perfil = new Perfil();
                perfil.setId(rs.getLong("id"));
                perfil.setUsuario(rs.getString("usuario"));
                perfil.setContraseña(rs.getString("contraseña"));
                perfil.setidProgreso(rs.getLong("Progreso_ID"));
                // se transforma el resultado de tipo Blob a byte[]
                Blob imagen = rs.getBlob("imagen");
                if (imagen != null) {
                    perfil.setImagen(imagen.getBytes(1, (int) imagen.length()));
                }
                perfiles.add(perfil);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return perfiles;
    }
    
    /*
     * Función que verifica si existe el Perfil en la base de datos
     */
    public boolean existe(Perfil perfil) {
    	boolean ex=false;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID,USUARIO,CONTRASEÑA,IMAGEN FROM Usuarios.PERFIL");
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            // se recorren los registros del resultado retorna y se comparan los usuarios
            while (rs.next()) {
                if(perfil.getUsuario().contains(rs.getString("usuario"))==true) {
                	ex=true;
                	break;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return ex;
    }
    
    public int ObtenerID(Perfil perfil) {
    	int ex = 0;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID,USUARIO,CONTRASEÑA,IMAGEN FROM Usuarios.PERFIL");
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            // Se busca un Perfil en especifico segun su usuario
            while (rs.next()) {
                if(perfil.getUsuario().contains(rs.getString("usuario"))==true) {
                	ex=rs.getInt("id");
                	break;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        //Se retorna el ID del perfil encontrado
        return ex;
    }

    /*
     * Función que elimina el perfil de la base de datos
     */
    public boolean eliminar(Long id) {
        boolean bDeleted = false;

        try {
        	// se crea una consulta SQL
            PreparedStatement stmtDeleteAddress = connection.
                    prepareStatement("DELETE FROM Usuarios.PERFIL WHERE ID = ?");
            //se inyectan los paramets a la base de datos
            stmtDeleteAddress.clearParameters();
            stmtDeleteAddress.setLong(1, id);
            // se ejecuta la consulta
            stmtDeleteAddress.executeUpdate();
            bDeleted = true;

        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se retorna un booleano para confirmar que se elemino el perfil
        return bDeleted;

    }

    /*
     * Función para guardar en la base de datos un objeto de tipo Perfil
     */
    public boolean crear(Perfil perfil) {
        boolean bCreated = false;
        try {
        	// se crea una consulta SQL
            PreparedStatement stmtCreate = connection.prepareStatement(
                    "INSERT INTO Usuarios.PERFIL (USUARIO,CONTRASEÑA,IMAGEN,PROGRESO_ID)"
                    + "VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            //se insertan los elementos
            stmtCreate.clearParameters();
            if (perfil.getImagen()!=null) {
                Blob imagen = new SerialBlob(perfil.getImagen());
                stmtCreate.setBlob(3, imagen);
            }  else {                       
                stmtCreate.setNull(3, Types.BLOB);
            }
            stmtCreate.setString(1, perfil.getUsuario());
            stmtCreate.setString(2, perfil.getContraseña());
            stmtCreate.setLong(4, perfil.getidProgreso());
            
            stmtCreate.executeUpdate();
            ResultSet results = stmtCreate.getGeneratedKeys();
            if (results.next()) {
                perfil.setId(results.getLong(1));
            }
            bCreated = true;
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      //Se retorna un booleano para confirmar que se creo el perfil
        return bCreated;
    }

    public boolean actualizar(Perfil perfil) {
        boolean bEdited = false;
        try {
            PreparedStatement stmtUpdate = connection.prepareStatement(
                    "UPDATE Usuarios.ESTUDIANTE "
                    + " SET IMAGEN=? "
                    + " WHERE ID=?",
                    Statement.RETURN_GENERATED_KEYS);

            stmtUpdate.clearParameters();
            if (perfil.getImagen()!=null) {
                Blob imagen = new SerialBlob(perfil.getImagen());
                stmtUpdate.setBlob(1, imagen);
            }  else {                       
                stmtUpdate.setNull(1, Types.BLOB);
            }               
            stmtUpdate.executeUpdate();
            bEdited = true;
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bEdited;

    }
}
