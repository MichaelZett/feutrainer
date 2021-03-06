package de.zettsystems.feutrainer.domain.courses;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.feutrainer.domain.organisation.Chair;

/**
 * Entity for course. TODO modul!
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
@NamedEntityGraph(name = "graph.CourseUnit.id", attributeNodes = { @NamedAttributeNode(value = "chairs"),
		@NamedAttributeNode(value = "courseUnits", subgraph = "id") }, subgraphs = @NamedSubgraph(name = "id", attributeNodes = @NamedAttributeNode("id") ) )
public class Course extends AbstractBaseEntity {

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Chair> chairs = new HashSet<>();

	/** The units. */
	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	@OrderBy("id")
	private SortedSet<CourseUnit> courseUnits = new TreeSet<>();

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

	/**
	 * Gets the units.
	 *
	 * @return the units
	 */
	public SortedSet<CourseUnit> getCourseUnits() {
		return this.courseUnits;
	}

	/**
	 * Adds the unit.
	 *
	 * @param unit
	 *            the unit
	 */
	public void addCourseUnit(CourseUnit unit) {
		this.courseUnits.add(unit);
	}
}
