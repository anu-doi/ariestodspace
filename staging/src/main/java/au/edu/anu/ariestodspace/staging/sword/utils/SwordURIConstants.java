package au.edu.anu.ariestodspace.staging.sword.utils;

import au.edu.anu.ariestodspace.staging.util.StagingProperties;

/**
 * Constant class to retrieve appropriate URLs and values
 * 
 * @author Genevieve Turner
 *
 */
public class SwordURIConstants {
	/**
	 * The prefix of the edit url
	 */
	public static final String EDIT_PREFIX = StagingProperties.getProperty("sword.server", "staging") + "/edit/";
	
	/**
	 * The prefix of the edit media url
	 */
	public static final String EDIT_MEDIA_PREFIX = StagingProperties.getProperty("sword.server", "staging" ) + "/edit-media/";
	
	/**
	 * The service document url
	 */
	public static final String SERVICE_DOCUMENT = StagingProperties.getProperty("sword.server", "staging") + "/servicedocument";
	
	/**
	 * The prefix of the collection url
	 */
	public static final String COLLECTION_PREFIX = StagingProperties.getProperty("sword.server", "staging") + "/collection/";
	
	/**
	 * The collection
	 */
	public static final String DEFAULT_COLLECTION = COLLECTION_PREFIX + StagingProperties.getProperty("sword.default.collection", "staging");
}
