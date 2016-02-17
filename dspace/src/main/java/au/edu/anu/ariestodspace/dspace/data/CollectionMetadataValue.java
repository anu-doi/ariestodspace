package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("3")
public class CollectionMetadataValue extends MetadataValue {
	private Collection collection;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="resource_id", referencedColumnName="collection_id"
		, nullable = false, insertable=false, updatable=false)
	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}
}
