package de.zettsystems.feutrainer.domain.courses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.feutrainer.domain.organisation.Chair;

/**
 * Entity for course units (Kurseinheit).
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
@NamedEntityGraph(name = "graph.Course.CourseUnit.id", attributeNodes = { @NamedAttributeNode(value = "chair"),
		@NamedAttributeNode(value = "course", subgraph = "graph.CourseUnit.id") })
public class CourseUnit extends AbstractBaseEntity implements Comparable<CourseUnit> {

	private static final String KE_IDENTIFIER = "KE";

	@ManyToOne(optional = false)
	@NotNull
	private Course course;

	@ManyToOne(optional = false)
	@NotNull
	private Chair chair;

	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return this.course;
	}

	/**
	 * Sets the course.
	 *
	 * @param course
	 *            the new course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Gets the chair.
	 *
	 * @return the chair
	 */
	public Chair getChair() {
		return this.chair;
	}

	/**
	 * Sets the chair.
	 *
	 * @param chair
	 *            the new chair
	 */
	public void setChair(Chair chair) {
		this.chair = chair;
	}

	@Override
	public int compareTo(CourseUnit o) {
		if (getId() == null || o.getId() == null) {
			return 0;
		}
		int ownId = convertIdToCourseUnitNumber(getId());
		int otherId = convertIdToCourseUnitNumber(o.getId());
		return Integer.compare(ownId, otherId);
	}

	private int convertIdToCourseUnitNumber(String id) {
		return Integer.parseInt(id.substring(id.indexOf(KE_IDENTIFIER) + KE_IDENTIFIER.length()));
	}

}
