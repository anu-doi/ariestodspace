package au.edu.anu.ariestodspace.staging.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for the 'identifier_mapping' table.  Indicates that the DSpace item and the ARIES research output are the same output
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="identifier_mapping")
public class IdentifierMapping {
	private Long id;
	private String ariesIdentifier;
	private Integer itemId;
	private String handle;
	private String collectionHandle;
	
	/**
	 * Constructor
	 */
	public IdentifierMapping() {
	}
	
	public IdentifierMapping(String ariesIdentifier, Integer itemId) {
		this.ariesIdentifier = ariesIdentifier;
		this.itemId = itemId;
	}
	
	/**
	 * Constructor
	 * 
	 * @param ariesIdentifier ARIES Identifier
	 * @param itemId DSpace Item Id
	 * @param handle DSpace Handle
	 * @param collectionHandle DSpace item owning collection handle
	 */
	public IdentifierMapping(String ariesIdentifier, Integer itemId, String handle, String collectionHandle) {
		this.ariesIdentifier = ariesIdentifier;
		this.itemId = itemId;
		this.handle = handle;
		this.collectionHandle = collectionHandle;
	}
	
	/**
	 * Get the identifier
	 * 
	 * @return The identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * Set the identifier
	 * 
	 * @param id The identifier
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the ARIES identifier
	 * 
	 * @return The ARIES identifier
	 */
	@Column(name="aries_identifier")
	public String getAriesIdentifier() {
		return ariesIdentifier;
	}
	
	/**
	 * Set the ARIES identifier
	 * 
	 * @param ariesIdentifier THe ARIES identifier
	 */
	public void setAriesIdentifier(String ariesIdentifier) {
		this.ariesIdentifier = ariesIdentifier;
	}
	
	/**
	 * Get the DSpace item identifier
	 * 
	 * @return The item identifier
	 */
	@Column(name="item_id")
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * SEt the DSPace item identifier
	 * 
	 * @param itemId The item identifier
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * Get the DSpace item's handle
	 * 
	 * @return The handle
	 */
	public String getHandle() {
		return handle;
	}
	
	/**
	 * Set the DSpace item's handle
	 * 
	 * @param handle The handle
	 */
	public void setHandle(String handle) {
		this.handle = handle;
	}

	/**
	 * Get the DSpace item's owning collection handle
	 * 
	 * @return The collection handle
	 */
	@Column(name="collection_handle")
	public String getCollectionHandle() {
		return collectionHandle;
	}

	/**
	 * Set the DSpace items owning collection handle
	 * 
	 * @param collectionHandle The collection handle
	 */
	public void setCollectionHandle(String collectionHandle) {
		this.collectionHandle = collectionHandle;
	}
}
