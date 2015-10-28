package au.edu.anu.ariestodspace.dspace.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class for the 'item' table in the DSpace database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="item")
@DiscriminatorValue("2")
public class Item {
	private Integer itemId;
	private Integer submitterId;
	private Boolean inArchive;
	private Boolean withdrawn;
	private Date lastModified;
	private Collection owningCollection;
	private List<ItemHandle> handles;
	private List<MetadataValue> metadataValues = new ArrayList<MetadataValue>();
	
	/**
	 * Get the item id
	 * 
	 * @return The item id
	 */
	@Id
	@Column(name="item_id")
	public Integer getItemId() {
		return itemId;
	}
	
	/**
	 * SEt the item id
	 * 
	 * @param itemId The item id
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Get the submitter id
	 * 
	 * @return The submitter id
	 */
	@Column(name="submitter_id")
	public Integer getSubmitterId() {
		return submitterId;
	}
	
	/**
	 * Set the submitter id
	 * 
	 * @param submitterId The submitter id
	 */
	public void setSubmitterId(Integer submitterId) {
		this.submitterId = submitterId;
	}
	
	/**
	 * Get whether the item is in the archive or not
	 * 
	 * @return The archive status
	 */
	@Column(name="in_archive")
	public Boolean getInArchive() {
		return inArchive;
	}
	
	/**
	 * Set whether the item is in the archive or not
	 * 
	 * @param inArchive The archive status
	 */
	public void setInArchive(Boolean inArchive) {
		this.inArchive = inArchive;
	}
	
	/**
	 * Get withdrawn status
	 * 
	 * @return The withdrawn status
	 */
	@Column(name="withdrawn")
	public Boolean getWithdrawn() {
		return withdrawn;
	}
	
	/**
	 * Set the withdrawn status
	 * 
	 * @param withdrawn The withdrawn status
	 */
	public void setWithdrawn(Boolean withdrawn) {
		this.withdrawn = withdrawn;
	}
	
	/**
	 * Get the date last modified
	 * 
	 * @return THe last modified date
	 */
	@Column(name="last_modified")
	public Date getLastModified() {
		return lastModified;
	}
	
	/**
	 * Set the last modified date
	 * 
	 * @param lastModified THe last modified date
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Get the owning collection
	 * 
	 * @return The owning collection
	 */
	@ManyToOne
	@JoinColumn(name="owning_collection", referencedColumnName="collection_id")
	public Collection getOwningCollection() {
		return owningCollection;
	}

	/**
	 * SEt the owning collection
	 * 
	 * @param owningCollection The owning collection
	 */
	public void setOwningCollection(Collection owningCollection) {
		this.owningCollection = owningCollection;
	}

	/**
	 * Get the handles
	 * 
	 * @return The handles
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="item")
	public List<ItemHandle> getHandles() {
		return handles;
	}

	/**
	 * Set the handles
	 * 
	 * @param handles The handles
	 */
	public void setHandles(List<ItemHandle> handles) {
		this.handles = handles;
	}

	/**
	 * Get the metadata values
	 * 
	 * @return The metadata values
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	public List<MetadataValue> getMetadataValues() {
		return metadataValues;
	}

	/**
	 * Set the metadata values
	 * 
	 * @param metadataValues The metadata values
	 */
	public void setMetadataValues(List<MetadataValue> metadataValues) {
		this.metadataValues = metadataValues;
	}
}
