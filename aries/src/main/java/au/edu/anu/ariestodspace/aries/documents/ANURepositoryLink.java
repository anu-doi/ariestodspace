package au.edu.anu.ariestodspace.aries.documents;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsDataDocuments;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity mapping for the 'ANU Repository Link' document name
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("RL")
public class ANURepositoryLink extends ResearchOutputsDataDocuments {
	@Override
	@DSpaceField("dc.identifier.uri")
	public String getChrURL() {
		return super.getChrURL();
	}
}
