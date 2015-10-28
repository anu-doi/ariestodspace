package au.edu.anu.ariestodspace.aries.outputs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

/**
 * Entity class for the software research outputs.  Relates to the RO6 output type.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("06XXX")
public class Software extends ResearchOutputsData1 {
	private String chrCatalogueNo;
	private String chrCommercialDistributor;
	private String chrDistributionMedium;
	private String chrEdition;
	private static final List<String> EXT_CATEGORIES = Collections.unmodifiableList(
			new ArrayList<String>()
			{
				private static final long serialVersionUID = 1L;

			{
					add("RL104");
			}}
		);
	
	/**
	 * Get the type
	 * 
	 * @return The type
	 */
	@Transient
	@DSpaceField("dc.type")
	public String getType() {
		return "Computer program";
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
	 * SEt the catalogue number
	 * 
	 * @param chrCatalogueNo The catalogue number
	 */
	public void setChrCatalogueNo(String chrCatalogueNo) {
		this.chrCatalogueNo = chrCatalogueNo;
	}

	/**
	 * Get the distributor
	 * 
	 * @return The publisher
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
	 * Get the distribution medium
	 * 
	 * @return  The distribution medium
	 */
	public String getChrDistributionMedium() {
		return chrDistributionMedium;
	}

	/**
	 * Set the distribution medium
	 * 
	 * @param chrDistributionMedium The distribution medium
	 */
	public void setChrDistributionMedium(String chrDistributionMedium) {
		this.chrDistributionMedium = chrDistributionMedium;
	}
	
	/**
	 * Get the medium of the software
	 * 
	 * @return The software medium
	 */
	@Transient
	@DSpaceField("dc.format.medium")
	public String getFormatMedium() {
		if (chrDistributionMedium != null && chrDistributionMedium.trim().length() > 0) {
			if (chrDistributionMedium.startsWith("http")) {
				return "Web download";
			}
			else {
				return chrDistributionMedium;
			}
		}
		return null;
	}
	
	@Transient
	@DSpaceField("dc.source.uri")
	public String getMediumSource() {
		if (chrDistributionMedium != null && chrDistributionMedium.startsWith("http://")) {
			return chrDistributionMedium;
		}
		
		return null;
	}

	/**
	 * Get the software version
	 * 
	 * @return The software version
	 */
	@DSpaceField("dc.relation.isversionof")
	public String getChrEdition() {
		return chrEdition;
	}

	/**
	 * Set the software version
	 * 
	 * @param chrEdition The software version
	 */
	public void setChrEdition(String chrEdition) {
		this.chrEdition = chrEdition;
	}
	
	@Transient
	@Override
	public List<String> getExternalCategories() {
		return EXT_CATEGORIES;
//		List<String> categories = new ArrayList<String>();
//		categories.add("RL104");
//		return categories;
	}
}
