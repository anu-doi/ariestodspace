package au.edu.anu.ariestodspace.aries.outputs.patent;

import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Base entity for Inventions. Inventions relate to the RO5 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
public class Invention extends ResearchOutputsData1 {
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Invention";
	}
}
