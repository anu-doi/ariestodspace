package au.edu.anu.ariestodspace.staging.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="duplicate_records")
public class DuplicateRecords {
	private Long id;
	private Long duplicateId;
	private Integer sequenceNumber;
	private String ariesIdentifier;
	
	@Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="duplicate_id")
	public Long getDuplicateId() {
		return duplicateId;
	}
	
	public void setDuplicateId(Long duplicateId) {
		this.duplicateId = duplicateId;
	}
	
	@Column(name="sequence_number")
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@Column(name="aries_identifier")
	public String getAriesIdentifier() {
		return ariesIdentifier;
	}

	public void setAriesIdentifier(String ariesIdentifier) {
		this.ariesIdentifier = ariesIdentifier;
	}
}
