package au.edu.anu.ariestodspace.aries;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * External author class that returns data based on the authors external to ANU
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("External")
public class ExternalAuthor extends ResearchOutputsDataAuthors {
	private ExternalUsers user;
	
	/**
	 * Get the user
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name="chrStaffNumber",referencedColumnName="chrCode",foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	@BatchSize(size=10)
	public ExternalUsers getUser() {
		return user;
	}

	/**
	 * Set the user
	 * 
	 * @param user The user
	 */
	public void setUser(ExternalUsers user) {
		this.user = user;
	}

	/**
	 * Generate the name of the user
	 */
	@Transient
	@DSpaceField("dc.contributor.author")
	public String getName() {
		if (user == null) {
			return null;
		}
		return user.getChrSurname() + ", " + user.getChrFirstname();
	}

	/**
	 * Generate the affiliation of the author
	 */
	@Transient
	@DSpaceField("local.contributor.affiliation")
	public String getAffiliation() {
		if (user == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(", ");
		sb.append(user.getChrInstitutionName());
		return sb.toString();
	}
	
	/**
	 * Get the university id of the author
	 * @return
	 */
	@Transient
//	@DSpaceField("local.contributor.authoruid")
	public String getFormattedUid() {
		if (user == null) {
			return null;
		}
		return getName() + ", " + user.getChrCode();
	}
}
