package au.edu.anu.ariestodspace.aries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;

/**
 * Entity class for 'Research_outputs_notes' table in the ARIES database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_notes")
@DiscriminatorFormula("case when intNotesType = 5 then 'CI' "
		+ "when intNotesType = 6 then 'EL' "
		+ "when intNotesType = 4 then 'KW' "
		+ "when intNotesType = 3 then 'GF' "
		+ "else 'OT' end")
public class ResearchOutputsNotes {
	private String chrOutputsNotesCode;
	private ResearchOutputsData1 data1;
	private Integer intOutputNotesCounter;
	private Integer intNotesType;
	private String chrNotes;

	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrOutputsNotesCode() {
		return chrOutputsNotesCode;
	}

	/**
	 * SEt the identifier
	 * 
	 * @param chrOutputsNotesCode The identifier
	 */
	public void setChrOutputsNotesCode(String chrOutputsNotesCode) {
		this.chrOutputsNotesCode = chrOutputsNotesCode;
	}
	
	/**
	 * Get the ARIES publication
	 * 
	 * @return The publication
	 */
	@ManyToOne
	@JoinColumn(name="chrOutput6Code")
	public ResearchOutputsData1 getData1() {
		return data1;
	}

	/**
	 * Set the ARIES publication
	 * 
	 * @param data1 The publication
	 */
	public void setData1(ResearchOutputsData1 data1) {
		this.data1 = data1;
	}

	/**
	 * Get the notes counter
	 * 
	 * @return The counter
	 */
	public Integer getIntOutputNotesCounter() {
		return intOutputNotesCounter;
	}

	/**
	 * Set the notes counter
	 * 
	 * @param intOutputNotesCounter The counter
	 */
	public void setIntOutputNotesCounter(Integer intOutputNotesCounter) {
		this.intOutputNotesCounter = intOutputNotesCounter;
	}
	
	/**
	 * Get the notes type
	 * 
	 * @return  The type
	 */
	public Integer getIntNotesType() {
		return intNotesType;
	}

	/**
	 * Set the notes type
	 * @param intNotesType
	 */
	public void setIntNotesType(Integer intNotesType) {
		this.intNotesType = intNotesType;
	}

	/**
	 * Get the note
	 * 
	 * @return The note
	 */
	@Column(insertable=false, updatable=false)
	public String getChrNotes() {
		return chrNotes;
	}

	/**
	 * Set the notes type
	 * 
	 * @param chrNotes The notes
	 */
	public void setChrNotes(String chrNotes) {
		this.chrNotes = chrNotes;
	}
}
