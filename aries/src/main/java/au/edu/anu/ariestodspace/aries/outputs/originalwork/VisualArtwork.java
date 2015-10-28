package au.edu.anu.ariestodspace.aries.outputs.originalwork;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.util.Util;

/**
 * Entity for the visual art works. Relates to the RL123 and RL133 publication categories.
 * 
 * @author Genevieve Turner
 */
@Entity
@DiscriminatorValue("23123")
public class VisualArtwork extends OriginalWork {
	private String chrCuratorship;
	private String chrExhibition;
	private String chrVenue;
	private Date dateFirstPerformed;
	private Integer intNumberOfWorksInExhibition;
	
	/**
	 * Get who name of the exhibition curator
	 * 
	 * @return The curator
	 */
//	@DSpaceField("dc.contributor.editor")
	public String getChrCuratorship() {
		return chrCuratorship;
	}

	/**
	 * Set the name of the exhibition curator
	 * 
	 * @param chrCuratorship The curator
	 */
	public void setChrCuratorship(String chrCuratorship) {
		this.chrCuratorship = chrCuratorship;
	}

	@Transient
	@DSpaceField("dc.contributor.editor")
	public List<String> getEditors() {
		return Util.parseEditors(chrCuratorship);
	}

	/**
	 * Get the title of the exhibition the work was displayed in 
	 * 
	 * @return The title of the exhibition
	 */
	@DSpaceField("dc.source")
	@Column(name="chrExhibition",columnDefinition="text")
	public String getChrExhibition() {
		return chrExhibition;
	}

	/**
	 * Set the title of the exhibition the work was displayed in
	 * 
	 * @param chrExhibition The title of the exhibition
	 */
	public void setChrExhibition(String chrExhibition) {
		this.chrExhibition = chrExhibition;
	}

	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the venue, city and country of the exhibiton
	 * 
	 * @return The location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrVenue() {
		return chrVenue;
	}
	
	/**
	 * Set the venue, city and country of the exhibition
	 * 
	 * @param chrVenue
	 */
	public void setChrVenue(String chrVenue) {
		this.chrVenue = chrVenue;
	}
	
	/**
	 * Get the date the work was first exhibited
	 * 
	 * @return The date first performed
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}
	
	/**
	 * Set the date the work was first exhibited
	 * 
	 * @param dateFirstPerformed The date the work was first exhibited
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
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
	 * Set the number of works in the exhibition
	 * 
	 * @param intNumberOfWorksInExhibition The number of works in the exhibition
	 */
	public void setIntNumberOfWorksInExhibition(Integer intNumberOfWorksInExhibition) {
		this.intNumberOfWorksInExhibition = intNumberOfWorksInExhibition;
	}
	
	/**
	 * Get the number of works formatted to provide context
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
