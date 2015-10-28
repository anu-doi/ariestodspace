package au.edu.anu.ariestodspace.aries;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;

/**
 * Entity class for the 'Resarch_outputs_data_documents' table in the ARIES database.
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_data_documents")
@DiscriminatorColumn(name="chrDocumentName")
@DiscriminatorFormula("case when chrDocumentName = 'ANU Repository Link' then 'RL' "
		+ "when chrDocumentName = 'Funding link' then 'FL' "
		+ "else 'OT' end")
public class ResearchOutputsDataDocuments {
	private String chrOutputDocumentCode;
	private String chrOutput6Code;
	private Integer intOutputDocumentCounter;
	private String chrDocumentName;
	private String chrURL;
	private Integer intDocumentType;
	private String chrFileExtention;
	private String chrOutputFileName;
	
	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@Id
	public String getChrOutputDocumentCode() {
		return chrOutputDocumentCode;
	}
	
	/**
	 * Set the identifier
	 * 
	 * @param chrOutputDocumentCode The identifier
	 */
	public void setChrOutputDocumentCode(String chrOutputDocumentCode) {
		this.chrOutputDocumentCode = chrOutputDocumentCode;
	}
	
	/**
	 * Get the output identifier
	 * 
	 * @return The output identifier
	 */
	public String getChrOutput6Code() {
		return chrOutput6Code;
	}
	
	/**
	 * Set the output identifier
	 * 
	 * @param chrOutput6Code The output identifier
	 */
	public void setChrOutput6Code(String chrOutput6Code) {
		this.chrOutput6Code = chrOutput6Code;
	}
	
	/**
	 * Get the document counter
	 * 
	 * @return The output counter
	 */
	public Integer getIntOutputDocumentCounter() {
		return intOutputDocumentCounter;
	}
	
	/**
	 * SEt the document counter
	 * 
	 * @param intOutputDocumentCounter The output counter
	 */
	public void setIntOutputDocumentCounter(Integer intOutputDocumentCounter) {
		this.intOutputDocumentCounter = intOutputDocumentCounter;
	}
	
	/**
	 * Get the document name
	 * 
	 * @return The document name
	 */
	public String getChrDocumentName() {
		return chrDocumentName;
	}
	
	/**
	 * Set the document name
	 * 
	 * @param chrDocumentName The document name
	 */
	public void setChrDocumentName(String chrDocumentName) {
		this.chrDocumentName = chrDocumentName;
	}
	
	/**
	 * Get the document URL
	 * 
	 * @return The URL
	 */
	@Column(name="chrURL",insertable=false,updatable=false)
	public String getChrURL() {
		return chrURL;
	}
	
	/**
	 * Set the document URL
	 * 
	 * @param chrURL The URL
	 */
	public void setChrURL(String chrURL) {
		this.chrURL = chrURL;
	}

	/**
	 * Get the document type
	 * 
	 * @return The document type
	 */
	public Integer getIntDocumentType() {
		return intDocumentType;
	}

	/**
	 * Set the document type
	 * 
	 * @param intDocumentType The document type
	 */
	public void setIntDocumentType(Integer intDocumentType) {
		this.intDocumentType = intDocumentType;
	}

	/**
	 * Get the file extension
	 * 
	 * @return The file extension
	 */
	public String getChrFileExtention() {
		return chrFileExtention;
	}

	/**
	 * Set the file extension
	 * 
	 * @param chrFileExtension The file extension
	 */
	public void setChrFileExtention(String chrFileExtention) {
		this.chrFileExtention = chrFileExtention;
	}

	/**
	 * Get the file name
	 * 
	 * @return The file name
	 */
	public String getChrOutputFileName() {
		return chrOutputFileName;
	}

	/**
	 * Set the file name
	 * 
	 * @param chrOutputFileName The file name
	 */
	public void setChrOutputFileName(String chrOutputFileName) {
		this.chrOutputFileName = chrOutputFileName;
	}
}
