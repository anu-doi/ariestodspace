package au.edu.anu.ariestodspace.aries.outputs.recorded;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity classes for the Inter-arts outputs and Other recorded outputs 
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("24126")
public class InterArtsAndOther extends Recorded {
}
