package com.empresa.aprendefp.servicio;

import java.util.List;

import com.empresa.aprendefp.dao.PerfilDao;
import com.empresa.aprendefp.dao.ProgresoDao;
import com.empresa.aprendefp.dominio.Perfil;
import com.empresa.aprendefp.dominio.Progreso;

public class ProgresoServicio {
	
	// se declara una referencia para apuntar a un objeto de tipo ProgresoDao
	private ProgresoDao progresoDao;
	
	public ProgresoServicio() {
        // se crea un objeto de tipo ProgresoDao
		progresoDao = new ProgresoDao();
    }
	
	/*
     * Función para modificar un elemento de tipo Perfil
     */
	public boolean modificarTutos(long id, long nTuto) {

        // asigna la conexión al objeto dao
		progresoDao.connect();

        // invoca al método consultar del dao
		boolean perfil = progresoDao.modificarTutos(id,nTuto);

        // se desconecta de la base
        progresoDao.disconnect();
        return perfil;
    }
	
	/*
     * Función para modificar un elemento de tipo Perfil
     */
	public boolean modificarEjer(long id, long nEjer) {

        // asigna la conexión al objeto dao
		progresoDao.connect();

        // invoca al método consultar del dao
		boolean perfil = progresoDao.modificarEjer(id,nEjer);

        // se desconecta de la base
        progresoDao.disconnect();
        return perfil;
    }
	
	/*
     * Función para guardar en la base de datos un objeto de tipo Progreso
     */
	public boolean crear(Progreso perfil) {

        // asigna la conexión al objeto dao
		progresoDao.connect();

        // invoca al método crear del dao
        boolean resultado = progresoDao.crear(perfil);

        // se desconecta de la base
        progresoDao.disconnect();
        return resultado;

    }
	
	/*
     * Función que retorna el id del ultimo elemento Progreso agregado a la base de datos
     */
	public long consultar() {

        // asigna la conexión al objeto dao
		progresoDao.connect();

        // invoca al método consultar del dao
        long id = progresoDao.consultar();

        // se desconecta de la base
        progresoDao.disconnect();
        return id;
    }
	
	/*
     * Función que retorna una lista que contiene todos los elementos de tipo Progreso
     */
	public List<Progreso> consultarTodo() {
        
        // asigna la conexión al objeto dao
		progresoDao.connect();

        // invoca al método consultar del dao
        List<Progreso> perfils = progresoDao.consultarTodo();

        // se desconecta de la base
        progresoDao.disconnect();
        return perfils;
    }
}
