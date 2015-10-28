package au.edu.anu.ariestodspace.aries.outputs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for audio visual outputs. Relates to the RO11 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("11XXX")
public class XAudioVisual extends ResearchOutputsData1 {
	private String chrCatalogueNo;
	private String chrCommercialDistributor;
	private String chrFormatTypeOfAV;
	private String chrSeriesTitle;
	private Date dateOfRecording;
	
	/**
	 * Get the type
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
	@Column(name="chrCatalogueNo")
	public String getChrCatalogueNo() {
		return chrCatalogueNo;
	}

	/**
	 * Set the catalogue number
	 * 
	 * @param chrCatalogueNo  The catalogue number
	 */
	public void setChrCatalogueNo(String chrCatalogueNo) {
		this.chrCatalogueNo = chrCatalogueNo;
	}

	/**
	 * Get the distributor
	 * 
	 * @return  The distributor
	 */
	@DSpaceField("dc.publisher")
	public String getChrCommercialDistributor() {
		return chrCommercialDistributor;
	}

	/**
	 * Set the distributor
	 * 
	 * @param chrCommercialDistributor The distributor
	 */
	public void setChrCommercialDistributor(String chrCommercialDistributor) {
		this.chrCommercialDistributor = chrCommercialDistributor;
	}

	/**
	 * Get the format
	 * 
	 * @return The format
	 */
	@DSpaceField("dc.format.medium")
	public String getChrFormatTypeOfAV() {
		return chrFormatTypeOfAV;
	}

	/**
	 * Set the format
	 * 
	 * @param chrFormatTypeOfAV The format
	 */
	public void setChrFormatTypeOfAV(String chrFormatTypeOfAV) {
		this.chrFormatTypeOfAV = chrFormatTypeOfAV;
	}

	@Override
	public String getChrReportingYear() {
		return super.getChrReportingYear();
	}
	
	/**
	 * Get the title of the series it belongs to
	 * 
	 * @return The title of the series
	 */
	@DSpaceField("dc.relation.ispartofseries")
	@Column(name="chrSeriesTitle",columnDefinition="text")
	public String getChrSeriesTitle() {
		return chrSeriesTitle;
	}

	/**
	 * SEt the series of the title
	 * 
	 * @param chrSeriesTitle  The series title
	 */
	public void setChrSeriesTitle(String chrSeriesTitle) {
		this.chrSeriesTitle = chrSeriesTitle;
	}

	/**
	 * Get the issued date
	 * 
	 * @return The issued date
	 */
	@DSpaceField("dc.date.issued")
	public Date getDateOfRecording() {
		return dateOfRecording;
	}

	/**
	 * Set the issued date
	 * 
	 * @param dateOfRecording The issued date
	 */
	public void setDateOfRecording(Date dateOfRecording) {
		this.dateOfRecording = dateOfRecording;
	}
}
