package au.edu.anu.ariestodspace.aries.outputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsJournals;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity for journal articles. Relates to the RO2 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("02XXX")
public class JournalArticle extends ResearchOutputsData1 {
	private String chrConferencePaperRefereed;
	private String chrIssue;
	private String chrPageNumbers;
	private String chrVolume;
	private ResearchOutputsJournals journal;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
				new ArrayList<String>()
				{
					private static final long serialVersionUID = 1L;
					{
						add("RL88");
						add("RL95");
						add("RL101");
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
		return "Journal article";
	}

	/**
	 * Get whether the journal article has been refereed
	 * 
	 * @return The refereed status
	 */
	@DSpaceField("local.description.refereed")
	public String getChrConferencePaperRefereed() {
		return chrConferencePaperRefereed;
	}

	/**
	 * Set whether the journal article has been refereed
	 * 
	 * @param chrConferencePaperRefereed The refereed status
	 */
	public void setChrConferencePaperRefereed(String chrConferencePaperRefereed) {
		this.chrConferencePaperRefereed = chrConferencePaperRefereed;
	}

	/**
	 * Get teh issue number of the journal article
	 * 
	 * @return The issue number
	 */
	@DSpaceField("local.bibliographicCitation.issue")
	public String getChrIssue() {
		return chrIssue;
	}

	/**
	 * Set the issue number of the journal article
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
	 * Get the journal volume
	 * 
	 * @return The volume
	 */
	@DSpaceField("local.identifier.citationvolume")
	public String getChrVolume() {
		return chrVolume;
	}

	/**
	 * set the journal volume
	 * 
	 * @param chrVolume The volume
	 */
	public void setChrVolume(String chrVolume) {
		this.chrVolume = chrVolume;
	}

	/**
	 * Get the journal
	 * 
	 * @return The journal
	 */
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="intJournalCode")
	public ResearchOutputsJournals getJournal() {
		return journal;
	}

	/**
	 * Set the journal
	 * 
	 * @param journal The journal
	 */
	public void setJournal(ResearchOutputsJournals journal) {
		this.journal = journal;
	}
	
	@Transient
	@Override
	public List<String> getExternalCategories() {
		return EXT_CATEGORIES;
//		List<String> categories = new ArrayList<String>();
//		categories.add("RL88");
//		categories.add("RL95");
//		categories.add("RL101");
//		return categories;
	}
}
