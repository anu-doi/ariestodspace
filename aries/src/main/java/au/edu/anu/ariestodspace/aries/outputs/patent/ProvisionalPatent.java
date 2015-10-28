package au.edu.anu.ariestodspace.aries.outputs.patent;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for provisional patents. Relates to the RL30 publication category.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("05030")
public class ProvisionalPatent extends Invention {
	private String chrPatentCountry;
	private String chrPatentNumber;
	private Date dateFirstPerformed;
	
	/**
	 * Get the country the patent application was lodged.
	 * 
	 * @return The country
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrPatentCountry() {
		return chrPatentCountry;
	}
	
	/**
	 * SEt the country the patent application was lodged.
	 * 
	 * @param chrPatentCountry The country
	 */
	public void setChrPatentCountry(String chrPatentCountry) {
		this.chrPatentCountry = chrPatentCountry;
	}
	
	/**
	 * Get the patent application number
	 * 
	 * @return The application number
	 */
	@DSpaceField("dc.identifier")
	public String getChrPatentNumber() {
		return chrPatentNumber;
	}
	
	/**
	 * Set the patent application number
	 * 
	 * @param chrPatentNumber The application number
	 */
	public void setChrPatentNumber(String chrPatentNumber) {
		this.chrPatentNumber = chrPatentNumber;
	}
	
	/**
	 * Get the date the patent was registered
	 * 
	 * @return  The registration date
	 */
	@DSpaceField("dc.date.created")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}
	
	/**
	 * SEt the date the patent was registered
	 * 
	 * @param dateFirstPerformed The registration date
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}
}
