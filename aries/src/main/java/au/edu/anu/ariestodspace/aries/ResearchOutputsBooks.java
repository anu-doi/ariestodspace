package au.edu.anu.ariestodspace.aries;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;
import au.edu.anu.ariestodspace.aries.util.Util;

/**
 * Entity class for the 'Research_outputs_books' table in the ARIES database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_books")
public class ResearchOutputsBooks {
//	private static Logger LOGGER = LoggerFactory.getLogger(ResearchOutputsBooks.class);
	private Integer intBookCode;
	private String chrBookName;
	private String chrEditor;
	private String chrEdition;
	private String chrISBN;
	private String chrPlacePublished;
	private String chrYear;
	private Integer intNumberOfVolumes;
	private Integer intNumberOfChapters;
	private Integer intpublisherID;
	private ResearchOutputsJournalsPublishers publisher;
	
	/**
	 * Constructor
	 */
	public ResearchOutputsBooks() {
		
	}

	/**
	 * Get the book identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public Integer getIntBookCode() {
		return intBookCode;
	}

	/**
	 * Set the book identifier
	 * 
	 * @param intBookCode The book identifier
	 */
	public void setIntBookCode(Integer intBookCode) {
		this.intBookCode = intBookCode;
	}

	/**
	 * Get the book name
	 * 
	 * @return The book name
	 */
	@DSpaceField("dc.relation.ispartof")
	@Column(name="chrBookName")
	public String getChrBookName() {
		return chrBookName;
	}

	/**
	 * Set the book name
	 * 
	 * @param chrBookName The book name
	 */
	public void setChrBookName(String chrBookName) {
		this.chrBookName = chrBookName;
	}

	/**
	 * Get the editor
	 * 
	 * @return the editor
	 */
//	@DSpaceField("dc.contributor.editor")
	public String getChrEditor() {
		return chrEditor;
	}

	@Transient
	@DSpaceField("dc.contributor.editor")
	public List<String> getEditors() {
//		LOGGER.info("Editors: {}", chrEditor);
//		List<String> editors = Util.parseEditors(chrEditor);
//		LOGGER.info("Editor list: {}", editors);
//		return editors;
		return Util.parseEditors(chrEditor);
	}

	/**
	 * Set the editor
	 * 
	 * @param chrEditor The editor
	 */
	public void setChrEditor(String chrEditor) {
		this.chrEditor = chrEditor;
	}

	/**
	 * Get the edition
	 * 
	 * @return The edition
	 */
	@DSpaceField("dc.relation.isversionof")
	public String getChrEdition() {
		return chrEdition;
	}

	/**
	 * SEt the edition
	 * 
	 * @param chrEdition The edition
	 */
	public void setChrEdition(String chrEdition) {
		this.chrEdition = chrEdition;
	}

	/**
	 * Get the books ISBN
	 * 
	 * @return The ISBN
	 */
	@DSpaceField("dc.identifier.isbn")
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the books ISBN
	 * 
	 * @param chrISBN The ISBN
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}

	/**
	 * Get the location the book was published
	 * 
	 * @return The place of publication
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPlacePublished() {
		return chrPlacePublished;
	}

	/**
	 * Set the place of publication
	 * 
	 * @param chrPlacePublished The place of publication
	 */
	public void setChrPlacePublished(String chrPlacePublished) {
		this.chrPlacePublished = chrPlacePublished;
	}

	/**
	 * Get the year of publication
	 * 
	 * @return The year of publication
	 */
	public String getChrYear() {
		return chrYear;
	}

	/**
	 * Set the year of publication
	 * 
	 * @param chrYear The year of publication
	 */
	public void setChrYear(String chrYear) {
		this.chrYear = chrYear;
	}

	/**
	 * Get the number of volumes associated with the book
	 * 
	 * @return The number of volumes
	 */
	public Integer getIntNumberOfVolumes() {
		return intNumberOfVolumes;
	}

	/**
	 * Set the number of volumes associated with the book
	 * @param intNumberOfVolumes
	 */
	public void setIntNumberOfVolumes(Integer intNumberOfVolumes) {
		this.intNumberOfVolumes = intNumberOfVolumes;
	}

	/**
	 * Get the number of chapters in the book
	 * 
	 * @return The number of chapters
	 */
	public Integer getIntNumberOfChapters() {
		return intNumberOfChapters;
	}

	/**
	 * Set the number of chapters in the book
	 * 
	 * @param intNumberOfChapters The number of chapters
	 */
	public void setIntNumberOfChapters(Integer intNumberOfChapters) {
		this.intNumberOfChapters = intNumberOfChapters;
	}

	/**
	 * Get the publisher id
	 * 
	 * @return The publisher id
	 */
	public Integer getIntpublisherID() {
		return intpublisherID;
	}

	/**
	 * Set the publisher id
	 * 
	 * @param intpublisherID The publisher id
	 */
	public void setIntpublisherID(Integer intpublisherID) {
		this.intpublisherID = intpublisherID;
	}

	/**
	 * Get the publisher
	 * 
	 * @return THe publisher
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
	 * @param publishers The publisher
	 */
	public void setPublisher(ResearchOutputsJournalsPublishers publishers) {
		this.publisher = publishers;
	}
}
