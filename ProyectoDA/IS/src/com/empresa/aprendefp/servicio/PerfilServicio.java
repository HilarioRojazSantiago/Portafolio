package com.empresa.aprendefp.servicio;

import com.empresa.aprendefp.dao.PerfilDao;
import com.empresa.aprendefp.dominio.Perfil;
import com.empresa.aprendefp.util.ConnectionManager;
import java.sql.Connection;
import java.util.List;

/**
 * Clase para realizar operaciones CRUD con objetos de tipo Perfil
 *
 * @author HEMC
 */
public class PerfilServicio {

    // se declara una referencia para apuntar a un objeto de tipo PerfilDao
    private PerfilDao perfilDao;

    public PerfilServicio() {
        // se crea un objeto de tipo PerfilDao
        perfilDao = new PerfilDao();
    }

    /*
    * Función que retorna un objeto de tipo Perfil a partir de su id
     */
    public Perfil consultar(long id) {

        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método consultar del dao
        Perfil perfil = perfilDao.consultar(id);

        // se desconecta de la base
        perfilDao.disconnect();
        return perfil;
    }

    /*
    * Función que retorna una lista de objetos de tipo Perfil
     */
    public List<Perfil> consultar() {
        
        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método consultar del dao
        List<Perfil> perfils = perfilDao.consultar();

        // se desconecta de la base
        perfilDao.disconnect();
        return perfils;
    }

    /*
    * Función que guarda en la base un objeto de tipo Perfil
    */
    public boolean crear(Perfil perfil) {

       
        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método crear del dao
        boolean resultado = perfilDao.crear(perfil);

        // se desconecta de la base
        perfilDao.disconnect();
        return resultado;

    }
    
    /*
     * Función que verifica si existe el Perfil en la base de datos
     */
    public boolean existe(Perfil perfil) {

        
        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método existe del dao
        boolean resultado = perfilDao.existe(perfil);

        // se desconecta de la base
        perfilDao.disconnect();
        return resultado;

    }
    
    public int ObteneID(Perfil perfil) {

        
        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método ObtenerID del dao
        int resultado = perfilDao.ObtenerID(perfil);

        // se desconecta de la base
        perfilDao.disconnect();
        return resultado;

    }

     /*
    * Función que actualiza en la base un objeto de tipo Perfil
    */
    public boolean actualizar(Perfil perfil) {
        
        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método actualizar del dao
        boolean resultado = perfilDao.actualizar(perfil);

        // se desconecta de la base
        perfilDao.disconnect();
        return resultado;
    }

     /*
    * Función que elimina en la base un objeto de tipo Perfil
    */
    public boolean eliminar(Long id) {

        // asigna la conexión al objeto dao
        perfilDao.connect();

        // invoca al método eliminar del dao
        boolean resultado = perfilDao.eliminar(id);

        // se desconecta de la base
        perfilDao.disconnect();
        return resultado;

    }

}
