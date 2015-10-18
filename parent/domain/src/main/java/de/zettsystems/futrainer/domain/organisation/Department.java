package de.zettsystems.futrainer.domain.organisation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import de.zettsystems.futrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for departments like WiWi.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Entity
public class Department extends AbstractBaseEntity {

	@ManyToOne
	@NotNull
	private Institute institute;

	@OneToMany
	private Set<Chair> chairs = new HashSet<>();

	/**
	 * Gets the institute.
	 *
	 * @return the institute
	 */
	public Institute getInstitute() {
		return this.institute;
	}

	/**
	 * Sets the institute.
	 *
	 * @param institute
	 *            the institute to set
	 */
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	/**
	 * Gets the chairs.
	 *
	 * @return the chairs
	 */
	public Set<Chair> getChairs() {
		return this.chairs;
	}

	/**
	 * Adds the chair.
	 *
	 * @param chair
	 *            the chair
	 */
	public void addChair(Chair chair) {
		this.chairs.add(chair);
	}

}
