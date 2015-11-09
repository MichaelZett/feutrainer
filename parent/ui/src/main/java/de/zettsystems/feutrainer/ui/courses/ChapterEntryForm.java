package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class ChapterEntryForm extends AbstractBaseForm<Chapter> {

	private ComboBox superChapter;
	private ComboBox courseUnit;

	public ChapterEntryForm(Chapter entry, ChapterRepository chapterRepo, CourseUnitRepository courseUnitRepo) {
		super(entry, chapterRepo, courseUnitRepo);
	}

	@Override
	protected void initializeAdditionalComponents(Chapter entry, BaseRepository<?>... repository) {
		ChapterRepository chapterRepository = (ChapterRepository) repository[0];
		CourseUnitRepository courseUnitRepository = (CourseUnitRepository) repository[1];
		this.courseUnit = new AutocompleteCourseUnitComboBox(courseUnitRepository);
		this.superChapter = new AutocompleteChapterComboBox(chapterRepository, this.courseUnit);

	}

	@Override
	protected String getWidthForNameTextField() {
		return "500px";
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.courseUnit, this.superChapter };
	}

}
