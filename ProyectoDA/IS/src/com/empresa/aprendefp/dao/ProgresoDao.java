package com.empresa.aprendefp.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;

import com.empresa.aprendefp.dominio.Perfil;
import com.empresa.aprendefp.dominio.Progreso;

public class ProgresoDao extends ConceptoDao {
	
	/*
     * Función para modificar un elemento de tipo Perfil
     */
	public boolean modificarTutos(long id, long nTuto) {
		boolean perfil = false;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
            		"UPDATE Usuarios.Progreso SET TutoResueltos = ? WHERE ID= ?",Statement.RETURN_GENERATED_KEYS);
            // se inyecta un parámetro en la posición 1
            stmtConsulta.clearParameters();
            stmtConsulta.setLong(1, nTuto);
            stmtConsulta.setLong(2, id);
            // se ejecuta la consulta;
            stmtConsulta.executeUpdate();
            perfil = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
      //Se retorna un booleano para confirmar que se modifico el elemento progreso
        return perfil;
    }
	
	/*
     * Función para modificar un elemento de tipo Perfil
     */
	public boolean modificarEjer(long id, long nEjer) {
		boolean perfil = false;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
            		"UPDATE Usuarios.Progreso SET EjerResueltos = ? WHERE ID= ?",Statement.RETURN_GENERATED_KEYS);
            // se inyecta un parámetro en la posición 1
            stmtConsulta.clearParameters();
            stmtConsulta.setLong(1, nEjer);
            stmtConsulta.setLong(2, id);
            // se ejecuta la consulta;
            stmtConsulta.executeUpdate();
            perfil = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
      //Se retorna un booleano para confirmar que se modifico el elemento progreso
        return perfil;
    }
	
	/*
     * Función para guardar en la base de datos un objeto de tipo Progreso
     */
	public boolean crear(Progreso perfil) {
        boolean bCreated = false;
        try {
        	// se crea una consulta SQL
            PreparedStatement stmtCreate = connection.prepareStatement(
                    "INSERT INTO Usuarios.Progreso (EJERRESUELTOS,TUTORESUELTOS)"
                    + "VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            //se insertan los elementos
            stmtCreate.clearParameters();
            stmtCreate.setLong(1, perfil.getNumEjer());
            stmtCreate.setLong(2, perfil.getNumTutos());
         // se ejecuta la consulta;
            stmtCreate.executeUpdate();
            ResultSet results = stmtCreate.getGeneratedKeys();
            if (results.next()) {
                perfil.setId(results.getLong(1));
            }
            bCreated = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgresoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      //Se retorna un booleano para confirmar que se creo el elemento progreso
        return bCreated;
    }
	
	/*
     * Función que retorna el id del ultimo elemento Progreso agregado a la base de datos
     */
	public long consultar() {
        long id=0;
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT ID FROM Usuarios.Progreso");
            // se ejecuta la consulta;
            ResultSet rs = stmtConsulta.executeQuery();
            
            // se obtiene el id del ultimo elemento Progreso agregado a la base de datos
            while (rs.next()) {
            	id=rs.getLong("id");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
     // se retorna el id del ultimo elemento Progreso agregado a la base de datos
        return id;
    }
	
	/*
     * Función que retorna una lista que contiene todos los elementos de tipo Progreso
     */
	public List<Progreso> consultarTodo() {
        List<Progreso> progresos = new ArrayList<Progreso>();
        try {
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT * FROM Usuarios.Progreso");
            // se ejecuta la consulta
            ResultSet rs = stmtConsulta.executeQuery();
            Progreso progreso;
            // por cada registro de la respuesta se crea un objeto y se le inyectan los valores
            // del registro
            while (rs.next()) {
            	progreso = new Progreso();
            	progreso.setId(rs.getLong("id"));
            	progreso.setNumEjer(rs.getInt("EjerResueltos"));
            	progreso.setNumTutos(rs.getInt("TutoResueltos"));
            	progresos.add(progreso);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        //Se retorna una lista que contiene todos los elementos de tipo Progreso
        return progresos;
    }

}
