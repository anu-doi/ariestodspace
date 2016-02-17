package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("2")
public class ItemMetadataValue extends MetadataValue {
	private Item item;

	/**
	 * Get the item
	 * 
	 * @return  The item
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="resource_id", referencedColumnName="item_id", nullable = false
			, insertable=false, updatable=false)
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

}
