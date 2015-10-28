package au.edu.anu.ariestodspace.aries.documents;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsDataDocuments;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity mapping for the 'Funding link' document name
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("FL")
public class FundingLink extends ResearchOutputsDataDocuments {
	@Override
	@DSpaceField("dc.relation")
	public String getChrURL() {
		return super.getChrURL();
	}
}
