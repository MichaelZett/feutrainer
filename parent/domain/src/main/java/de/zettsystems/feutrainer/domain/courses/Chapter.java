package de.zettsystems.feutrainer.domain.courses;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.feutrainer.domain.test.Question;

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

	@OneToMany(mappedBy = "superChapter")
	@OrderBy("id")
	private SortedSet<Chapter> subChapters = new TreeSet<>();

	@OneToMany(mappedBy = "chapter")
	private Set<Question> questions = new HashSet<>();

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

	/**
	 * Gets the sub chapters.
	 *
	 * @return the sub chapters
	 */
	public Set<Chapter> getSubChapters() {
		return this.subChapters;
	}

	/**
	 * Adds the sub chapters.
	 *
	 * @param subChapter
	 *            the sub chapter
	 */
	public void addSubChapters(Chapter subChapter) {
		this.subChapters.add(subChapter);
	}

	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public Set<Question> getQuestions() {
		return this.questions;
	}

	/**
	 * Adds the question.
	 *
	 * @param question
	 *            the question
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

}
