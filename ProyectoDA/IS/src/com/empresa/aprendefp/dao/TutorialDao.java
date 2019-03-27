package com.empresa.aprendefp.dao;

import com.empresa.aprendefp.dominio.Perfil;
import com.empresa.aprendefp.dominio.Tutorial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorialDao extends ConceptoDao {
	
	/*
     * Función que retorna un Elemento de tipo tutorial especifico
     */
	public Tutorial consultar(long tema, long tuto) {
		Tutorial tutorial = null;
        try {
        	System.out.println("correct");
            // se crea una consulta SQL
            PreparedStatement stmtConsulta = connection.prepareStatement(
                    "SELECT * FROM Usuarios.Tutoriales");
            // se ejecuta la consulta;
            ResultSet rs = stmtConsulta.executeQuery();
            
            // se recorre la lista obtenida y se busca el objeto Tutorial
            //se crea un objeto Tutorial y se le inyectan los valores retornados
            // por la consulta
            while (rs.next()) {
            	if(rs.getLong("#Tema")==tema && rs.getLong("#Tutorial")==tuto) {
            		tutorial = new Tutorial();
                	tutorial.setId(rs.getLong("id"));
                	tutorial.setNumTema((int)rs.getLong("#Tema"));
                	tutorial.setNumTuto((int)rs.getLong("#Tutorial"));
                	tutorial.setTitulo(rs.getString("Titulo"));
                	tutorial.setTuto(rs.getString("Tutorial"));
            	}
            	else {
            		continue;
            	}
            	
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        //Se retorna el tutorial deseado
        return tutorial;
    }
	
}
