package au.edu.anu.ariestodspace.aries.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines which DSpace field a database field maps to.
 * 
 * @author Genevieve Turner
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DSpaceField {
	String value();
}
