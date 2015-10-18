package tools.ea;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class QuestionData.
 */
public class QuestionData {

	private int ke;
	private String eaId;
	private int aufgabe;
	private String questionText;
	private Map<String, String> answers = new HashMap<>();

	/**
	 * Instantiates a new question data.
	 *
	 * @param filename
	 *            the filename
	 * @param ke
	 *            the ke
	 * @param aufgabe
	 *            the aufgabe
	 */
	public QuestionData(String eaId, int ke, int aufgabe) {
		this.ke = ke;
		this.eaId = eaId;
		this.aufgabe = aufgabe;
	}

	/**
	 * Sets the question.
	 *
	 * @param questionText
	 *            the new question
	 */
	public void setQuestion(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * Adds the answer.
	 *
	 * @param key
	 *            the key
	 * @param text
	 *            the text
	 */
	public void addAnswer(String key, String text) {
		this.answers.put(key, text);
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getEaId() {
		return this.eaId;
	}

	/**
	 * Gets the question text.
	 *
	 * @return the question text
	 */
	public String getQuestionText() {
		return this.questionText;
	}

	/**
	 * Sets the question text.
	 *
	 * @param questionText
	 *            the new question text
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * Gets the ke.
	 *
	 * @return the ke
	 */
	public int getKe() {
		return this.ke;
	}

	/**
	 * Gets the aufgabe.
	 *
	 * @return the aufgabe
	 */
	public int getAufgabe() {
		return this.aufgabe;
	}

	/**
	 * Gets the answers.
	 *
	 * @return the answers
	 */
	public Map<String, String> getAnswers() {
		return this.answers;
	}

}
