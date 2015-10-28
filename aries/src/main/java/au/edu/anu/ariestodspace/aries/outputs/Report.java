package au.edu.anu.ariestodspace.aries.outputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for report outputs. Relates to the RO10 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("10XXX")
public class Report extends ResearchOutputsData1 {
	private String chrCommissionedBy;
	private String chrISBN;
	private String chrISSN;
	private String chrPageNumbers; // todo bibliographic citation
	private String chrPublicationPlace;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;
			{
					add("RL103");
					add("RL159");
					add("RL160");
					add("RL161");
			}}
		);
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Report";
	}

	/**
	 * Get who the report was commissioned by
	 * 
	 * @return The commissioner
	 */
	public String getChrCommissionedBy() {
		return chrCommissionedBy;
	}

	/**
	 * Set show the report was commissioned by
	 * 
	 * @param chrCommissionedBy The commissioner
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
			return "This report was commisioned by " + chrCommissionedBy;
		}
		return null;
	}

	/**
	 * Get the ISBN
	 * 
	 * @return The isbn
	 */
	@DSpaceField("dc.identifier.isbn")
	public String getChrISBN() {
		return chrISBN;
	}

	/**
	 * Set the ISBN
	 * 
	 * @param chrISBN The ISBN
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
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
	 * Set the ISSN
	 * 
	 * @param chrISSN The ISSN
	 */
	public void setChrISSN(String chrISSN) {
		this.chrISSN = chrISSN;
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
	 * Get the last page
	 * 
	 * @return  The last page
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
	
	@Transient
	@Override
	public List<String> getExternalCategories() {
		return EXT_CATEGORIES;
	}
}
