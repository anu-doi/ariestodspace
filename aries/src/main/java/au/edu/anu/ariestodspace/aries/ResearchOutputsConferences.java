package au.edu.anu.ariestodspace.aries;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;
import au.edu.anu.ariestodspace.aries.util.Util;

/**
 * Entity class for the 'Research_otuputs_conferences' table in the ARIES database.
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_conferences")
public class ResearchOutputsConferences {
	private Integer intConferenceCode;
	private String chrConferenceName;
	private String chrConferencePublication;
	private String chrEditor;
	private String chrISBN;
	private String chrLocation;
	private String chrURL;
	private String dateOfConference;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Get the conference identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public Integer getIntConferenceCode() {
		return intConferenceCode;
	}

	/**
	 * Set the conference identifier
	 * 
	 * param intConferenceCode The identifier
	 */
	public void setIntConferenceCode(Integer intConferenceCode) {
		this.intConferenceCode = intConferenceCode;
	}

	/**
	 * Get the conference name
	 * 
	 * @return The conference name
	 */
	@DSpaceField("dc.relation.ispartofseries")
	public String getChrConferenceName() {
		return chrConferenceName;
	}

	/**
	 * Set the conference name
	 * 
	 * @param chrConferenceName The conference name
	 */
	public void setChrConferenceName(String chrConferenceName) {
		this.chrConferenceName = chrConferenceName;
	}

	/**
	 * Get the name of the conference proceedings (i.e. the conference papers book name)
	 * 
	 * @return The proceedings name
	 */
	@DSpaceField("dc.source")
	public String getChrConferencePublication() {
		return chrConferencePublication;
	}

	/**
	 * sedt the name of the conference proceedings (i.e. the conference papers book name)
	 * 
	 * @param chrConferencePublication The proceedings name
	 */
	public void setChrConferencePublication(String chrConferencePublication) {
		this.chrConferencePublication = chrConferencePublication;
	}

	/**
	 * Get the conference editor
	 * 
	 * @return The editor
	 */
	public String getChrEditor() {
		return chrEditor;
	}

	/**
	 * Set the conference editor
	 * 
	 * @param chrEditor The editor
	 */
	public void setChrEditor(String chrEditor) {
		this.chrEditor = chrEditor;
	}
	
	/**
	 * Get the parsed editors
	 * 
	 * @return The editors
	 */
	@Transient
	@DSpaceField("dc.contributor.editor")
	public List<String> getEditors() {
		return Util.parseEditors(chrEditor);
	}

	/**
	 * Get the ISBN of the conference proceedings
	 * 
	 * @return The ISBN
	 */
	@DSpaceField("dc.identifier.isbn")
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the ISBN of the conference proceedings
	 * 
	 * @param chrISBN The ISBN
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}

	/**
	 * Get the location of the conference
	 * 
	 * @return The conference location
	 */
	@DSpaceField("dc.coverage.spatial")
	public String getChrLocation() {
		return chrLocation;
	}

	/**
	 * Set the conference location
	 * 
	 * @param chrLocation The conference location
	 */
	public void setChrLocation(String chrLocation) {
		this.chrLocation = chrLocation;
	}

	/**
	 * Get website for the conference
	 * 
	 * @return The website for the conference
	 */
	@DSpaceField("dc.source.uri")
	public String getChrURL() {
		return chrURL;
	}

	/**
	 * Get the website for the conference
	 * 
	 * @param chrURL The website for the conference
	 */
	public void setChrURL(String chrURL) {
		this.chrURL = chrURL;
	}

	/**
	 * Get the date the conference occurred
	 *  
	 * @return The date of the conference
	 */
	@DSpaceField("dc.date.created")
	public String getDateOfConference() {
		return dateOfConference;
	}

	/**
	 * Set the  date the conference occurred
	 * 
	 * @param dateOfConference The date of the conference
	 */
	public void setDateOfConference(String dateOfConference) {
		this.dateOfConference = dateOfConference;
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
