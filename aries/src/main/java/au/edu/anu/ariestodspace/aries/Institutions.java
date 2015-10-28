package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for the 'Institutions' table in the ARIES database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Institutions")
public class Institutions {
	private String chrTier1Code;
	private String chrTier1Name;
	
	/**
	 * Get the institution identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrTier1Code() {
		return chrTier1Code;
	}
	
	/**
	 * SEt the institution identifier
	 * 
	 * @param chrTier1Code The identifier
	 */
	public void setChrTier1Code(String chrTier1Code) {
		this.chrTier1Code = chrTier1Code;
	}
	
	/**
	 * Get the institution name
	 * 
	 * @return The institution name
	 */
	public String getChrTier1Name() {
		return chrTier1Name;
	}
	
	/**
	 * Set the institution name
	 * 
	 * @param chrTier1Name The institution name
	 */
	public void setChrTier1Name(String chrTier1Name) {
		this.chrTier1Name = chrTier1Name;
	}
}
