package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private String name;
	private CollectionHandle handle;
	
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
	 * Get the collection name
	 * 
	 * @return THe name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the collection name
	 * 
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
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
}
