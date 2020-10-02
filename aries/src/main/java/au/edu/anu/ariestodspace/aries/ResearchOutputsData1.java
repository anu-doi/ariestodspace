package au.edu.anu.ariestodspace.aries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceFieldObject;

/**
 * Entity class for the 'Research_outputs_data1' table in the ARIES database.  The table is the main class for the publications in ARIES.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="Research_outputs_data1")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(value="case when chrOutput2Code = 'RL151' or chrOutput2Code = 'RL154' or chrOutput2Code = 'RL156' or chrOutput2Code = 'RL157' or chrOutput2Code = 'RL158' then 'OTHER' "
		+ "when chrOutput1Code = 'RO1' then '01XXX' "
		+ "when chrOutput1Code = 'RO2' then '02XXX' "
		+ "when chrOutput1Code = 'RO3' then '03XXX' "
		+ "when chrOutput1Code = 'RO4' then '04XXX' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL37' then '05037' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL78' then '05078' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL30' then '05030' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL130' then '05130' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL131' then '05131' "
		+ "when chrOutput1Code = 'RO5' and chrOutput2Code = 'RL132' then '05132' "
		+ "when chrOutput1Code = 'RO6' then '06XXX' "
		+ "when chrOutput1Code = 'RO9' then '09XXX' "
		+ "when chrOutput1Code = 'RO10' then '10XXX' "
		+ "when chrOutput1Code = 'RO11' then '11XXX' "
		+ "when chrOutput1Code = 'RO12' then '12XXX' "
		+ "when chrOutput1Code = 'RO14' then '14XXX' "
		+ "when chrOutput1Code = 'RO15' then '15XXX' "
		+ "when chrOutput1Code = 'RO17' then '17XXX' "
		+ "when chrOutput1Code = 'RO20' then '20XXX' "
		+ "when chrOutput1Code = 'RO21' and (chrOutput2Code = 'RL115' or chrOutput2Code = 'RL146') then '21115' "
		+ "when chrOutput1Code = 'RO21' and (chrOutput2Code = 'RL112' or chrOutput2Code = 'RL139') then '21112' "
		+ "when chrOutput1Code = 'RO21' and (chrOutput2Code = 'RL113' or chrOutput2Code = 'RL140') then '21113' "
		+ "when chrOutput1Code = 'RO21' and (chrOutput2Code = 'RL114' or chrOutput2Code = 'RL150') then '21114' "
		+ "when chrOutput1Code = 'RO22' and (chrOutput2Code = 'RL117' or chrOutput2Code = 'RL137') then '22117' "
		+ "when chrOutput1Code = 'RO22' then '22119' "
		+ "when chrOutput1Code = 'RO23' and (chrOutput2Code = 'RL123' or chrOutput2Code = 'RL133') then '23123' "
		+ "when chrOutput1Code = 'RO23' and (chrOutput2Code = 'RL120' or chrOutput2Code = 'RL134') then '23120' "
		+ "when chrOutput1Code = 'RO23' and (chrOutput2Code = 'RL122' or chrOutput2Code = 'RL135') then '23122' "
		+ "when chrOutput1Code = 'RO23' and (chrOutput2Code = 'RL121' or chrOutput2Code = 'RL136') then '23121' "
		+ "when chrOutput1Code = 'RO24' and (chrOutput2Code = 'RL128' or chrOutput2Code = 'RL141') then '24128' "
		+ "when chrOutput1Code = 'RO24' and (chrOutput2Code = 'RL126' or chrOutput2Code = 'RL142' or chrOutput2Code = 'RL127' or chrOutput2Code = 'RL147') then '24126' "
		+ "when chrOutput1Code = 'RO24' and (chrOutput2Code = 'RL124' or chrOutput2Code = 'RL143') then '24124' "
		+ "when chrOutput1Code = 'RO24' and (chrOutput2Code = 'RL129' or chrOutput2Code = 'RL144') then '24129' "
		+ "when chrOutput1Code = 'RO24' and (chrOutput2Code = 'RL125' or chrOutput2Code = 'RL145') then '24125' "
		+ "else 'OTHER' end")
public class ResearchOutputsData1 {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private String chrApplicationDescription;
	private String chrCalculatePoints;
	private String chrCreatedByCode;
	private String chrOutput1Code;
	private String chrOutput2Code;
	private Integer chrOutput6CodeCounter;
	private String chrOutput6Code;
	private String chrReportingYear;
	private String chrPublicationTitle;
	private String chrScopusID;
	private String chrThompsonID;
	private String chrUrlArticle;
	private ForCodes chrFORcode1;
	private ForCodes chrFORcode2;
	private ForCodes chrFORcode3;
	private SeoCodes chrSEOncode1;
	private SeoCodes chrSEOncode2;
	private SeoCodes chrSEOncode3;
	private Date chrAmendedByDateTime;
	private Date chrCreatedByDateTime;
	private List<ResearchOutputsDataAuthors> authors = new ArrayList<ResearchOutputsDataAuthors>();
	private List<ResearchOutputsNotes> notes = new ArrayList<ResearchOutputsNotes>();
	private List<ResearchOutputsDataDocuments> documents = new ArrayList<ResearchOutputsDataDocuments>();
	
	/**
	 * Constructor
	 */
	public ResearchOutputsData1 () {
		
	}

	/**
	 * Get the research outputs abstract
	 * 
	 * @return The abstract
	 */
	@DSpaceField("dc.description.abstract")
	@Column(name="chrApplicationDescription",columnDefinition="text")
	public String getChrApplicationDescription() {
		return chrApplicationDescription;
	}

	/**
	 * Set the research outputs abstract
	 * 
	 * @param chrApplicationDescription The abstract
	 */
	public void setChrApplicationDescription(String chrApplicationDescription) {
		this.chrApplicationDescription = chrApplicationDescription;
	}

	/**
	 * Get the calculate points status.  If the value is 'Yes' it is a verified record.
	 * 
	 * @return The calculate points status
	 */
	public String getChrCalculatePoints() {
		return chrCalculatePoints;
	}

	/**
	 * Set the calculate points status.  If the value is 'Yes' it is a verified record.
	 * 
	 * @param chrCalculatePoints The calculate points status
	 */
	public void setChrCalculatePoints(String chrCalculatePoints) {
		this.chrCalculatePoints = chrCalculatePoints;
	}

	/**
	 * Get who created the record in ARIES
	 * 
	 * @return The record  creator
	 */
	@DSpaceField("local.identifier.uidSubmittedBy")
	@Column(name="chrCreatedByCode")
	public String getChrCreatedByCode() {
		return chrCreatedByCode;
	}

	/**
	 * Set who created the record
	 * 
	 * @param chrCreatedByCode The record creator
	 */
	public void setChrCreatedByCode(String chrCreatedByCode) {
		this.chrCreatedByCode = chrCreatedByCode;
	}

	/**
	 * Get the type of research output
	 * 
	 * @return The output type
	 */
	@Column(name="chrOutput1Code", insertable=false, updatable=false)
	public String getChrOutput1Code() {
		return chrOutput1Code;
	}

	/**
	 * Get the type of research output
	 * 
	 * @param chrOutput1Code The output type
	 */
	public void setChrOutput1Code(String chrOutput1Code) {
		this.chrOutput1Code = chrOutput1Code;
	}

	/**
	 * Get the publication category
	 * 
	 * @return The publication category
	 */
	@Column(name="chrOutput2Code")
	public String getChrOutput2Code() {
		return chrOutput2Code;
	}

	/**
	 * Set the publication category
	 * 
	 * @param chrOutput2Code The publication category
	 */
	public void setChrOutput2Code(String chrOutput2Code) {
		this.chrOutput2Code = chrOutput2Code;
	}

	/**
	 * Get the publication identifier
	 * 
	 * @return The publication identifier
	 */
	@Id
	@Column(name="chrOutput6Code", insertable=false, updatable=false)
	@DSpaceField("local.identifier.ariespublication")
	public String getChrOutput6Code() {
		return chrOutput6Code;
	}

	/**
	 * Set the publication identifier
	 * 
	 * @param chrOutput6Code The publication identifier
	 */
	public void setChrOutput6Code(String chrOutput6Code) {
		this.chrOutput6Code = chrOutput6Code;
	}

	/**
	 * Get the output 6 code counter
	 * 
	 * @return The counter
	 */
	public Integer getChrOutput6CodeCounter() {
		return chrOutput6CodeCounter;
	}

	/**
	 * Set the output 6 code counter
	 * 
	 * @param chrOutput6CodeCounter The counter
	 */
	public void setChrOutput6CodeCounter(Integer chrOutput6CodeCounter) {
		this.chrOutput6CodeCounter = chrOutput6CodeCounter;
	}

	/**
	 * Get the publication date
	 * 
	 * @return The publication date
	 */
	@Column(name="chrReportingYear",insertable=false,updatable=false)
	@DSpaceField("dc.date.issued")
	public String getChrReportingYear() {
		return chrReportingYear;
	}

	/**
	 * Set the publication date
	 * 
	 * @param chrReportingYear The publication date
	 */
	public void setChrReportingYear(String chrReportingYear) {
		this.chrReportingYear = chrReportingYear;
	}

	/**
	 * Get the publication title
	 * 
	 * @return The publication title
	 */
	@Column(name="chrPublicationTitle",columnDefinition="text")
	@DSpaceField("dc.title")
	public String getChrPublicationTitle() {
		return chrPublicationTitle;
	}

	/**
	 * Set the publication title
	 * 
	 * @param chrPublicationTitle The publication title
	 */
	public void setChrPublicationTitle(String chrPublicationTitle) {
		this.chrPublicationTitle = chrPublicationTitle;
	}

	/**
	 * Get the publication SCOPUS ID
	 * 
	 * @return The scopus id
	 */
	@DSpaceField("local.identifier.scopusID")
	public String getChrScopusID() {
		return chrScopusID;
	}

	/**
	 * Set the publication SCOPUS ID
	 * 
	 * @param chrScopusID The scopus id
	 */
	public void setChrScopusID(String chrScopusID) {
		this.chrScopusID = chrScopusID;
	}

	/**
	 * Get the thomson-reuters web of knowledge id
	 * 
	 * @return The thomson-reuters id
	 */
	@DSpaceField("local.identifier.thomsonID")
	public String getChrThompsonID() {
		return chrThompsonID;
	}

	/**
	 * Set the thomson-reuters web of knowledge id
	 * 
	 * @param chrThompsonID The thomson-reuters id
	 */
	public void setChrThompsonID(String chrThompsonID) {
		this.chrThompsonID = chrThompsonID;
	}

	/**
	 * Get a url associated with the publication.  This is either a website or a DOI.
	 * 
	 * @return The url
	 */
	public String getChrUrlArticle() {
		return chrUrlArticle;
	}

	/**
	 * Set the url associated with the publication. This is either a website or a DOI.
	 * 
	 * @param chrUrlArticle THe url
	 */
	public void setChrUrlArticle(String chrUrlArticle) {
		this.chrUrlArticle = chrUrlArticle;
	}

	/**
	 * Get the DOI
	 * 
	 * @return The DOI
	 */
	@Transient
	@DSpaceField("local.identifier.doi")
	public String getDOI() {
		if (chrUrlArticle != null && chrUrlArticle.matches("https?://(dx\\.|)doi\\.org.*")) {
			return chrUrlArticle.substring(18);
		}
		return null;
	}
	
	/**
	 * Get the associated website
	 * 
	 * @return The website URL
	 */
	@Transient
	@DSpaceField("dc.source.uri")
	public String getSource() {
		if (chrUrlArticle != null && !chrUrlArticle.matches("https?://(dx\\.|)doi\\.org.*")) {
			return chrUrlArticle;
		}
		return null;
	}

	/**
	 * Get the first field of research subject
	 * 
	 * @return The field of research
	 */
//	@DSpaceField("local.identifier.absfor")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrFORcode1", referencedColumnName="chrForObjectiveCode")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public ForCodes getChrFORcode1() {
		return chrFORcode1;
	}

	/**
	 * Set the first field of research subject
	 * 
	 * @param chrFORcode1 The field of research
	 */
	public void setChrFORcode1(ForCodes chrFORcode1) {
		this.chrFORcode1 = chrFORcode1;
	}

	/**
	 * Get the second field of research subject
	 * 
	 * @return The field of research
	 */
//	@DSpaceField("local.identifier.absfor")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrFORcode2", referencedColumnName="chrForObjectiveCode")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public ForCodes getChrFORcode2() {
		return chrFORcode2;
	}

	/**
	 * Set the second field of research subject
	 * 
	 * @param chrFORcode2 The field of research
	 */
	public void setChrFORcode2(ForCodes chrFORcode2) {
		this.chrFORcode2 = chrFORcode2;
	}

	/**
	 * Get the third field of research subject
	 * 
	 * @return The field of research
	 */
//	@DSpaceField("local.identifier.absfor")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrFORcode3", referencedColumnName="chrForObjectiveCode")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public ForCodes getChrFORcode3() {
		return chrFORcode3;
	}

	/**
	 * SEt the third field of research subject
	 * 
	 * @param chrFORcode3 The field of research
	 */
	public void setChrFORcode3(ForCodes chrFORcode3) {
		this.chrFORcode3 = chrFORcode3;
	}

	/**
	 * Get the first socio-economic objective
	 * 
	 * @return The socio-deconomic objective
	 */
//	@DSpaceField("local.identifier.absseo")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrSEOncode1")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public SeoCodes getChrSEOncode1() {
		return chrSEOncode1;
	}

	/**
	 * Set the first socio-economic objective
	 * 
	 * @param chrSEOncode1 THe socio-economic objective
	 */
	public void setChrSEOncode1(SeoCodes chrSEOncode1) {
		this.chrSEOncode1 = chrSEOncode1;
	}

	/**
	 * Get the second socio-economic objective
	 * 
	 * @return THe socio-economic objective
	 */
//	@DSpaceField("local.identifier.absseo")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrSEOncode2")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public SeoCodes getChrSEOncode2() {
		return chrSEOncode2;
	}

	/**
	 * Set the second socio-economic objective
	 * 
	 * @param chrSEOncode2 The socio-economic objective
	 */
	public void setChrSEOncode2(SeoCodes chrSEOncode2) {
		this.chrSEOncode2 = chrSEOncode2;
	}

	/**
	 * Get the third socio-economic objective
	 * 
	 * @return The socio-economic objective
	 */
//	@DSpaceField("local.identifier.absseo")
	@DSpaceFieldObject
	@ManyToOne
	@JoinColumn(name="chrSEOncode3")
	@NotFound(action=NotFoundAction.IGNORE)
	@BatchSize(size=10)
	public SeoCodes getChrSEOncode3() {
		return chrSEOncode3;
	}

	/**
	 * Set the third socio-economic objective
	 * 
	 * @param chrSEOncode3 The socio-economic objective
	 */
	public void setChrSEOncode3(SeoCodes chrSEOncode3) {
		this.chrSEOncode3 = chrSEOncode3;
	}
	
	/**
	 * Get the date and time the record was last amended
	 * 
	 * @return The last amendment date
	 */
	public Date getChrAmendedByDateTime() {
		return chrAmendedByDateTime;
	}

	/**
	 * Set the date and time the record was last amended
	 * 
	 * @param chrAmendedByDateTime  The last amendment date
	 */
	public void setChrAmendedByDateTime(Date chrAmendedByDateTime) {
		this.chrAmendedByDateTime = chrAmendedByDateTime;
	}

	/**
	 * Set the date and time the record was created
	 * 
	 * @return The creation date
	 */
	public Date getChrCreatedByDateTime() {
		return chrCreatedByDateTime;
	}

	/**
	 * Set the date and time the record was created
	 * 
	 * @param chrCreatedByDateTime The creation date
	 */
	public void setChrCreatedByDateTime(Date chrCreatedByDateTime) {
		this.chrCreatedByDateTime = chrCreatedByDateTime;
	}

	/**
	 * Get the start page
	 * 
	 * @param pageNumbers The page numbers to parse
	 * @return The start page
	 */
	protected String getStartPage(String pageNumbers) {
		if (pageNumbers == null || pageNumbers.trim().length() == 0 || pageNumbers.contains("pp") || pageNumbers.contains("pages")) {
			return null;
		}
		if (pageNumbers != null && pageNumbers.contains("-")) {
			String[] split = pageNumbers.split("-");
			if (split.length == 2) {
				return split[0].trim();
			}
		}
		return pageNumbers;
	}
	
	/**
	 * Get the last page
	 * 
	 * @param pageNumbers The page numbers to parse
	 * @return The last page
	 */
	protected String getLastPage(String pageNumbers) {
		if (pageNumbers == null || pageNumbers.trim().length() == 0 || pageNumbers.contains("pp") || pageNumbers.contains("pages")) {
			return null;
		}
		if (pageNumbers != null && pageNumbers.contains("-")) {
			String[] split = pageNumbers.split("-");
			if (split.length == 2) {
				return split[1].trim();
			}
		}
		return null;
	}

	/**
	 * Get the authors
	 * 
	 * @return The authors
	 */
	@DSpaceFieldObject
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="chrOutput6Code")
	@BatchSize(size=10)
	public List<ResearchOutputsDataAuthors> getAuthors() {
		return authors;
	}

	/**
	 * Set the authors
	 * 
	 * @param authors The authors
	 */
	public void setAuthors(List<ResearchOutputsDataAuthors> authors) {
		this.authors = authors;
	}

	/**
	 * Get the associated notes
	 * 
	 * @return The notes
	 */
	@DSpaceFieldObject
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="chrOutput6Code")
	@BatchSize(size=10)
	public List<ResearchOutputsNotes> getNotes() {
		return notes;
	}

	/**
	 * Set the associated notes
	 * 
	 * @param notes The notes
	 */
	public void setNotes(List<ResearchOutputsNotes> notes) {
		this.notes = notes;
	}

	/**
	 * Get the associated documents
	 * @return
	 */
	@DSpaceFieldObject
	@OneToMany(fetch=FetchType.EAGER)
	@BatchSize(size=10)
	@JoinColumn(name="chrOutput6Code")
	public List<ResearchOutputsDataDocuments> getDocuments() {
		return documents;
	}

	/**
	 * Set the associated documents
	 * 
	 * @param documents The documents
	 */
	public void setDocuments(List<ResearchOutputsDataDocuments> documents) {
		this.documents = documents;
	}
	
	@Transient
	public List<String> getExternalCategories() {
		return new ArrayList<String>();
	}
	
	@Transient
	@DSpaceField("local.type.status")
	public String getLocalStatus() {
		return "Published Version";
	}
}
