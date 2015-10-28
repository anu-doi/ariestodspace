package au.edu.anu.ariestodspace.aries.outputs.curated;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for web exhibitions. Relates to the RL115 and RL146 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("21115")
public class WebExhibition extends Curated {
	private String chrTypeOfWork;
	private Date dateFirstPerformed;
	
	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}

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

	/**
	 * Get the date the website launched
	 * 
	 * @return The website
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}

	/**
	 * Set the date the website launched
	 * 
	 * @param dateFirstPerformed The website
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}
}
