package au.edu.anu.ariestodspace.staging.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to hold the staging properties
 * 
 * @author Genevieve Turner
 *
 */
public class StagingProperties {
	private static Logger LOGGER = LoggerFactory.getLogger(StagingProperties.class);
	
	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	
	/**
	 * Constructor
	 */
	private StagingProperties() {
		
	}
	
	/**
	 * Load the properties
	 * 
	 * @param group The property group
	 */
	private static void loadProperties(String group) {
		String propertiesName = group + ".properties";
		InputStream is = StagingProperties.class.getClassLoader().getResourceAsStream(propertiesName);
		Properties properties = new Properties();
		try {
			properties.load(is);
		}
		catch (IOException e) {
			LOGGER.error("Error reading properties file");
		}
		propertiesMap.put(group, properties);
	}
	
	/**
	 * Get the property
	 * 
	 * @param name The name of the property
	 * @param group The group
	 * @return The property group
	 */
	public static String getProperty(String name, String group) {
		Properties properties = propertiesMap.get(group);
		if (properties == null) {
			loadProperties(group);
			properties = propertiesMap.get(group);
		}
		
		return properties.getProperty(name, null);
	}
}
