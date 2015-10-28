package au.edu.anu.ariestodspace.aries.outputs.patent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import au.edu.anu.ariestodspace.aries.annotation.DSpaceField;

@Entity
@DiscriminatorValue("05130")
public class PlantBreedersRights extends Invention {
	private String chrPatentCountry;
	private String chrPatentNumber;
	
	@DSpaceField("dc.coverage.spatial")
	public String getChrPatentCountry() {
		return chrPatentCountry;
	}
	
	public void setChrPatentCountry(String chrPatentCountry) {
		this.chrPatentCountry = chrPatentCountry;
	}
	
	@DSpaceField("dc.identifier")
	public String getChrPatentNumber() {
		return chrPatentNumber;
	}
	
	public void setChrPatentNumber(String chrPatentNumber) {
		this.chrPatentNumber = chrPatentNumber;
	}
}
