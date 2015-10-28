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
			
//			If we want to have an external property file for this...
//			Map<String, String> connProps = new HashMap<String, String>();
//			
//			String jdbcDriver = GlobalProps.getProperty("jdbc.driver");
//			String jdbcUrl = GlobalProps.getProperty("jdbc.url");
//			String jdbcUser = GlobalProps.getProperty("jdbc.username");
//			String jdbcPassword = GlobalProps.getProperty("jdbc.password");
//			
//			connProps.put("hibernate.connection.driver_class", jdbcDriver);
//			connProps.put("hibernate.connection.url", jdbcUrl);
//			connProps.put("hibernate.connection.user", jdbcUser);
//			connProps.put("hibernate.connection.password", jdbcPassword);
			
			this.emf = Persistence.createEntityManagerFactory("staging");
		}
	}

}
