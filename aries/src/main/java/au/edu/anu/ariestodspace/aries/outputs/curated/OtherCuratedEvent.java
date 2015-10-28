package au.edu.anu.ariestodspace.aries.outputs.curated;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity class for the other curated events. Relates to the RL114 and RL150 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("21114")
public class OtherCuratedEvent extends Curated {
}
