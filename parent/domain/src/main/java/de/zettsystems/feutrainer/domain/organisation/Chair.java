package de.zettsystems.feutrainer.domain.organisation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for chair.
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
public class Chair extends AbstractBaseEntity {

	@ManyToOne(optional = false)
	@NotNull
	private Department department;

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return this.department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department
	 *            the new department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}
