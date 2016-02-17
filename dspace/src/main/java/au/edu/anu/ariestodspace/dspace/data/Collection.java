package au.edu.anu.ariestodspace.dspace.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class for the 'collection' table in the DSpace table.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="collection")
public class Collection {
	private Integer collectionId;
	private CollectionHandle handle;
	private List<CollectionMetadataValue> metadataValues = new ArrayList<CollectionMetadataValue>();
	
	/**
	 * Get the collection id
	 * 
	 * @return The id
	 */
	@Id
	@Column(name="collection_id")
	public Integer getCollectionId() {
		return collectionId;
	}
	
	/**
	 * Set the collection id
	 * 
	 * @param collectionId The id
	 */
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	/**
	 * Get the handle
	 * 
	 * @return The handle
	 */
	@OneToOne(mappedBy="collection")
	public CollectionHandle getHandle() {
		return handle;
	}

	/**
	 * SEt the handle
	 * 
	 * @param handle The handle
	 */
	public void setHandle(CollectionHandle handle) {
		this.handle = handle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "collection")
	public List<CollectionMetadataValue> getMetadataValues() {
		return metadataValues;
	}

	public void setMetadataValues(List<CollectionMetadataValue> metadataValues) {
		this.metadataValues = metadataValues;
	}
}
