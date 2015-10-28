package au.edu.anu.ariestodspace.dspace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.edu.anu.ariestodspace.dspace.annotation.MetadataValueMatcher;

/**
 * Class for a DSpaceObject.  This class is for convenience purposes to retrieve some values easily.
 * @author Genevieve Turner
 *
 */
public class DSpaceObject {
	private List<String> handles = new ArrayList<String>();
	private Date dateIssued;
	private String title;
	private List<String> ariesIds = new ArrayList<String>();
	private List<String> authors = new ArrayList<String>();
	private List<String> editors = new ArrayList<String>();
	private List<String> affiliation = new ArrayList<String>();
	private List<String> abstracts = new ArrayList<String>();
	private List<String> isbns = new ArrayList<String>();
	private List<String> issns = new ArrayList<String>();
	private List<String> dois = new ArrayList<String>();
	private List<String> sourceUris = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public DSpaceObject() {
		
	}

	/**
	 * Get the handles associated with the item. There is usually only one.
	 * 
	 * @return  The handles
	 */
	public List<String> getHandles() {
		return handles;
	}

	/**
	 * SEt the handles associated with the item. There is usually only one
	 * 
	 * @param handles The handles
	 */
	public void setHandles(List<String> handles) {
		this.handles = handles;
	}

	/**
	 * Get the date issued
	 * 
	 * @return The date issued
	 */
	@MetadataValueMatcher(metadataValue=107)
	public Date getDateIssued() {
		return dateIssued;
	}

	/**
	 * Set the date issued
	 * 
	 * @param dateIssued Teh date issued
	 */
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	/**
	 * Get the title
	 * 
	 * @return The title
	 */
	@MetadataValueMatcher(metadataValue=64)
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title
	 * 
	 * @param title The titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the ARIES identifiers
	 * 
	 * @return  The identifers
	 */
	@MetadataValueMatcher(metadataValue=110)
	public List<String> getAriesIds() {
		return ariesIds;
	}

	/**
	 * Set the ARIES identifieres
	 * 
	 * @param ariesIds  The identifiers
	 */
	public void setAriesIds(List<String> ariesIds) {
		this.ariesIds = ariesIds;
	}

	/**
	 * Get the authors
	 * 
	 * @return  The authors
	 */
	@MetadataValueMatcher(metadataValue=3)
	public List<String> getAuthors() {
		return authors;
	}

	/**
	 * Set the authors
	 * 
	 * @param authors The authors
	 */
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	/**
	 * Get the editors
	 * 
	 * @return The editors
	 */
	@MetadataValueMatcher(metadataValue=4)
	public List<String> getEditors() {
		return editors;
	}

	/**
	 * Set the editors
	 * 
	 * @param editors The editors
	 */
	public void setEditors(List<String> editors) {
		this.editors = editors;
	}

	/**
	 * Get the affiliations
	 * 
	 * @return The affiliations
	 */
	@MetadataValueMatcher(metadataValue=121)
	public List<String> getAffiliation() {
		return affiliation;
	}

	/**
	 * Set the affiliations
	 * 
	 * @param affiliation The affiliations
	 */
	public void setAffiliation(List<String> affiliation) {
		this.affiliation = affiliation;
	}

	/**
	 * Get the abstracts descriptions
	 * 
	 * @return The descriptions
	 */
	@MetadataValueMatcher(metadataValue=27)
	public List<String> getAbstracts() {
		return abstracts;
	}

	/**
	 * Set the abstracts descriptions
	 * 
	 * @param abstracts the descriptions
	 */
	public void setAbstracts(List<String> abstracts) {
		this.abstracts = abstracts;
	}

	/**
	 * Get the associated isbns
	 * 
	 * @return The isbns
	 */
	@MetadataValueMatcher(metadataValue=20)
	public List<String> getIsbns() {
		return isbns;
	}

	/**
	 * Set the associated isbns
	 * 
	 * @param isbns THe isbns
	 */
	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}

	/**
	 * Get the associated issns
	 * 
	 * @return The issns
	 */
	@MetadataValueMatcher(metadataValue=21)
	public List<String> getIssns() {
		return issns;
	}

	/**
	 * SEt the associated issns
	 * 
	 * @param issns The issns
	 */
	public void setIssns(List<String> issns) {
		this.issns = issns;
	}

	/**
	 * Get the digital object identifiers (DOI's)
	 * 
	 * @return  THE DOI's
	 */
	@MetadataValueMatcher(metadataValue=145)
	public List<String> getDois() {
		return dois;
	}

	/**
	 * Set the digital object identifiers (DOI's)
	 * 
	 * @param dois The DOI's
	 */
	public void setDois(List<String> dois) {
		this.dois = dois;
	}

	/**
	 * Get the source uri's i.e. the location of the publication.
	 * 
	 * @return The uri's
	 */
	@MetadataValueMatcher(metadataValue=56)
	public List<String> getSourceUris() {
		return sourceUris;
	}

	/**
	 * Set the source uri's i.e. the location of the publication.
	 * 
	 * @param sourceUris THe uri's
	 */
	public void setSourceUris(List<String> sourceUris) {
		this.sourceUris = sourceUris;
	}
}
