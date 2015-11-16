package au.edu.anu.ariestodspace.aries.outputs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for Newspaper/Magazine items. Relates to the RO15 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("15XXX")
public class NewspaperMagazine extends ResearchOutputsData1 {
	private String chrIssue;
	private String chrPageNumbers;
	private String chrPublicationPlace;
	private String chrTitleOfConferenceProceedings;
	private Date dateFirstPerformed;
	
	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Newspaper/magazine article";
	}

	/**
	 * Get the issue of the newspaper
	 * 
	 * @return THe issue
	 */
	@DSpaceField("local.bibliographicCitation.issue")
	public String getChrIssue() {
		return chrIssue;
	}

	/**
	 * Set the issue of the newspaper
	 * 
	 * @param chrIssue The issue
	 */
	public void setChrIssue(String chrIssue) {
		this.chrIssue = chrIssue;
	}

	/**
	 * Get the page numbers
	 * 
	 * @return The page numbers
	 */
	public String getChrPageNumbers() {
		return chrPageNumbers;
	}

	/**
	 * Set the page numbers
	 * 
	 * @param chrPageNumbers The page numbers
	 */
	public void setChrPageNumbers(String chrPageNumbers) {
		this.chrPageNumbers = chrPageNumbers;
	}
	
	/**
	 * Get the start page
	 * 
	 * @return The start page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.startpage")
	public String getStartPage() {
		return getStartPage(chrPageNumbers);
	}

	/**
	 * Set the last page
	 * 
	 * @return The last page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.lastpage")
	public String getLastPage() {
		return getLastPage(chrPageNumbers);
	}

	/**
	 * Get the place of publication
	 * 
	 * @return The publication location
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPublicationPlace() {
		return chrPublicationPlace;
	}

	/**
	 * Set the place of publication
	 * 
	 * @param chrPublicationPlace The publication location
	 */
	public void setChrPublicationPlace(String chrPublicationPlace) {
		this.chrPublicationPlace = chrPublicationPlace;
	}

	/**
	 * Get the Newspaper or magazine title
	 * 
	 * @return The title
	 */
	@DSpaceField("dc.source")
	@Column(name="chrTitleOfConferenceProceedings",columnDefinition="text")
	public String getChrTitleOfConferenceProceedings() {
		return chrTitleOfConferenceProceedings;
	}

	/**
	 * Set the newspaper or magazine title
	 * 
	 * @param chrTitleOfConferenceProceedings The title
	 */
	public void setChrTitleOfConferenceProceedings(
			String chrTitleOfConferenceProceedings) {
		this.chrTitleOfConferenceProceedings = chrTitleOfConferenceProceedings;
	}

	/**
	 * Get the publication date
	 * 
	 * @return THe publication date
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
