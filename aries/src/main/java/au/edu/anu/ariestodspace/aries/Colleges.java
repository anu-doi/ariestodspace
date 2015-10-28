package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity that maps to the 'Colleges' table in ARIES
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="colleges")
public class Colleges {
	private String chrCollegeCode;
	private String chrCollegeName;
	
	/**
	 * Get the Identifier for the college
	 * 
	 * @return The college code
	 */
	@Id
	public String getChrCollegeCode() {
		return chrCollegeCode;
	}
	
	/**
	 * Set the Identifier for hte college
	 * 
	 * @param chrCollegeCode The college code
	 */
	public void setChrCollegeCode(String chrCollegeCode) {
		this.chrCollegeCode = chrCollegeCode;
	}
	
	/**
	 * Get the name of the college
	 * 
	 * @return The college name
	 */
	public String getChrCollegeName() {
		return chrCollegeName;
	}
	
	/**
	 * Set the college name
	 * 
	 * @param chrCollegeName
	 */
	public void setChrCollegeName(String chrCollegeName) {
		this.chrCollegeName = chrCollegeName;
	}
}
