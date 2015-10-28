package au.edu.anu.ariestodspace.aries.outputs.originalwork;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for design/architectural works.  Relates to the RL120 and RL134 publication category.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("23120")
public class DesignArchitectural extends OriginalWork {
	private String chrAccessionNumber;
	private String chrPlace;

	/**
	 * Get the number in the accession register
	 * 
	 * @return The accession number
	 */
	@DSpaceField("dc.identifier")
	public String getChrAccessionNumber() {
		return chrAccessionNumber;
	}

	/**
	 * Set the number in the accession register
	 * 
	 * @param chrAccessionNumber The asscion number
	 */
	public void setChrAccessionNumber(String chrAccessionNumber) {
		this.chrAccessionNumber = chrAccessionNumber;
	}

	/**
	 * Get the city and country where the work exists
	 * 
	 * @return The location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrPlace() {
		return chrPlace;
	}

	/**
	 * Set the city and country where the work exists
	 * 
	 * @param chrPlace The location
	 */
	public void setChrPlace(String chrPlace) {
		this.chrPlace = chrPlace;
	}
}
