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

import au.edu.anu.ariestodspace.aries.ResearchOutputsBooks;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Get the book chapter. Relates to the RO1 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("01XXX")
public class BookChapter extends ResearchOutputsData1 {
	private String chrConferencePaperRefereed;
	private String chrPageNumbers;
	private String chrSeriesTitle;
	private ResearchOutputsBooks book;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;

			{
					add("RL89");
					add("RL94");
					add("RL96");
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
		return "Book chapter";
	}
	
	/**
	 * Get whether the chapter was refereed
	 * 
	 * @return The indicator
	 */
	@DSpaceField("local.description.refereed")
	public String getChrConferencePaperRefereed() {
		return chrConferencePaperRefereed;
	}
	
	/**
	 * Set wehther the chapter was refereed
	 * 
	 * @param chrConferencePaperRefereed The indicator
	 */
	public void setChrConferencePaperRefereed(String chrConferencePaperRefereed) {
		this.chrConferencePaperRefereed = chrConferencePaperRefereed;
	}

	/**
	 * Get the chapter page numbers
	 * 
	 * @return The page numbers
	 */
	public String getChrPageNumbers() {
		return chrPageNumbers;
	}

	/**
	 * Set the chapter page numbers
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
	 * Get teh last page
	 * 
	 * @return The last page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.lastpage")
	public String getLastPage() {
		return getLastPage(chrPageNumbers);
	}

	/**
	 * Get the book series title
	 * 
	 * @return The series title
	 */
	@DSpaceField("dc.relation.ispartofseries")
	@Column(name="chrSeriesTitle",columnDefinition="text")
	public String getChrSeriesTitle() {
		return chrSeriesTitle;
	}

	/**
	 * Set the book series title
	 * 
	 * @param chrSeriesTitle The series title
	 */
	public void setChrSeriesTitle(String chrSeriesTitle) {
		this.chrSeriesTitle = chrSeriesTitle;
	}
	
	/**
	 * Get the book
	 * 
	 * @return The book
	 */
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="intBookCode")
	public ResearchOutputsBooks getBook() {
		return book;
	}

	/**
	 * SEt the book
	 * 
	 * @param book The book
	 */
	public void setBook(ResearchOutputsBooks book) {
		this.book = book;
	}
	
	@Transient
	@Override
	public List<String> getExternalCategories() {
		return EXT_CATEGORIES;
//		List<String> categories = new ArrayList<String>();
//		categories.add("RL89");
//		categories.add("RL94");
//		categories.add("RL96");
//		return categories;
	}
}
