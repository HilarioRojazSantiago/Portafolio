/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.aprendefp.dao;

import com.empresa.aprendefp.dominio.Estudiante;
import com.empresa.aprendefp.dominio.Perfil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa operaciones CRUD con la base por medio de consultas SQL
 * @author HEMC
 */
public class EstudianteDao extends ConceptoDao{
    /**
     * Función que retorna un objeto Estudiante desde la base de datos por su id 
     */
    public Estudiante consultar(long id) {
        Estudiante estudiante = null;
        try {
            // se crea la consulta
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID, NOMBRE, FECHA_NACIMIENTO, MATRICULA, CORREO, PERFIL_ID FROM Usuarios.Estudiante WHERE id=?");
            // se inyecta el parámetro id
            stmtConsulta.setLong(1, id);
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            // si se obtiene un registro se crea el objeto Estudiante y se inyectan los valores
            if (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setId(rs.getLong("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setMatricula(rs.getString("matricula"));
                estudiante.setCorreo(rs.getString("correo"));
                // si el valor de la clave foránea existe se crea un objeto Perfil y se asigna el id
                long perfilId = rs.getLong("perfil_id");
                System.out.println("Dao"+perfilId);
                estudiante.setidPerfil((int)perfilId);
                if (!rs.wasNull()) {
                    estudiante.setPerfil(new Perfil());
                    estudiante.getPerfil().setId(perfilId);
                }               
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return estudiante;
    }

    /*
    * Función que retorna una lista de objetos estudiante consultados desde la base de datos
    */
     public List<Estudiante> consultar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try {
              // se crea la consulta
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID, NOMBRE, FECHA_NACIMIENTO, MATRICULA, CORREO, PERFIL_ID FROM Usuarios.ESTUDIANTE");
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            Estudiante estudiante;
            // se recorren los registros del resultado retorna y se crea un alista de estudiantes
            while (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setId(rs.getLong("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setMatricula(rs.getString("matricula"));
                estudiante.setCorreo(rs.getString("correo"));
                
                // si el valor de la clave foránea existe se crea un objeto Perfil y se asigna el id
                long perfilId = rs.getLong("perfil_id");
                estudiante.setidPerfil((int)perfilId);
                if (!rs.wasNull()) {
                    estudiante.setPerfil(new Perfil());
                    estudiante.getPerfil().setId(perfilId);
                }   
                
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return estudiantes;
    }
     
     /*
      * Función que verifica si existe el Estudiante en la base de datos
      */
     public boolean existe(Estudiante nuevoEstudiante) {
    	 boolean ex=false;
         try {
               // se crea la consulta
             PreparedStatement stmtConsulta = connection.prepareStatement(
                     "SELECT ID, NOMBRE, FECHA_NACIMIENTO, MATRICULA, CORREO FROM Usuarios.ESTUDIANTE");
             // se ejecuta la consulta
             ResultSet rs = stmtConsulta.executeQuery();
             // se recorren los registros del resultado retorna y se comparan las matriculas
             while (rs.next()) {
                 if(nuevoEstudiante.getMatricula().equals(rs.getString("matricula"))==true ) {
                	 ex=true;
                	 break;
                 }
             }
         } catch (SQLException e) {
             System.err.println(e);
         }
         return ex;
     }
    
    /*
     * Función que elimina un registro de Estudiante en la base de datos
     */
    public boolean eliminar(Long id) {
        boolean bDeleted = false;

        try {
            // se crea la consulta
            PreparedStatement stmtDeleteAddress = connection.
                    prepareStatement("DELETE FROM Usuarios.ESTUDIANTE WHERE ID = ?");

            // se inyecta el parámetro id en la consulta
            stmtDeleteAddress.clearParameters();
            stmtDeleteAddress.setLong(1, id);
            // se ejecuta la consulta
            stmtDeleteAddress.executeUpdate();
            bDeleted = true;

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bDeleted;

    }

    /*
    * Función para guardar en la base de datos un objeto de tipo Estudiante
    */
    public boolean crear(Estudiante estudiante) {
        boolean bCreated = false;
        try {
              // se crea la consulta
            PreparedStatement stmtCreate = connection.prepareStatement(
                    "INSERT INTO Usuarios.ESTUDIANTE "
                    + "   (NOMBRE, FECHA_NACIMIENTO, "
                    + "    MATRICULA, CORREO, PERFIL_ID) "
                    + "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            // se inyectan los parámetros
            stmtCreate.clearParameters();
            stmtCreate.setString(1, estudiante.getNombre());
            stmtCreate.setDate(2, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmtCreate.setString(3, estudiante.getMatricula());
            stmtCreate.setString(4, estudiante.getCorreo());
            stmtCreate.setInt(5, estudiante.getidPerfil());
            
            // se ejecuta la consulta
            stmtCreate.executeUpdate();
            ResultSet results = stmtCreate.getGeneratedKeys();
            if (results.next()) {
                estudiante.setId(results.getLong(1));
            }
            bCreated = true;
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bCreated;
    }

    /*
    * Función para actualizar los datos en la base de un objeto de tipo Estudiante
    */
    public boolean actualizar(Estudiante estudiante) {
        boolean bEdited = false;
        try {
              // se crea la consulta
            PreparedStatement stmtUpdate = connection.prepareStatement(
                    "UPDATE Usuarios.ESTUDIANTE "
                    + " SET NOMBRE=?, "
                    + " FECHA_NACIMIENTO=?,"
                    + " MATRICULA=?,"
                    + " CORREO=? "
                    + " PERFIL_ID=? "
                    + " WHERE ID=?",
                    Statement.RETURN_GENERATED_KEYS);

            // se inyectan los parámetros
            stmtUpdate.clearParameters();
            stmtUpdate.setString(1, estudiante.getNombre());
            stmtUpdate.setDate(2, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmtUpdate.setString(3, estudiante.getMatricula());
            stmtUpdate.setString(4, estudiante.getCorreo());
            
            // Si el estudiante tiene un perfil se inyecta la clave foránea correspondiente
            if (estudiante.getPerfil()!=null) {
                stmtUpdate.setLong(5, estudiante.getPerfil().getId());
            } else {
                stmtUpdate.setNull(5, Types.INTEGER);
            }
            
            stmtUpdate.setLong(6, estudiante.getId());
            
            // se ejecuta la consulta
            stmtUpdate.executeUpdate();
            bEdited = true;
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bEdited;

    }
}
