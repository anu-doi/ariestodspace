package au.edu.anu.ariestodspace.aries;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity that maps to the 'Departments' table in ARIES
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Departments")
public class Departments {
	private String chrTier3Code;
	private String chrTier3Name;
	private FacultySchoolCentre school;
	private Colleges college;
	private Institutions institution;
	
	/**
	 * Get the identifier for the department
	 * 
	 * @return The department code
	 */
	@Id
	public String getChrTier3Code() {
		return chrTier3Code;
	}

	/**
	 * Set the identifier for the department
	 * 
	 * @param chrTier3Code
	 */
	public void setChrTier3Code(String chrTier3Code) {
		this.chrTier3Code = chrTier3Code;
	}

	/**
	 * Get the department name
	 * 
	 * @return The department name
	 */
	public String getChrTier3Name() {
		return chrTier3Name;
	}

	/**
	 * Set the department name
	 * 
	 * @param chrTier3Name The department name
	 */
	public void setChrTier3Name(String chrTier3Name) {
		this.chrTier3Name = chrTier3Name;
	}

	/**
	 * Get the department school
	 * 
	 * @return The department school
	 */
	@ManyToOne
	@JoinColumn(name="chrTier2Code")
	public FacultySchoolCentre getSchool() {
		return school;
	}
	
	/**
	 * Set the department school
	 * 
	 * @param school  The department school
	 */
	public void setSchool(FacultySchoolCentre school) {
		this.school = school;
	}

	/**
	 * Get the department's college
	 * 
	 * @return The college
	 */
	@ManyToOne
	@JoinColumn(name="chrCollegeCode")
	public Colleges getCollege() {
		return college;
	}
	
	/**
	 * Set the departments college
	 * 
	 * @param college The college
	 */
	public void setCollege(Colleges college) {
		this.college = college;
	}

	/**
	 * Get the departments institution
	 * 
	 * @return The institution
	 */
	@ManyToOne
	@JoinColumn(name="chrTier1Code")
	public Institutions getInstitution() {
		return institution;
	}
	
	/**
	 * Set the departments institution
	 * 
	 * @param institution The institution
	 */
	public void setInstitution(Institutions institution) {
		this.institution = institution;
	}
}
