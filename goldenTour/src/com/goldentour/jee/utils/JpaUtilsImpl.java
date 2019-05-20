package com.goldentour.jee.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtilsImpl implements JpaUtils{
	
	private static JpaUtils _instance = null;
	
	private EntityManagerFactory emfactory = null;
	
	
	// Costruttore private per il pattern Singleton
	private JpaUtilsImpl() {
		emfactory = Persistence.createEntityManagerFactory("goldenTourPU");	
	}

	// Creazione istanza Singleton
	public static synchronized JpaUtils getInstance() {
		
		if(_instance == null) {
			_instance = new JpaUtilsImpl();
		}
		return _instance;
	}

	// Metodo per il recupero dell'entity manager
	public EntityManager getEntityManager() {
		
		return emfactory.createEntityManager();
		
	}

}