package de.zettsystems.feutrainer.domain.organisation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for university and such.
 *
 * @author michael_zoeller
 * @created 26.08.2013
 */
@Entity
public class Institute extends AbstractBaseEntity {

	@OneToMany(mappedBy = "institute")
	private Set<Department> departments = new HashSet<>();

	/**
	 * Gets the departments.
	 *
	 * @return the departments
	 */
	public Set<Department> getDepartments() {
		return this.departments;
	}

	/**
	 * Adds the department.
	 *
	 * @param department
	 *            the department
	 */
	public void addDepartment(Department department) {
		this.departments.add(department);
	}

}
