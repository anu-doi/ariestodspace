package au.edu.anu.ariestodspace.aries.outputs.patent;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for patents that were eligible for HERDC in 2001. Relates to the RL37 publication category.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("05037")
public class HERDC2001Patent extends Invention {
	private String chrCountryOfRegistration;
	private String chrDesignPatentNo;
	private Date dateFirstPerformed;
	private Date dateOfRecording;
	
	/**
	 * Get the country of registration.
	 * 
	 * @return  The country of registration
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrCountryOfRegistration() {
		return chrCountryOfRegistration;
	}
	
	/**
	 * Set the country of registration
	 * 
	 * @param chrCountryOfRegistration The country of registration
	 */
	public void setChrCountryOfRegistration(String chrCountryOfRegistration) {
		this.chrCountryOfRegistration = chrCountryOfRegistration;
	}
	
	/**
	 * Get the patent number
	 * 
	 * @return The patent number
	 */
	@DSpaceField("dc.identifier")
	public String getChrDesignPatentNo() {
		return chrDesignPatentNo;
	}
	
	/**
	 * Set the patent number
	 * 
	 * @param chrDesignPatentNo  The patent number
	 */
	public void setChrDesignPatentNo(String chrDesignPatentNo) {
		this.chrDesignPatentNo = chrDesignPatentNo;
	}
	
	/**
	 * Get the registered date
	 * 
	 * @return The registered date
	 */
	@DSpaceField("dc.date.created")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}
	
	/**
	 * Set the registered date
	 * 
	 * @param dateFirstPerformed The registered date
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}
	
	/**
	 * Get the date the patent was granted
	 * 
	 * @return The patent granted date
	 */
	@DSpaceField("dcterms.dateAccepted")
	public Date getDateOfRecording() {
		return dateOfRecording;
	}
	
	/**
	 * Set the date the patent was granted
	 * 
	 * @param dateOfRecording The patent granted date
	 */
	public void setDateOfRecording(Date dateOfRecording) {
		this.dateOfRecording = dateOfRecording;
	}
}
