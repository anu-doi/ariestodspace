package au.edu.anu.ariestodspace.aries.documents;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.ResearchOutputsDataDocuments;

/**
 * All other documents
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("OT")
public class OtherDocument extends ResearchOutputsDataDocuments {

}
