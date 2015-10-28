package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for the 'FOR_codes' table.  FOR codes are the ABS Fields of Research. 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="FOR_codes")
public class ForCodes {
	private String chrForDivisionCode;
	private String chrForGroupCode;
	private String chrForObjectiveCode;
	private String chrForDescription;
	
	/**
	 * Get the Field of Research division code.  For example '02'
	 * 
	 * @return The division code
	 */
	public String getChrForDivisionCode() {
		return chrForDivisionCode;
	}
	
	/**
	 * Set the Field of Research division code. For example '02'
	 * 
	 * @param chrForDivisionCode The division code
	 */
	public void setChrForDivisionCode(String chrForDivisionCode) {
		this.chrForDivisionCode = chrForDivisionCode;
	}
	
	/**
	 * Get the Field of Research group code. For example '0202'
	 * 
	 * @return THe group code
	 */
	public String getChrForGroupCode() {
		return chrForGroupCode;
	}
	
	/**
	 * Set the field of resarch group code. For example '0202'
	 * 
	 * @param chrForGroupCode
	 */
	public void setChrForGroupCode(String chrForGroupCode) {
		this.chrForGroupCode = chrForGroupCode;
	}
	
	/**
	 * Get the field of research objective code. For example '020204'
	 * 
	 * @return The objective code
	 */
	@Id
	public String getChrForObjectiveCode() {
		return chrForObjectiveCode;
	}
	
	/**
	 * Set the field of research objective code. For example '020204'
	 * 
	 * @param chrForObjectiveCode The objective code
	 */
	public void setChrForObjectiveCode(String chrForObjectiveCode) {
		this.chrForObjectiveCode = chrForObjectiveCode;
	}
	
	/**
	 * Get the field of research description
	 * 
	 * @return The description
	 */
	public String getChrForDescription() {
		return chrForDescription;
	}
	
	/**
	 * Set the field of resaerch description
	 * 
	 * @param chrForDescription The description
	 */
	public void setChrForDescription(String chrForDescription) {
		this.chrForDescription = chrForDescription;
	}

	/**
	 * Get the field of research value to be stored in DSpace
	 * 
	 * @return The field of research value
	 */
	@Transient
	@DSpaceField("local.identifier.absfor")
	public String getValue() {
		return chrForObjectiveCode + " - " + chrForDescription;
	}
}
