package au.edu.anu.ariestodspace.aries.outputs.originalwork;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity for the other creative works. Relates to RL121 and RL136 publication xategory.
 * 
 * @author Genevieve Turner
 */
@Entity
@DiscriminatorValue("23121")
public class OtherCreativeWork extends OriginalWork {

}
