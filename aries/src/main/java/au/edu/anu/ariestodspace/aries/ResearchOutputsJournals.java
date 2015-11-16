package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity class for the 'Research_outputs_journals' table in the ARIES database.
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_journals")
public class ResearchOutputsJournals {
	private Integer intJournalCode;
	private String chrISSN;
	private String chrJournalName;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * The journal identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public Integer getIntJournalCode() {
		return intJournalCode;
	}
	
	/**
	 * Set the journal identifier
	 * 
	 * @param intJournalCode The identifier
	 */
	public void setIntJournalCode(Integer intJournalCode) {
		this.intJournalCode = intJournalCode;
	}
	
	/**
	 * Get the ISSN
	 * 
	 * @return The ISSN
	 */
	@DSpaceField("dc.identifier.issn")
	public String getChrISSN() {
		return chrISSN;
	}
	
	/**
	 * SEt the ISSN
	 * 
	 * @param chrISSN The ISSN
	 */
	public void setChrISSN(String chrISSN) {
		this.chrISSN = chrISSN;
	}
	
	/**
	 * Get the Journal Name
	 * 
	 * @return The Journal Name
	 */
	@DSpaceField("dc.source")
	public String getChrJournalName() {
		return chrJournalName;
	}
	
	/**
	 * SEt the Journal Name
	 * 
	 * @param chrJournalName The journal name
	 */
	public void setChrJournalName(String chrJournalName) {
		this.chrJournalName = chrJournalName;
	}

	/**
	 * Get the journal publisher
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
	 * SEt the journal publisher
	 * 
	 * @param publisher The publisher
	 */
	public void setPublisher(ResearchOutputsJournalsPublishers publisher) {
		this.publisher = publisher;
	}
}
