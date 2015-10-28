package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class for the 'metadatavalue' table in the DSpace database
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="metadatavalue")
public class MetadataValue {
	private Integer id;
	private Item item;
	private Integer metadataFieldId;
	private String textValue;
	private String textLang;
	private Integer place;
	private String authority;
	private Integer confidence;
	
	/**
	 * Constructor
	 */
	public MetadataValue() {
		
	}

	/**
	 * Get the metadata value id
	 * 
	 * @return The id
	 */
	@Id
	@Column(name="metadata_value_id")
	public Integer getId() {
		return id;
	}

	/**
	 * Set the metadata value id
	 * 
	 * @param id The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the item
	 * 
	 * @return  The item
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="item_id", nullable = false)
	public Item getItem() {
		return item;
	}

	/**
	 * Set the item
	 * 
	 * @param item The item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Get the metadata field id
	 * 
	 * @return The metadata field id
	 */
	@Column(name="metadata_field_id")
	public Integer getMetadataFieldId() {
		return metadataFieldId;
	}

	/**
	 * SEt the metadata field id
	 * 
	 * @param metadataFieldId The metadata field id
	 */
	public void setMetadataFieldId(Integer metadataFieldId) {
		this.metadataFieldId = metadataFieldId;
	}

	/**
	 * Get the text value
	 * 
	 * @return The value
	 */
	@Column(name="text_value",length=4000)
	public String getTextValue() {
		return textValue;
	}

	/**
	 * SEt the text value
	 * 
	 * @param textValue The value
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	/**
	 * Get the language
	 * 
	 * @return The language
	 */
	@Column(name="text_lang")
	public String getTextLang() {
		return textLang;
	}

	/**
	 * SEt hte language
	 * 
	 * @param textLang The language
	 */
	public void setTextLang(String textLang) {
		this.textLang = textLang;
	}

	/**
	 * Get the order position for the given metadata field id
	 * 
	 * @return The order position
	 */
	@Column(name="place")
	public Integer getPlace() {
		return place;
	}

	/**
	 * Set the order position for the given metadata field id
	 * 
	 * @param place The order position
	 */
	public void setPlace(Integer place) {
		this.place = place;
	}

	/**
	 * Get the authority key value
	 * 
	 * @return The authority
	 */
	@Column(name="authority")
	public String getAuthority() {
		return authority;
	}

	/**
	 * Set the authority key value
	 * 
	 * @param authority The authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * Get the confidence that the authority key is correct
	 * 
	 * @return The confidence
	 */
	@Column(name="confidence")
	public Integer getConfidence() {
		return confidence;
	}

	/**
	 * Set the confidence that the authority key is correct
	 * 
	 * @param confidence
	 */
	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}
}
