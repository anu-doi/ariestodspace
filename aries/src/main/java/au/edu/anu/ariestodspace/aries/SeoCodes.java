package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for the 'SEO_codes' table.  SEO codes are the ABS Socio-Economic Objectives
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="SEO_codes")
public class SeoCodes {
	private String chrSubdivision;
	private String chrSubdivisionCode;
	private String chrGroupCode;
	private String chrClassCode;
	
	/**
	 * Get the socio-economic objective description
	 * 
	 * @return The description
	 */
	public String getChrSubdivision() {
		return chrSubdivision;
	}
	
	/**
	 * Set the socio-economic objective description
	 * 
	 * @param chrSubdivision The description
	 */
	public void setChrSubdivision(String chrSubdivision) {
		this.chrSubdivision = chrSubdivision;
	}
	
	/**
	 * Get the socio-economic objectivie division code
	 * 
	 * @return The division code
	 */
	public String getChrSubdivisionCode() {
		return chrSubdivisionCode;
	}
	
	/**
	 * Set the socio-economic objective division code
	 * 
	 * @param chrSubdivisionCode The division code
	 */
	public void setChrSubdivisionCode(String chrSubdivisionCode) {
		this.chrSubdivisionCode = chrSubdivisionCode;
	}
	
	/**
	 * Get the socio-economic objective group code
	 * 
	 * @return THe group code
	 */
	public String getChrGroupCode() {
		return chrGroupCode;
	}
	
	/**
	 * Set the socio-economic objective group code
	 * 
	 * @param chrGroupCode The group code
	 */
	public void setChrGroupCode(String chrGroupCode) {
		this.chrGroupCode = chrGroupCode;
	}
	
	/**
	 * Get the socio-economic objective code
	 * 
	 * @return The code
	 */
	@Id
	public String getChrClassCode() {
		return chrClassCode;
	}
	
	/**
	 * Set the socio-economic objective code
	 * 
	 * @param chrClassCode The code
	 */
	public void setChrClassCode(String chrClassCode) {
		this.chrClassCode = chrClassCode;
	}

	/**
	 * Get the socio-economic value to be stored in DSpace
	 * 
	 * @return The socio-economic objective value
	 */
	@Transient
	@DSpaceField("local.identifier.absseo")
	public String getValue() {
		return chrClassCode + " - " + chrSubdivision;
	}
}
