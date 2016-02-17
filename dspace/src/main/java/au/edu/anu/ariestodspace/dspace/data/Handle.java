package au.edu.anu.ariestodspace.dspace.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

/**
 * Entity class for the 'handle' table in the DSpace database.
 * 
 * @author Genevieve Turner
 *
 */
@Entity
@Table(name="handle")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type_id",discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorOptions(force=true)
public class Handle {
	private Integer id;
	private String handle;

	/**
	 * Get the id
	 * 
	 * @return The id
	 */
	@Id
	@Column(name="handle_id")
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id
	 * 
	 * @param id The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the handle
	 * 
	 * @return The handle
	 */
	@Column(name="handle")
	public String getHandle() {
		return handle;
	}

	/**
	 * SEt the handle
	 * 
	 * @param handle The handle
	 */
	public void setHandle(String handle) {
		this.handle = handle;
	}
}
