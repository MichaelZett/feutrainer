package de.zettsystems.futrainer.domain.courses;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import de.zettsystems.futrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.futrainer.domain.organisation.Chair;

/**
 * Entity for course units (Kurseinheit).
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
public class CourseUnit extends AbstractBaseEntity {

	@ManyToOne(optional = false)
	@NotNull
	private Course course;

	@ManyToOne(optional = false)
	@NotNull
	private Chair chair;

	@OneToMany(mappedBy = "courseUnit")
	@OrderBy("id")
	private SortedSet<Chapter> chapters = new TreeSet<>();

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
	 * Gets the chapters.
	 *
	 * @return the chapters
	 */
	public SortedSet<Chapter> getChapters() {
		return this.chapters;
	}

	/**
	 * Adds the chapter.
	 *
	 * @param chapter
	 *            the chapter
	 */
	public void addChapter(Chapter chapter) {
		this.chapters.add(chapter);
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

}
