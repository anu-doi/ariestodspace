package au.edu.anu.ariestodspace.staging.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="last_run")
public class LastRun {
	private String runType;
	private Date runDate;
	
	@Id
	@Column(name="run_type")
	public String getRunType() {
		return runType;
	}
	
	public void setRunType(String runType) {
		this.runType = runType;
	}
	
	@Column(name="run_date")
	public Date getRunDate() {
		return runDate;
	}
	
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
}
