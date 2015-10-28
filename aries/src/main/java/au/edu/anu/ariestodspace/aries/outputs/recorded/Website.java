package au.edu.anu.ariestodspace.aries.outputs.recorded;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity for websites. Relates to the RL129 and RL144 publication categories.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("24129")
public class Website extends Recorded {
	private Date dateOfRecording;

	/**
	 * Get the date the website was released
	 * 
	 * @return The date of release
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateOfRecording() {
		return dateOfRecording;
	}

	/**
	 * Set the date the website was released
	 * 
	 * @param dateOfRecording The date of release
	 */
	public void setDateOfRecording(Date dateOfRecording) {
		this.dateOfRecording = dateOfRecording;
	}
}
