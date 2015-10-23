package de.zettsystems.feutrainer.domain.organisation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for departments like WiWi.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Entity
public class Department extends AbstractBaseEntity {

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@NotNull
	private Institute institute;

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

}
