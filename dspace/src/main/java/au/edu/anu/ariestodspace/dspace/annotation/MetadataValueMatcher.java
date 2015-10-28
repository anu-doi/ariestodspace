package au.edu.anu.ariestodspace.dspace.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation class to define a corresponding metadatavalue metadata field id.
 * 
 * @author Genevieve Turner
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetadataValueMatcher {
	/**
	 * The metadata field id
	 * @return The id
	 */
	int metadataValue();
}
