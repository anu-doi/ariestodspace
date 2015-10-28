package au.edu.anu.ariestodspace.aries.outputs.liveperformance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for music performances. Rleates to the RL117 and RL137 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("22117")
public class Music extends LivePerformance {
	private String chrTypeOfWork;

	/**
	 * Get the type of work
	 * 
	 * @return The type of work
	 */
	@DSpaceField("dc.format.medium")
	public String getChrTypeOfWork() {
		return chrTypeOfWork;
	}

	/**
	 * Set the type of work
	 * 
	 * @param chrTypeOfWork The type of work
	 */
	public void setChrTypeOfWork(String chrTypeOfWork) {
		this.chrTypeOfWork = chrTypeOfWork;
	}
}
