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

import au.edu.anu.ariestodspace.aries.ResearchOutputsBooks;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity for the book research type. Relates to the RO3 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("03XXX")
public class Book extends ResearchOutputsData1 {
	private String chrConferencePaperRefereed;
	private String chrPageNumbers;
	private String chrSeriesTitle;
	private ResearchOutputsBooks book;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;
			{
					add("RL87");
					add("RL91");
					add("RL93");
			}}
		);
	
	/**
	 * Get the DSpace publication type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Book";
	}

	/**
	 * Indicates whether the book has been refereed
	 * 
	 * @return The refereed status
	 */
	@DSpaceField("local.description.refereed")
	public String getChrConferencePaperRefereed() {
		return chrConferencePaperRefereed;
	}

	/**
	 * Set whether the book has been refereed
	 * 
	 * @param chrConferencePaperRefereed The refereed status
	 */
	public void setChrConferencePaperRefereed(String chrConferencePaperRefereed) {
		this.chrConferencePaperRefereed = chrConferencePaperRefereed;
	}

	/**
	 * Get the number of pages in the book
	 * 
	 * @return The number of pages
	 */
	@DSpaceField("dc.format.extent")
	public String getChrPageNumbers() {
		return chrPageNumbers;
	}

	/**
	 * Set the number of pages in the book
	 * 
	 * @param chrPageNumbers The number of pages
	 */
	public void setChrPageNumbers(String chrPageNumbers) {
		this.chrPageNumbers = chrPageNumbers;
	}
	
	/**
	 * Get the book series
	 * 
	 * @return The series
	 */
	@DSpaceField("dc.relation.ispartofseries")
	@Column(name="chrSeriesTitle",columnDefinition="text")
	public String getChrSeriesTitle() {
		return chrSeriesTitle;
	}

	/**
	 * Set the book series
	 * 
	 * @param chrSeriesTitle The series
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
	@NotFound(action=NotFoundAction.IGNORE)
	public ResearchOutputsBooks getBook() {
		return book;
	}

	/**
	 * Set the book
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
	}
}
