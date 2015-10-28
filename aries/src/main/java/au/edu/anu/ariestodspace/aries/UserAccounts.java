package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for the 'UserAccounts' table in the ARIES database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="UserAccounts")
public class UserAccounts {
	private String chrStaffNumber;
	private String chrSurname;
	private String chrFirstname;
	
	/**
	 * Get the ANU Uni ID
	 * 
	 * @return The Uni ID
	 */
	@Id
	public String getChrStaffNumber() {
		return chrStaffNumber;
	}

	/**
	 * Set the ANU Uni ID
	 * 
	 * @param chrStaffNumber The Uni ID
	 */
	public void setChrStaffNumber(String chrStaffNumber) {
		this.chrStaffNumber = chrStaffNumber;
	}

	/**
	 * Get the surname
	 * 
	 * @return The surname
	 */
	public String getChrSurname() {
		return chrSurname;
	}
	
	/**
	 * Set the surname
	 * 
	 * @param chrSurname The surname
	 */
	public void setChrSurname(String chrSurname) {
		this.chrSurname = chrSurname;
	}
	
	/**
	 * Get the given name
	 * 
	 * @return The given name
	 */
	public String getChrFirstname() {
		return chrFirstname;
	}
	
	/**
	 * Set the given name
	 * 
	 * @param chrFirstname The given name
	 */
	public void setChrFirstname(String chrFirstname) {
		this.chrFirstname = chrFirstname;
	}
}
