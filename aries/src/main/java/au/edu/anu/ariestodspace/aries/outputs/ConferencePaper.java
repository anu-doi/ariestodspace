package au.edu.anu.ariestodspace.aries.outputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import au.edu.anu.ariestodspace.aries.ResearchOutputsConferences;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity for Conference papers. Relates to the RO4 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("04XXX")
public class ConferencePaper extends ResearchOutputsData1 {
	private String chrConferencePaperRefereed;
	private String chrNumberOfPage;
	private String chrPageNumbers;
	private String chrSeriesTitle;
	private ResearchOutputsConferences conference;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;

			{
					add("RL90");
					add("RL97");
					add("RL99");
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
		if ("RL16".equals(getChrOutput2Code()) || "RL99".equals(getChrOutput2Code()))	{
			return "Conference proceedings";
		}
		return "Conference paper";
	}

	/**
	 * Get whether the conference paper has been refereed
	 *  
	 * @return The refereed status of the paper
	 */
	@DSpaceField("local.description.refereed")
	public String getChrConferencePaperRefereed() {
		return chrConferencePaperRefereed;
	}

	/**
	 * Set whether the conference paper has been refereed
	 * 
	 * param chrConferencePaperRefereed The refereed status of the paper
	 */
	public void setChrConferencePaperRefereed(String chrConferencePaperRefereed) {
		this.chrConferencePaperRefereed = chrConferencePaperRefereed;
	}

	/**
	 * Get the number of pages in the conference paper
	 * 
	 * @return The number of pages
	 */
	@DSpaceField("dc.format.extent")
	public String getChrNumberOfPage() {
		return chrNumberOfPage;
	}

	/**
	 * Set the number of pages in the conference paper
	 * 
	 * @param chrNumberOfPage The number of pages
	 */
	public void setChrNumberOfPage(String chrNumberOfPage) {
		this.chrNumberOfPage = chrNumberOfPage;
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
	 * SEt the last page
	 * 
	 * @return The last page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.lastpage")
	public String getLastPage() {
		return getLastPage(chrPageNumbers);
	}
	
	/**
	 * Get the conference paper series title
	 * 
	 * @return The series title
	 */
	@DSpaceField("dc.relation.ispartofseries")
	@Column(name="chrSeriesTitle",columnDefinition="text")
	public String getChrSeriesTitle() {
		return chrSeriesTitle;
	}

	/**
	 * Set the conference paper series title
	 * 
	 * @param chrSeriesTitle The series titel
	 */
	public void setChrSeriesTitle(String chrSeriesTitle) {
		this.chrSeriesTitle = chrSeriesTitle;
	}
	
	/**
	 * Get the conference
	 * 
	 * @return The conference
	 */
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="intConferenceCode")
	@NotFound(action=NotFoundAction.IGNORE)
	public ResearchOutputsConferences getConference() {
		return conference;
	}

	/**
	 * Set the conference
	 * 
	 * @param conference The conference
	 */
	public void setConference(ResearchOutputsConferences conference) {
		this.conference = conference;
	}
	
	@Transient
	@Override
	public List<String> getExternalCategories() {
		return EXT_CATEGORIES;
	}
}
