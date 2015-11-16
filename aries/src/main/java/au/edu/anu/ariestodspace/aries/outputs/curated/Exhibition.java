package au.edu.anu.ariestodspace.aries.outputs.curated;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for Exhibitions.  This class relates to the RL112 and RL139 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("21112")
public class Exhibition extends Curated {
	private String chrVenue;
	private Date dateFIrstPerformed;
	private Integer intNumberOfWorksInExhibition;
	
	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}

	/**
	 * Get the Venue, city and country of the exhibition
	 * 
	 * @return The location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrVenue() {
		return chrVenue;
	}

	/**
	 * Set the Venue, city and country of the exhibition
	 * 
	 * @param chrVenue The location
	 */
	public void setChrVenue(String chrVenue) {
		this.chrVenue = chrVenue;
	}

	/**
	 * Get the date the exhibition opened
	 * 
	 * @return The opening date
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFIrstPerformed() {
		return dateFIrstPerformed;
	}

	/**
	 * SEt the date the exhibition opened
	 * 
	 * @param dateFIrstPerformed The opening date
	 */
	public void setDateFIrstPerformed(Date dateFIrstPerformed) {
		this.dateFIrstPerformed = dateFIrstPerformed;
	}

	/**
	 * Get the number of works in the exhibition
	 * 
	 * @return The number of works in the exhibition
	 */
	public Integer getIntNumberOfWorksInExhibition() {
		return intNumberOfWorksInExhibition;
	}

	/**
	 * Set teh number of works in the exhibition
	 * 
	 * @param intNumberOfWorksInExhibition The number of works in the exhibition
	 */
	public void setIntNumberOfWorksInExhibition(Integer intNumberOfWorksInExhibition) {
		this.intNumberOfWorksInExhibition = intNumberOfWorksInExhibition;
	}
	
	/**
	 * Get a description of the number of works in the exhibition
	 * 
	 * @return The number of works
	 */
	@Transient
	@DSpaceField("dc.format.extent")
	public String numberOfWorks() {
		if (intNumberOfWorksInExhibition != null) {
			return intNumberOfWorksInExhibition.toString() + " works";
		}
		return null;
	}
}
