package au.edu.anu.ariestodspace.aries.outputs.recorded;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for the recorded performance.  
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("24128")
public class RecordedPerformance extends Recorded {
	private String chrCatalogueNo;
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
	 * SEt the catalogue number
	 * 
	 * @param chrCatalogueNo The catalogue number
	 */
	public void setChrCatalogueNo(String chrCatalogueNo) {
		this.chrCatalogueNo = chrCatalogueNo;
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
