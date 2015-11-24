package au.edu.anu.ariestodspace.aries.outputs.patent;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for NHMRC endorsed guidelines. Related to the RL132 publication category.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("05132")
public class NHMRCGuideline extends Invention {
	private Date chrLaunchDate;

	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the date published
	 * 
	 * @return The date published
	 */
	@DSpaceField("dc.date.issued")
	public Date getChrLaunchDate() {
		return chrLaunchDate;
	}

	/**
	 * Set the date published
	 * 
	 * @param chrLaunchDate The date published
	 */
	public void setChrLaunchDate(Date chrLaunchDate) {
		this.chrLaunchDate = chrLaunchDate;
	}
}
