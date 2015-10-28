package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class that maps to the 'FacultySchoolCentre' table in the ARIES database
 * .
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="FacultySchoolCentre")
public class FacultySchoolCentre {
	private String chrTier2Code;
	private String chrTier2Name;
	
	/**
	 * Get the school identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrTier2Code() {
		return chrTier2Code;
	}
	
	/**
	 * Set the school identifier
	 * 
	 * @param chrTier2Code The identifier
	 */
	public void setChrTier2Code(String chrTier2Code) {
		this.chrTier2Code = chrTier2Code;
	}
	
	/**
	 * Get the school name
	 * 
	 * @return  The school name
	 */
	public String getChrTier2Name() {
		return chrTier2Name;
	}
	
	/**
	 * Set the school name
	 * 
	 * @param chrTier2Name The school name
	 */
	public void setChrTier2Name(String chrTier2Name) {
		this.chrTier2Name = chrTier2Name;
	}
}
