package au.edu.anu.ariestodspace.aries.outputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * Entity class for reference works. Relates to the RO9 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("09XXX")
public class ReferenceWorks extends ResearchOutputsData1 {
	private String chrPageNumbers;
	private ResearchOutputsBooks book;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;

			{
					add("RL34");
					add("RL106");
			}}
		);
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The dspace type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Reference work";
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
	 * @return The last page
	 */
	@Transient
	@DSpaceField("local.bibliographicCitation.lastpage")
	public String getLastPage() {
		return getLastPage(chrPageNumbers);
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
//		List<String> categories = new ArrayList<String>();
//		categories.add("RL106");
//		categories.add("RL34");
//		return categories;
	}
}
