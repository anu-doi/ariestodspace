package au.edu.anu.ariestodspace.aries.outputs;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Class for the Other output type. Relates to RO17 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("17XXX")
public class Other extends ResearchOutputsData1 {
	private String chrCommissionedBy;
	private String chrIssue;
	private String chrPageNumbers;
	private String chrPlace;
	
	/**
	 * Get the type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Other";
	}

	/**
	 * Get who the output was commissioned by
	 * 
	 * @return The commissioner
	 */
	public String getChrCommissionedBy() {
		return chrCommissionedBy;
	}

	/**
	 * Set who the output was commissioned by
	 * 
	 * @param chrCommissionedBy
	 */
	public void setChrCommissionedBy(String chrCommissionedBy) {
		this.chrCommissionedBy = chrCommissionedBy;
	}
	
	/**
	 * Get the sponsorship information
	 * 
	 * @return The sponsorship information
	 */
	@Transient
	@DSpaceField("dc.description.sponsorship")
	public String getSponsorship() {
		if (chrCommissionedBy != null && chrCommissionedBy.trim().length() > 0) {
			return "This item was commisioned by " + chrCommissionedBy;
		}
		return null;
	}

	/**
	 * Get the issue number
	 * 
	 * @return The issue
	 */
	@DSpaceField("local.bibliographicCitation.issue")
	public String getChrIssue() {
		return chrIssue;
	}

	/**
	 * Set the issue number
	 * 
	 * @param chrIssue The issue number
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
	 * SEt the page numbers
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
	 * Get the last page
	 * 
	 * @return The last page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.lastpage")
	public String getLastPage() {
		return getLastPage(chrPageNumbers);
	}

	/**
	 * Get the location the output was published
	 * 
	 * @return The publication location
	 */
	@DSpaceField("local.bibliographicCitation.placeofpublication")
	public String getChrPlace() {
		return chrPlace;
	}

	/**
	 * Set the place of publication
	 * 
	 * @param chrPlace The publication location
	 */
	public void setChrPlace(String chrPlace) {
		this.chrPlace = chrPlace;
	}
}
