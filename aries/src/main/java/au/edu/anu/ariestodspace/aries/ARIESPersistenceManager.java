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
			}
			catch (IllegalStateException e) {
				//DO nothing as it is closed already
			}
		}
	}
	
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
			
			this.emf = Persistence.createEntityManagerFactory("aries");
		}
	}

}
