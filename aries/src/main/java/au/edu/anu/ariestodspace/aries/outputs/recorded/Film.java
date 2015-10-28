package au.edu.anu.ariestodspace.aries.outputs.recorded;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for film recordings. Relates to the RL125 and RL145 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("24125")
public class Film extends Recorded {
	private String chrCatalogueNo;
	private Date chrReleaseDate;
	
	/**
	 * Get the DSpace type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Multimedia";
	}

	/**
	 * Get the catalogue number
	 * 
	 * @return The catalogue number
	 */
	@DSpaceField("dc.identifier")
	public String getChrCatalogueNo() {
		return chrCatalogueNo;
	}

	/**
	 * Set the catalogue number
	 * 
	 * @param chrCatalogueNo The catalogue number
	 */
	public void setChrCatalogueNo(String chrCatalogueNo) {
		this.chrCatalogueNo = chrCatalogueNo;
	}

	/**
	 * Get the release date
	 * 
	 * @return The release date
	 */
	@DSpaceField("dc.date.issued")
	public Date getChrReleaseDate() {
		return chrReleaseDate;
	}

	/**
	 * Set the release date
	 * 
	 * @param chrReleaseDate The release date
	 */
	public void setChrReleaseDate(Date chrReleaseDate) {
		this.chrReleaseDate = chrReleaseDate;
	}
}
