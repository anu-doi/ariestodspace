package au.edu.anu.ariestodspace.aries;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Persistence manager  for ARIES data.  It can create and close entity manager factories.
 * 
 * @author Genevieve Turner
 *
 */
public class ARIESPersistenceManager {
	private static final ARIESPersistenceManager inst = new ARIESPersistenceManager();
	
	private EntityManagerFactory emf;
	
	public static ARIESPersistenceManager getInstance() {
		return inst;
	}
	
	private ARIESPersistenceManager() {
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			createEntityManagerFactory();
		}
		return emf;
	}
	
	public void closeEntityManagerFactory() {
		if (emf != null) {
			try {
				emf.close();
				emf = null;
			}
			catch (IllegalStateException e) {
				//DO nothing as it is closed already
			}
		}
	}
	
	protected synchronized void createEntityManagerFactory() {
		if (this.emf == null) {
			this.emf = Persistence.createEntityManagerFactory("aries");
		}
	}

}
