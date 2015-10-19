package de.zettsystems.feutrainer.tools.toc;

/**
 * The Class ChapterData.
 */
public class ChapterData {

	private String chapter;
	private String courseUnitId;
	private String chapterName;
	private String superChapterId;

	/**
	 * Instantiates a new chapter data.
	 *
	 * @param courseUnitId
	 *            the course unit id
	 * @param chapter
	 *            the chapter
	 * @param name
	 *            the name
	 */
	public ChapterData(String courseUnitId, String chapter, String name) {
		this.courseUnitId = courseUnitId;
		this.chapter = chapter;
		this.chapterName = name;
	}

	/**
	 * Gets the chapter.
	 *
	 * @return the chapter
	 */
	public String getChapter() {
		return this.chapter;
	}

	/**
	 * Gets the chapter id.
	 *
	 * @return the chapter id
	 */
	public String getChapterId() {
		return this.courseUnitId + "_" + this.chapter;
	}

	/**
	 * Gets the course unit id.
	 *
	 * @return the course unit id
	 */
	public String getCourseUnitId() {
		return this.courseUnitId;
	}

	/**
	 * Gets the chapter name.
	 *
	 * @return the chapter name
	 */
	public String getChapterName() {
		return this.chapterName;
	}

	/**
	 * Adds the missing text to name.
	 *
	 * @param extractName
	 *            the extract name
	 */
	public void addMissingTextToName(String extractName) {
		this.chapterName = this.chapterName + " " + extractName;
	}

	/**
	 * Sets the super chapter id.
	 *
	 * @param superChapterId
	 *            the new super chapter id
	 */
	public void setSuperChapterId(String superChapterId) {
		this.superChapterId = superChapterId;
	}

	/**
	 * Gets the super chapter id.
	 *
	 * @return the super chapter id
	 */
	public String getSuperChapterId() {
		return this.superChapterId;
	}

}
