package au.edu.anu.ariestodspace.aries.outputs;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for legal cases. Relates to thte RO14 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("14XXX")
public class LegalCase extends ResearchOutputsData1 {
	private String chrPlace;
	private String chrVenue;
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The dspace type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Legal case";
	}

	/**
	 * Get the court heaering the case
	 * 
	 * @return The court name
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrPlace() {
		return chrPlace;
	}

	/**
	 * Set the court hearing the case
	 * 
	 * @param chrPlace The court name
	 */
	public void setChrPlace(String chrPlace) {
		this.chrPlace = chrPlace;
	}

	/**
	 * Get the city and country of the court case
	 * 
	 * @return The city and country
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrVenue() {
		return chrVenue;
	}

	/**
	 * Set the city and country of the court case
	 * 
	 * @param chrVenue The city and country
	 */
	public void setChrVenue(String chrVenue) {
		this.chrVenue = chrVenue;
	}
}
