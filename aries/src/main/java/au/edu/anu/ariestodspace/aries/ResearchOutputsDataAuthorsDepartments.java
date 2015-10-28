package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class for the 'Resarch_otuputs_data_authors_departments' table.  This table associates an author with a particular deparment for a publication
 * 
 * @author Genevieve Turner
 *
 */
@Table
@Entity(name="Research_outputs_data_authors_departments")
public class ResearchOutputsDataAuthorsDepartments {
	private String chrOutputDepartmentCode;
	private Departments department;
	private ResearchOutputsDataAuthors author;
	
	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrOutputDepartmentCode() {
		return chrOutputDepartmentCode;
	}
	
	/**
	 * Set the identifier
	 * 
	 * @param chrOutputDepartmentCode The identifier
	 */
	public void setChrOutputDepartmentCode(String chrOutputDepartmentCode) {
		this.chrOutputDepartmentCode = chrOutputDepartmentCode;
	}

	/**
	 * Get the department
	 * 
	 * @return The department
	 */
	@ManyToOne
	@JoinColumn(name="chrDepartmentCode", referencedColumnName="chrTier3Code")
	public Departments getDepartment() {
		return department;
	}

	/**
	 * Set the department
	 * 
	 * @param department The department
	 */
	public void setDepartment(Departments department) {
		this.department = department;
	}
	
	/**
	 * Get the author
	 * 
	 * @return The author
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="chrOutputInvestigatorCode",referencedColumnName="chrOutputInvestigatorCode")
	public ResearchOutputsDataAuthors getAuthor() {
		return author;
	}

	/**
	 * Set the author
	 * 
	 * @param author The author
	 */
	public void setAuthor(ResearchOutputsDataAuthors author) {
		this.author = author;
	}
}
