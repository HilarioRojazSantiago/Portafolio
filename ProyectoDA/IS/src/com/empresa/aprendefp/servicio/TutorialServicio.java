package com.empresa.aprendefp.servicio;

import com.empresa.aprendefp.dao.EstudianteDao;
import com.empresa.aprendefp.dao.PerfilDao;
import com.empresa.aprendefp.dao.TutorialDao;
import com.empresa.aprendefp.dominio.Estudiante;
import com.empresa.aprendefp.dominio.Tutorial;
import com.empresa.aprendefp.util.ConnectionManager;
import java.sql.Connection;
import java.util.List;

public class TutorialServicio {
	
	// se declara una referencia para apuntar a un objeto de tipo TutorialDao
	private TutorialDao tutorialDao;
	
	public TutorialServicio() {
        // se crea un objeto de tipo TutorialDao
		tutorialDao = new TutorialDao();
    }
	
	/*
     * Función que retorna un Elemento de tipo tutorial especifico
     */
	public Tutorial consultar(long tema, long tutorial) {

        // obtiene una conexión a la base
		tutorialDao.connect();
        
        // se invoca al método consultar del objeto dao
		Tutorial tuto = tutorialDao.consultar(tema, tutorial);

        // se desconecta de la base
        tutorialDao.disconnect();
        
        return tuto;
    }

}
