package au.edu.anu.ariestodspace.aries.notes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for the copyright information notes type
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("CI")
public class CopyrightInformation extends ResearchOutputsNotes {
	@DSpaceField("dc.rights")
	public String getChrNotes() {
		return super.getChrNotes();
	}
}
