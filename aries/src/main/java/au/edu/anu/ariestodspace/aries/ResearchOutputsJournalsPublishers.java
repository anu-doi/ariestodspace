package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for the 'Research_outputs_journals_publishers' table in the ARIES database.
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_journals_publishers")
public class ResearchOutputsJournalsPublishers {
	private Integer intPublisherID;
	private String chrPublisher;
	private String chrCommercial;
	
	/**
	 * Get the publisher ID
	 * 
	 * @return The publisher ID
	 */
	@Id
	public Integer getIntPublisherID() {
		return intPublisherID;
	}
	
	/**
	 * Set the publisher ID
	 * 
	 * @param intPublisherID The publisher ID
	 */
	public void setIntPublisherID(Integer intPublisherID) {
		this.intPublisherID = intPublisherID;
	}

	/**
	 * Get the publisher name
	 * 
	 * @return The publisher name
	 */
	@DSpaceField("dc.publisher")
	public String getChrPublisher() {
		return chrPublisher;
	}
	
	/**
	 * Set the publisher name
	 * 
	 * @param chrPublisher The publisher
	 */
	public void setChrPublisher(String chrPublisher) {
		this.chrPublisher = chrPublisher;
	}

	/**
	 * Set whether the publisher is a commercial publisher
	 * 
	 * @param chrCommercial The commerciality of the publisher
	 */
	public void setChrCommercial(String chrCommercial) {
		this.chrCommercial = chrCommercial;
	}
	
	/**
	 * Get whether the publisher is a commercial publisher
	 * 
	 * @return The commerciality of the publisher
	 */
	public String getChrCommercial() {
		return chrCommercial;
	}
}
