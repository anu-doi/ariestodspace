package au.edu.anu.ariestodspace.aries.outputs.liveperformance;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournalsPublishers;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity class for live performances. Relates to the RL119, RL148, RL116, RL149, RL138 and RL118 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("22119")
public class LivePerformance extends ResearchOutputsData1 {
	private String chrExtentOfWork;
	private String chrPublicationPlace;
	private String chrVenue;
	private Date dateFirstPerformed;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Creative work";
	}
	
	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}

	/**
	 * Get the extent of work e.g. the performance length and number of performers
	 * 
	 * @return The extent of work
	 */
	@DSpaceField("dc.format.extent")
	public String getChrExtentOfWork() {
		return chrExtentOfWork;
	}

	/**
	 * SEt the extent of work e.g. the performance length and number of performers
	 * 
	 * @param chrExtentOfWork The extent of work
	 */
	public void setChrExtentOfWork(String chrExtentOfWork) {
		this.chrExtentOfWork = chrExtentOfWork;
	}

	/**
	 * Get the publication place
	 * 
	 * @return The publication place
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPublicationPlace() {
		return chrPublicationPlace;
	}

	/**
	 * Set the publication place
	 * 
	 * @param chrPublicationPlace The publication place
	 */
	public void setChrPublicationPlace(String chrPublicationPlace) {
		this.chrPublicationPlace = chrPublicationPlace;
	}

	/**
	 * Get the venue, city and country of performance
	 * 
	 * @return The location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrVenue() {
		return chrVenue;
	}

	/**
	 * SEt the venue, city and country of performance
	 * 
	 * @param chrVenue The location
	 */
	public void setChrVenue(String chrVenue) {
		this.chrVenue = chrVenue;
	}

	/**
	 * Get the date first performed
	 * 
	 * @return The date first performed
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateFirstPerformed() {
		return dateFirstPerformed;
	}

	/**
	 * SEt the date first performed
	 * 
	 * @param dateFirstPerformed The date first performed
	 */
	public void setDateFirstPerformed(Date dateFirstPerformed) {
		this.dateFirstPerformed = dateFirstPerformed;
	}


	/**
	 * Get the publisher
	 * 
	 * @return The publisher
	 */
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="intPublisherID")
	@NotFound(action=NotFoundAction.IGNORE)
	public ResearchOutputsJournalsPublishers getPublisher() {
		return publisher;
	}

	/**
	 * Set the publisher
	 * 
	 * @param publisher The publisher
	 */
	public void setPublisher(ResearchOutputsJournalsPublishers publisher) {
		this.publisher = publisher;
	}
}
