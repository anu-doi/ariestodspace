package au.edu.anu.ariestodspace.aries.outputs.originalwork;

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
 * Base entity for original works. Regards the RL23 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
public class OriginalWork extends ResearchOutputsData1 {
	private String chrExtentOfWork;
	private String chrPublicationPlace;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Get the DSpace type
	 * @return
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Creative work";
	}

	/**
	 * Get the extent of the work
	 * 
	 * @return The extent
	 */
	@DSpaceField("dc.format.extent")
	public String getChrExtentOfWork() {
		return chrExtentOfWork;
	}

	/**
	 * Set the extent of the work
	 * 
	 * @param chrExtentOfWork The extent
	 */
	public void setChrExtentOfWork(String chrExtentOfWork) {
		this.chrExtentOfWork = chrExtentOfWork;
	}

	/**
	 * Get the place of publication
	 * 
	 * @return The place of publication
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPublicationPlace() {
		return chrPublicationPlace;
	}

	/**
	 * Set the place of publication
	 * 
	 * @param chrPublicationPlace The place of publication
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
