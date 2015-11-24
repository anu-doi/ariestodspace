package au.edu.anu.ariestodspace.aries.outputs.patent;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for registered designs. Relates to the RL131 publication category
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("05131")
public class RegisteredDesign extends Invention {
	private String chrDesignPatentNo;
	private String chrPatentRegistrationBody;
	private Date dateRegistrationDate;

	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the registered design number
	 * 
	 * @return The design number
	 */
	@DSpaceField("dc.identifier")
	public String getChrDesignPatentNo() {
		return chrDesignPatentNo;
	}
	
	/**
	 * Set the registered design number
	 * 
	 * @param chrDesignPatentNo The design number
	 */
	public void setChrDesignPatentNo(String chrDesignPatentNo) {
		this.chrDesignPatentNo = chrDesignPatentNo;
	}
	
	/**
	 * Get the registry organisation
	 * 
	 * @return The registry organisation
	 */
	@DSpaceField("dc.rights.holder")
	public String getChrPatentRegistrationBody() {
		return chrPatentRegistrationBody;
	}
	
	/**
	 * Set the registry organisation
	 * 
	 * @param chrPatentRegistrationBody The registry organisation
	 */
	public void setChrPatentRegistrationBody(String chrPatentRegistrationBody) {
		this.chrPatentRegistrationBody = chrPatentRegistrationBody;
	}
	
	/**
	 * Get the registration date
	 * 
	 * @return The registration date
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateRegistrationDate() {
		return dateRegistrationDate;
	}
	
	/**
	 * Set the registration date
	 * 
	 * @param dateRegistrationDate  The registration date
	 */
	public void setDateRegistrationDate(Date dateRegistrationDate) {
		this.dateRegistrationDate = dateRegistrationDate;
	}
}
