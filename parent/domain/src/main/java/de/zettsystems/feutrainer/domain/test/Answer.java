package de.zettsystems.feutrainer.domain.test;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

/**
 * The Class Answer.
 */
@Entity
public class Answer extends AbstractBaseEntity {
	private String additionalText;
	private String imageFileName;
	@ManyToOne(optional = false)
	@NotNull
	private Question question;
	@NotNull
	private boolean correct;

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
	 * Gets the question.
	 *
	 * @return the question
	 */
	public Question getQuestion() {
		return this.question;
	}

	/**
	 * Sets the question.
	 *
	 * @param question
	 *            the new question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Checks if is correct.
	 *
	 * @return true, if is correct
	 */
	public boolean isCorrect() {
		return this.correct;
	}

	/**
	 * Sets the correct.
	 *
	 * @param correct
	 *            the new correct
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
