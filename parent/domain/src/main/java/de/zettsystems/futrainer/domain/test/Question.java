package de.zettsystems.futrainer.domain.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import de.zettsystems.futrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.futrainer.domain.courses.Chapter;

/**
 * The Class Question.
 *
 * @author michael_zoeller
 * @created 08.10.2015
 */
@Entity
public class Question extends AbstractBaseEntity {
	private String additionalText;
	private String imageFileName;
	@ManyToOne
	@NotNull
	private Chapter chapter;
	@OneToMany
	private Set<Answer> answers = new HashSet<>();

	/**
	 * Gets the additional text.
	 *
	 * @return the additional text
	 */
	public String getAdditionalText() {
		return this.additionalText;
	}

	/**
	 * Sets the additional text.
	 *
	 * @param additionalText
	 *            the new additional text
	 */
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}

	/**
	 * Gets the image file name.
	 *
	 * @return the image file name
	 */
	public String getImageFileName() {
		return this.imageFileName;
	}

	/**
	 * Sets the image file name.
	 *
	 * @param imageFileName
	 *            the new image file name
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * Gets the chapter.
	 *
	 * @return the chapter
	 */
	public Chapter getChapter() {
		return this.chapter;
	}

	/**
	 * Sets the chapter.
	 *
	 * @param chapter
	 *            the new chapter
	 */
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	/**
	 * Gets the answers.
	 *
	 * @return the answers
	 */
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	/**
	 * Adds the answer.
	 *
	 * @param answer
	 *            the answer
	 */
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

}