package au.edu.anu.ariestodspace.aries.outputs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for map objects. Relates to RO12 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("12XXX")
public class MapObject extends ResearchOutputsData1 {
	private String chrPublicationPlace;
	private String chrTitleOfConferenceProceedings;
	private Date dateFirstPerformed;
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Cartographic material";
	}

	/**
	 * Get the location the map was published
	 * 
	 * @return  The publication location
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPublicationPlace() {
		return chrPublicationPlace;
	}

	/**
	 * Set the location the map was published
	 * 
	 * @param chrPublicationPlace The publication location
	 */
	public void setChrPublicationPlace(String chrPublicationPlace) {
		this.chrPublicationPlace = chrPublicationPlace;
	}

	@Override
	@Column(name="chrReportingYear")
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}

	/**
	 * Name of the publication in which the map appeared
	 * 
	 * @return The publication name
	 */
	@DSpaceField("dc.source")
	@Column(name="chrTitleOfConferenceProceedings",columnDefinition="text")
	public String getChrTitleOfConferenceProceedings() {
		return chrTitleOfConferenceProceedings;
	}

	/**
	 * Set the name of the publication in which the map appeared
	 * 
	 * @param chrTitleOfConferenceProceedings The publication name
	 */
	public void setChrTitleOfConferenceProceedings(
			String chrTitleOfConferenceProceedings) {
		this.chrTitleOfConferenceProceedings = chrTitleOfConferenceProceedings;
	}

	/**
	 * Get the publication date
	 * 
	 * @return The publication date
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}

	/**
	 * Set the publication date
	 * 
	 * @param dateFirstPerformed The publication date
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}
}
