package de.zettsystems.feutrainer.domain.courses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * Entity for chapters.
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
public class Chapter extends AbstractBaseEntity {

	@ManyToOne(optional = false)
	@NotNull
	private CourseUnit courseUnit;

	@ManyToOne
	private Chapter superChapter;

	/**
	 * Gets the course unit.
	 *
	 * @return the course unit
	 */
	public CourseUnit getCourseUnit() {
		return this.courseUnit;
	}

	/**
	 * Sets the course unit.
	 *
	 * @param courseUnit
	 *            the new course unit
	 */
	public void setCourseUnit(CourseUnit courseUnit) {
		this.courseUnit = courseUnit;
	}

	/**
	 * Gets the super chapter.
	 *
	 * @return the super chapter
	 */
	public Chapter getSuperChapter() {
		return this.superChapter;
	}

	/**
	 * Sets the super chapter.
	 *
	 * @param superChapter
	 *            the new super chapter
	 */
	public void setSuperChapter(Chapter superChapter) {
		this.superChapter = superChapter;
	}

}
