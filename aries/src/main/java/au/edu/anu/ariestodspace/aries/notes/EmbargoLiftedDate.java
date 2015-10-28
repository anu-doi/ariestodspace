package au.edu.anu.ariestodspace.aries.notes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for the embargo lift date notes type
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("EL")
public class EmbargoLiftedDate extends ResearchOutputsNotes {
	@DSpaceField("local.description.embargo")
	public String getChrNotes() {
		return super.getChrNotes();
	}
}
