package au.edu.anu.ariestodspace.aries.outputs.patent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Enttiy for granted patents. Relates to the RL78 publication category.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("05078")
public class GrantedPatent extends Invention {
	private String chrPatentCountry;
	private String chrPatentNumber;
	private String chrPatentRegistrationBody;
	private String chrPatentSpecification;
	
	/**
	 * Get the country of registration
	 * 
	 * @return The country
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrPatentCountry() {
		return chrPatentCountry;
	}
	
	/**
	 * Set the country of registration
	 * 
	 * @param chrPatentCountry The country
	 */
	public void setChrPatentCountry(String chrPatentCountry) {
		this.chrPatentCountry = chrPatentCountry;
	}
	
	/**
	 * Get the patent number
	 * 
	 * @return The patent number
	 */
	@DSpaceField("dc.identifier")
	public String getChrPatentNumber() {
		return chrPatentNumber;
	}
	
	/**
	 * Set the patent number
	 * 
	 * @param chrPatentNumber The patent number
	 */
	public void setChrPatentNumber(String chrPatentNumber) {
		this.chrPatentNumber = chrPatentNumber;
	}
	
	/**
	 * Get the registered owner of the patent
	 * 
	 * @return The registered owner
	 */
	@DSpaceField("dc.rights.holder")
	public String getChrPatentRegistrationBody() {
		return chrPatentRegistrationBody;
	}
	
	/**
	 * SEt the registered owner of the patent
	 * 
	 * @param chrPatentRegistrationBody The registered owner
	 */
	public void setChrPatentRegistrationBody(String chrPatentRegistrationBody) {
		this.chrPatentRegistrationBody = chrPatentRegistrationBody;
	}
	
	/**
	 * Get the patent family
	 * 
	 * @return The patent family
	 */
	@DSpaceField("dc.relation.ispartofseries")
	public String getChrPatentSpecification() {
		return chrPatentSpecification;
	}
	
	/**
	 * Set the patent family
	 * 
	 * @param chrPatentSpecification The patent family
	 */
	public void setChrPatentSpecification(String chrPatentSpecification) {
		this.chrPatentSpecification = chrPatentSpecification;
	}
}
