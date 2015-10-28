package au.edu.anu.ariestodspace.aries.notes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for the Research grant funding notes type
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("GF")
public class ResearchGrantFunding extends ResearchOutputsNotes {
	@DSpaceField("dc.description.sponsorship")
	public String getChrNotes() {
		return super.getChrNotes();
	}
}
