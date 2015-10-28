package au.edu.anu.ariestodspace.aries.outputs.recorded;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournalsPublishers;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Base entity for recorded items. Relates to the RO24 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
public class Recorded extends ResearchOutputsData1 {
	private String chrExtentOfWork;
	private String chrPublicationPlace;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Get the extent of work. e.g. the duration of a video, or the number of pages, or how many people are in the work
	 * 
	 * @return The extent of the work
	 */
	@DSpaceField("dc.format.extent")
	public String getChrExtentOfWork() {
		return chrExtentOfWork;
	}
	
	/**
	 * Set the extent of work. e.g. the duration of a video, or the number of pages, or how many people are in the work
	 * 
	 * @param chrExtentOfWork The extent of the work
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
	 * Get the publisher
	 * 
	 * @return The publisher
	 */
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="intPublisherID")
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
