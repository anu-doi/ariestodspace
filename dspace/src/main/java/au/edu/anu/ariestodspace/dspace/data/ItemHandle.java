package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Entity for item handles
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("2")
public class ItemHandle extends Handle {
	Item item;

	/**
	 * Get the item
	 * 
	 * @return The itme
	 */
	@OneToOne
	@JoinColumn(referencedColumnName="item_id",name="resource_id",foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	public Item getItem() {
		return item;
	}

	/**
	 * SEt the item
	 * 
	 * @param item THe item
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
}
