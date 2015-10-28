package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity that maps to the 'externalUsers' table in the ARIES database
 * 
 * @author Genevieve Turner
 */
@Entity
@Table(name="externalUsers")
public class ExternalUsers {
	private String chrCode;
	private String chrSurname;
	private String chrFirstname;
	private String chrInstitutionName;

	/**
	 * Get the identifier of the external user
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrCode() {
		return chrCode;
	}

	/**
	 * Set the identifier of the external user
	 * 
	 * @param chrCode The identifier
	 */
	public void setChrCode(String chrCode) {
		this.chrCode = chrCode;
	}

	/**
	 * Get the surname of the user
	 * 
	 * @return The surname
	 */
	public String getChrSurname() {
		return chrSurname;
	}
	
	/**
	 * Set the suername of the user
	 * 
	 * @param chrSurname The surname
	 */
	public void setChrSurname(String chrSurname) {
		this.chrSurname = chrSurname;
	}
	
	/**
	 * Get the given name of the user
	 * 
	 * @return The given name
	 */
	public String getChrFirstname() {
		return chrFirstname;
	}
	
	/**
	 * SEt the given name of the user
	 * 
	 * @param chrFirstname The given name
	 */
	public void setChrFirstname(String chrFirstname) {
		this.chrFirstname = chrFirstname;
	}

	/**
	 * Get the name of the institution the external user is associated with
	 * 
	 * @return The instittuion
	 */
	public String getChrInstitutionName() {
		return chrInstitutionName;
	}

	/**
	 * Set the name of the institution the external user is associated with
	 * 
	 * @param chrInstitutionName The institution name
	 */
	public void setChrInstitutionName(String chrInstitutionName) {
		this.chrInstitutionName = chrInstitutionName;
	}
}
