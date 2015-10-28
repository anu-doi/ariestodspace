package au.edu.anu.ariestodspace.aries.outputs;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;

/**
 * Ignore undefined objects
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("OTHER")
public class IgnoreOutput extends ResearchOutputsData1 {

}
