package au.edu.anu.ariestodspace.aries.outputs.curated;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournalsPublishers;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Base class for the curated ARIES outputs.  Curated records are the RO21 output type.
 * 
 * @author Genevieve Turner
 *
 */
public class Curated extends ResearchOutputsData1 {
	private String chrExtentOfWork;
	private String chrPublicationPlace;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Get DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Creative work";
	}

	/**
	 * The extent of work
	 * 
	 * @return  The extent of work
	 */
	@DSpaceField("dc.format.extent")
	public String getChrExtentOfWork() {
		return chrExtentOfWork;
	}

	/**
	 * Set the extent of work
	 * 
	 * @param chrExtentOfWork The extent of work
	 */
	public void setChrExtentOfWork(String chrExtentOfWork) {
		this.chrExtentOfWork = chrExtentOfWork;
	}

	/**
	 * Get the place of publication
	 * 
	 * @return  The publication location
	 */
	@DSpaceField("local.biliographicCitation.placeofpublication")
	public String getChrPublicationPlace() {
		return chrPublicationPlace;
	}

	/**
	 * Set location of publication
	 * @param chrPublicationPlace
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
