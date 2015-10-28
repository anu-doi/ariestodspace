package au.edu.anu.ariestodspace.aries.outputs.recorded;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for digital recorded works. Relates to the RL124 and RL143 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("24124")
public class DigitalCreativeWork extends Recorded {
	private String chrCatalogueNo;
	private String chrDistributionMedium;
	private Date dateOfRecording;

	/**
	 * Get the catalogue number
	 * 
	 * @return The catalogue number
	 */
	@DSpaceField("dc.identifier")
	public String getChrCatalogueNo() {
		return chrCatalogueNo;
	}

	/**
	 * Set the catalogue number
	 * 
	 * @param chrCatalogueNo The catalogue number
	 */
	public void setChrCatalogueNo(String chrCatalogueNo) {
		this.chrCatalogueNo = chrCatalogueNo;
	}

	/**
	 * Get the distribution medium
	 * 
	 * @return The distribution medium
	 */
	@DSpaceField("dc.format.medium")
	public String getChrDistributionMedium() {
		return chrDistributionMedium;
	}

	/**
	 * Set the distribution medium
	 * 
	 * @param chrDistributionMedium The distribution medium
	 */
	public void setChrDistributionMedium(String chrDistributionMedium) {
		this.chrDistributionMedium = chrDistributionMedium;
	}

	/**
	 * Get the date of recording
	 * 
	 * @return The date of recording
	 */
	@DSpaceField("dc.date.created")
	public Date getDateOfRecording() {
		return dateOfRecording;
	}

	/**
	 * Set the date of recording
	 * 
	 * @param dateOfRecording The date of recording
	 */
	public void setDateOfRecording(Date dateOfRecording) {
		this.dateOfRecording = dateOfRecording;
	}
}
