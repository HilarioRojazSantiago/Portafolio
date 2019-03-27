package com.empresa.aprendefp.servicio;

import com.empresa.aprendefp.dao.EstudianteDao;
import com.empresa.aprendefp.dao.PerfilDao;
import com.empresa.aprendefp.dominio.Estudiante;
import com.empresa.aprendefp.util.ConnectionManager;
import java.sql.Connection;
import java.util.List;

/**
 * Clase para realizar operaciones CRUD con objetos de tipo Estudiante
 *
 * @author HEMC
 */
public class EstudianteServicio {

    // se declara una referencia para apuntar a un objeto de tipo EstudianteDao
    private EstudianteDao estudianteDao;
    // se declara una referencia para apuntar a un objeto de tipo PerfilDao
    private PerfilDao perfilDao;

    public EstudianteServicio() {
        // se crea un objeto de tipo EstudianteDao
        estudianteDao = new EstudianteDao();

        // se crea un objeto de tipo PerfilDao
        perfilDao = new PerfilDao();
    }

    /*
    * Función que retorna un objeto de tipo Estudiante a partir de su id
     */
    public Estudiante consultar(long id) {

        // obtiene una conexión a la base
        estudianteDao.connect();
        
        // se invoca al método consultar del objeto dao
        Estudiante estudiante = estudianteDao.consultar(id);

        // se desconecta de la base
        estudianteDao.disconnect();
        
        return estudiante;
    }

    /*
    * Función que retorna una lista de objetos de tipo Estudiante
     */
    public List<Estudiante> consultar() {
        
        // asigna la conexión al objeto dao
        estudianteDao.connect();

        // se invoca al método consultar del objeto dao
        List<Estudiante> estudiantes = estudianteDao.consultar();

        // se desconecta de la base
        estudianteDao.disconnect();
        
        return estudiantes;
    }
    
    //Funcion que retorna un booleano dependiendo si ya existe el estudiante que se intenta crear.
    public boolean existe(Estudiante estudiante) {
        
        // asigna la conexión al objeto dao
        estudianteDao.connect();

        // se invoca al método existe del objeto dao
        boolean resultado = estudianteDao.existe(estudiante);

        // se desconecta de la base
        estudianteDao.disconnect();
        return resultado;

    }

    /*
    * Función que guarda en la base un objeto de tipo Estudiante
     */
    public boolean crear(Estudiante estudiante) {
       
        // asigna la conexión al objeto dao
        estudianteDao.connect();

        // se invoca al método crear del objeto dao
        boolean resultado = estudianteDao.crear(estudiante);

        // se desconecta de la base
        estudianteDao.disconnect();
        return resultado;

    }

    /*
    * Función que guarda en la base un objeto de tipo Estudiante,
    * así como a su objeto Perfil asociado
     */
    public boolean crearCascada(Estudiante estudiante) {
        
        // asigna la conexión al objeto dao
        estudianteDao.connect();
        // asigna la conexión al objeto perfilDao
        perfilDao.setConnection(estudianteDao.getConnection());

        boolean resultado = false;
        // se invoca al método crear del objeto perfilDao, y se crea un objeto perfil
        if (estudiante.getPerfil() != null) {
            resultado = perfilDao.crear(estudiante.getPerfil());
        }

        // se invoca al método crear del objeto estudianteDao y se crea el objeto estudiante
        if (resultado) {
            resultado = estudianteDao.crear(estudiante);
        }

        // se desconecta de la base
       estudianteDao.disconnect();
        return resultado;

    }

    /*
    * Función que actualiza en la base un objeto de tipo Estudiante
     */
    public boolean actualizar(Estudiante estudiante) {
        // asigna la conexión al objeto dao
        estudianteDao.connect();

        // se invoca al método actualizar del objeto dao
        boolean resultado = estudianteDao.actualizar(estudiante);

        // se desconecta de la base
        estudianteDao.disconnect();
        return resultado;
    }

    /*
    * Función que elimina en la base un objeto de tipo Estudiante
     */
    public boolean eliminar(Long id) {
        
        // asigna la conexión al objeto dao
        estudianteDao.connect();

        // se invoca al método eliminar del objeto dao
        boolean resultado = estudianteDao.eliminar(id);

        // se desconecta de la base
        estudianteDao.disconnect();
        return resultado;

    }

}
