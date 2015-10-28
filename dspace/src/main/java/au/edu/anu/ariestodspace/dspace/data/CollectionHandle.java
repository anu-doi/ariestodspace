package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Entity for collection handles.
 * @author Genevieve Turner
 *
 */
@Entity
@DiscriminatorValue("3")
public class CollectionHandle extends Handle {
	Collection collection;

	/**
	 * Get the collection
	 * 
	 * @return THe collection
	 */
	@OneToOne
	@JoinColumn(referencedColumnName="collection_id",name="resource_id",foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	public Collection getCollection() {
		return collection;
	}

	/**
	 * SEt teh collection
	 * 
	 * @param collection The collection
	 */
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
}
