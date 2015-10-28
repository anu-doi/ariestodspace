package au.edu.anu.ariestodspace.aries.notes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for the keywords notes type
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("KW")
public class Keywords extends ResearchOutputsNotes {
	@DSpaceField("dc.subject")
	public String getChrNotes() {
		return super.getChrNotes();
	}
	
	public void setChrNotes(String chrNotes) {
		super.setChrNotes(chrNotes);
	}
}
