package au.edu.anu.ariestodspace.aries.outputs.curated;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for festivals.  This class relates to the RL113 and RL140 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("21113")
public class Festival extends Curated {
	private String chrVenue;
	private Date dateFirstPerformed;
	
	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the venue, city and country of the festival
	 * 
	 * @return The location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrVenue() {
		return chrVenue;
	}
	
	/**
	 * Set the venue, city and country of the festival
	 * 
	 * @param chrVenue The location
	 */
	public void setChrVenue(String chrVenue) {
		this.chrVenue = chrVenue;
	}
	
	/**
	 * Get the data the festival commenced
	 * 
	 * @return The commencement date
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}
	
	/**
	 * Set the date the festival commenced
	 * 
	 * @param dateFirstPerformed The commencement date
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}
}
