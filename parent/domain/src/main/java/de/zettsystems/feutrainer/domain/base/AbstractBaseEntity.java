package de.zettsystems.feutrainer.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Basic attributes for all entities.
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {
	@Id
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sequence", allocationSize = 10)
	private long entityKey;

	@Version
	private long optimisticConcurrency;

	@NotNull
	@Column(unique = true)
	@Size(min = 1, max = 255)
	private String id;

	@NotNull
	@Size(min = 1, max = 255)
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
		return 31;
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
