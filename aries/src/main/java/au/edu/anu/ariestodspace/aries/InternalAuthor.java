package au.edu.anu.ariestodspace.aries;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Internal author class that returns data based on the ANU authors
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("Internal")
public class InternalAuthor extends ResearchOutputsDataAuthors {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private UserAccounts user;
	
	/**
	 * Get the user
	 * 
	 * @return The user
	 */
	@ManyToOne
	@JoinColumn(name="chrStaffNumber",foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	public UserAccounts getUser() {
		return user;
	}

	/**
	 * Set the user
	 * 
	 * @param user The user
	 */
	public void setUser(UserAccounts user) {
		this.user = user;
	}

	/**
	 * Generate the author name
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
	 * Generate the author affiliation
	 */
	@Transient
	@DSpaceField("local.contributor.affiliation")
	public String getAffiliation() {
		if (user == null || getAuthorDepartment() == null) {
			if (user == null) {
				LOGGER.error("User is null");
			}
			else if (getAuthorDepartment() == null) {
				LOGGER.error("Author department is null");
			}
			return null;
		}
		StringBuilder sb = new StringBuilder();
		
		Departments department = getAuthorDepartment().getDepartment();
		if (department == null) {
			LOGGER.info("Department is null");
			return null;
		}
		sb.append(getName());
		sb.append(", ");
//		sb.append(department.getChrTier3Name());
//		sb.append(", ");
//		sb.append(department.getSchool().getChrTier2Name());
//		sb.append(", ");
		sb.append(department.getCollege().getChrCollegeName());
		sb.append(", ");
		sb.append(department.getInstitution().getChrTier1Name());
		
		return sb.toString();
	}
	
	@Transient
	public String getUid() {
		return user.getChrStaffNumber();
	}
	
	/**
	 * Get the authors university id
	 * 
	 * @return The university id
	 */
	@Transient
	@DSpaceField("local.contributor.authoruid")
	public String getFormattedUid() {
		if (user == null) {
			return null;
		}
		return getName() + ", " + user.getChrStaffNumber().toLowerCase();
	}
}
