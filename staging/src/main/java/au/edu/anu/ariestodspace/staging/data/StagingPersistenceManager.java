package au.edu.anu.ariestodspace.staging.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Persistence manager for the staging database
 * 
 * @author Genevieve Turner
 */
public class StagingPersistenceManager {
	private static final StagingPersistenceManager inst = new StagingPersistenceManager();
	
	private EntityManagerFactory emf;
	
	/**
	 * Get the StagingPersistenceManager instance
	 * 
	 * @return The instance
	 */
	public static StagingPersistenceManager getInstance() {
		return inst;
	}
	
	/**
	 * Constructor
	 */
	private StagingPersistenceManager() {
	}
	
	/**
	 * Get the entity manager factory.
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		// If the entity manager factory does not exist then create it
		if (emf == null) {
			createEntityManagerFactory();
		}
		return emf;
	}
	
	/**
	 * Close the entity manager factory
	 */
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
	
	/**
	 * Create the entity manager factory
	 */
	protected synchronized void createEntityManagerFactory() {
		if (this.emf == null) {
			this.emf = Persistence.createEntityManagerFactory("staging");
		}
	}

}
