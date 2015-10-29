package de.zettsystems.feutrainer.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Basic attributes for all entities.
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {
	@Id
	@GeneratedValue
	private long entityKey;

	@Version
	private long optimisticConcurrency;

	@NotBlank
	@Column(unique = true)
	private String id;

	@NotBlank
	private String name;

	/**
	 * Instantiates a new abstract base entity.
	 */
	public AbstractBaseEntity() {
		super();
	}

	/**
	 * Instantiates a new abstract base entity.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public AbstractBaseEntity(String id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the entit key.
	 *
	 * @return the entit key
	 */
	public long getEntityKey() {
		return this.entityKey;
	}

	/**
	 * Gets the optimistic concurrency.
	 *
	 * @return the optimistic concurrency
	 */
	public long getOptimisticConcurrency() {
		return this.optimisticConcurrency;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.entityKey ^ (this.entityKey >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractBaseEntity other = (AbstractBaseEntity) obj;
		if (this.entityKey != other.entityKey) {
			return false;
		}
		return true;
	}

	public String toPresentation() {
		return this.id;
	}

}
