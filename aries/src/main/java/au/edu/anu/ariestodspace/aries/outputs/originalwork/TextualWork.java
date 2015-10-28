package au.edu.anu.ariestodspace.aries.outputs.originalwork;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for textual works. Relates to the RL122 and RL135 publication categories. 
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("23122")
public class TextualWork extends OriginalWork {
	private String chrISBN;
	private String chrISSN;
	
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
	 * @param chrISBN The isbn
	 */
	public void setChrISBN(String chrISBN) {
		this.chrISBN = chrISBN;
	}
	
	/**
	 * Get the issn.
	 * 
	 * @return The issn
	 */
	@DSpaceField("dc.identifier.issn")
	public String getChrISSN() {
		return chrISSN;
	}
	
	/**
	 * Set the issn
	 * 
	 * @param chrISSN The issn
	 */
	public void setChrISSN(String chrISSN) {
		this.chrISSN = chrISSN;
	}
}
