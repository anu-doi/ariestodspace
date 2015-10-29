package au.edu.anu.ariestodspace.aries;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity class for the 'Research_otuputs_data_authors' table in ARIES.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_data_authors")
@DiscriminatorColumn(name="chrRoute")
public class ResearchOutputsDataAuthors {
	private String chrOutputInvestigatorCode;
	private ResearchOutputsData1 data1;
	private String chrOrder;
	private ResearchOutputsDataAuthorsDepartments authorDepartment;
	
	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrOutputInvestigatorCode() {
		return chrOutputInvestigatorCode;
	}

	/**
	 * Set the identifier
	 * 
	 * @param chrOutputInvestigatorCode The identifier
	 */
	public void setChrOutputInvestigatorCode(String chrOutputInvestigatorCode) {
		this.chrOutputInvestigatorCode = chrOutputInvestigatorCode;
	}

	/**
	 * Get the research output
	 * 
	 * @return The research output
	 */
	@ManyToOne
	@JoinColumn(name="chrOutput6Code")
	public ResearchOutputsData1 getData1() {
		return data1;
	}

	/**
	 * Set the research output
	 * 
	 * @param data1 The research output
	 */
	public void setData1(ResearchOutputsData1 data1) {
		this.data1 = data1;
	}

	/**
	 * Get the author position
	 * 
	 * @return The position
	 */
	public String getChrOrder() {
		return chrOrder;
	}

	/**
	 * Set the author position
	 * 
	 * @param chrOrder THe position
	 */
	public void setChrOrder(String chrOrder) {
		this.chrOrder = chrOrder;
	}

	/**
	 * Get the authors department for this publication
	 * 
	 * @return The department
	 */
	@OneToOne(optional=true,mappedBy="author")
	public ResearchOutputsDataAuthorsDepartments getAuthorDepartment() {
		return authorDepartment;
	}

	/**
	 * Set the authors department for this publication
	 * 
	 * @param authorDepartment The department
	 */
	public void setAuthorDepartment(
			ResearchOutputsDataAuthorsDepartments authorDepartment) {
		this.authorDepartment = authorDepartment;
	}

	/**
	 * Get the authors name
	 * 
	 * @return The name
	 */
	@Transient
	public String getName() {
		return null;
	}
	
	/**
	 * Get the affiliation
	 * 
	 * @return The affliation
	 */
	@Transient
	public String getAffiliation() {
		return null;
	}
	
	@Transient
	public String getUid() {
		return null;
	}
}
