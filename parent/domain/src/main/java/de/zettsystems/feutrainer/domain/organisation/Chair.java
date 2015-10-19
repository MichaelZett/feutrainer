package de.zettsystems.feutrainer.domain.organisation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.feutrainer.domain.courses.Course;

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

	@ManyToMany(mappedBy = "chairs")
	private Set<Course> courses = new HashSet<>();

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

	/**
	 * Gets the courses.
	 *
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return this.courses;
	}

	/**
	 * Adds the course.
	 *
	 * @param course
	 *            the course
	 */
	public void addCourse(Course course) {
		this.courses.add(course);
	}
}
