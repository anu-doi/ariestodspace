package au.edu.anu.ariestodspace.aries.outputs;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.util.Util;

/**
 * Entity class for the Translation outputs. Relates to the RO20 output type.
 * 
 * @author Genevieve Turner
 */
@Entity
@DiscriminatorValue("20XXX")
public class Translation extends ResearchOutputsData1 {
	private String chrComments;
	private String chrEdition;
	private String chrEditorOfBook;
	private String chrFellowshipDescription;
	
	/**
	 * Get the type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Translation";
	}

	/**
	 * Get the original language
	 * 
	 * @return The language
	 */
	@Column(name="chrComments",columnDefinition="text")
	public String getChrComments() {
		return chrComments;
	}

	/**
	 * Set the original language
	 * 
	 * @param chrComments The language
	 */
	public void setChrComments(String chrComments) {
		this.chrComments = chrComments;
	}

	/**
	 * Get the edition
	 * 
	 * @return The edition
	 */
	@DSpaceField("dc.relation.isversionof")
	public String getChrEdition() {
		return chrEdition;
	}

	/**
	 * Set the edition
	 * 
	 * @param chrEdition The edition
	 */
	public void setChrEdition(String chrEdition) {
		this.chrEdition = chrEdition;
	}

	/**
	 * Get the editors
	 * 
	 * @return The editors
	 */
	public String getChrEditorOfBook() {
		return chrEditorOfBook;
	}
	
	@Transient
	@DSpaceField("dc.contributor.editor")
	public List<String> getEditors() {
		return Util.parseEditors(chrEditorOfBook);
	}

	/**
	 * SEt the editors
	 * 
	 * @param chrEditorOfBook The editors
	 */
	public void setChrEditorOfBook(String chrEditorOfBook) {
		this.chrEditorOfBook = chrEditorOfBook;
	}

	/**
	 * Get the language of the translation
	 * @return
	 */
	public String getChrFellowshipDescription() {
		return chrFellowshipDescription;
	}

	/**
	 * Set the language of the translation
	 * 
	 * @param chrFellowshipDescription
	 */
	public void setChrFellowshipDescription(String chrFellowshipDescription) {
		this.chrFellowshipDescription = chrFellowshipDescription;
	}
	
	/**
	 * Get a description of the translation
	 * 
	 * @return The description
	 */
	@Transient
	@DSpaceField("local.description.notes")
	public String getDescriptionNotes() {
		if (chrComments != null && chrFellowshipDescription != null) {
			return "This item has been translated from " + chrComments + " to " + chrFellowshipDescription + ".";
		}
		return null;
	}
}
