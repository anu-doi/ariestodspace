package au.edu.anu.ariestodspace.aries.notes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsNotes;

/**
 * Entity for all the other notes types
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("OT")
public class OtherNotes extends ResearchOutputsNotes {

}
