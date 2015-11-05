package de.zettsystems.feutrainer.ui.courses;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class ChapterEntryForm extends AbstractBaseForm<Chapter> {

	private TypedSelect<Chapter> superChapter;
	private TypedSelect<CourseUnit> courseUnit;

	public ChapterEntryForm(Chapter entry, ChapterRepository chapterRepo, CourseUnitRepository courseUnitRepo) {
		super(entry, chapterRepo, courseUnitRepo);
	}

	@Override
	protected void initializeAdditionalComponents(Chapter entry, BaseRepository<?>... repository) {
		ChapterRepository chapterRepository = (ChapterRepository) repository[0];
		initializeChapterSelect(entry, chapterRepository);
		CourseUnitRepository courseUnitRepository = (CourseUnitRepository) repository[1];
		initializeCourseUnitSelect(entry, courseUnitRepository, chapterRepository);
	}

	@Override
	protected String getWidthForNameTextField() {
		return "500px";
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.courseUnit, this.superChapter };
	}

	private void initializeChapterSelect(Chapter entry, ChapterRepository chapterRepository) {
		this.superChapter = new TypedSelect<>(Chapter.class);
		this.superChapter.setCaption("Super Chapter");
		this.superChapter.setOptions(chapterRepository.findAll());
		this.superChapter.addMValueChangeListener(event -> {
			entry.setSuperChapter(event.getValue());
		});
	}

	private void initializeCourseUnitSelect(Chapter entry, CourseUnitRepository courseUnitRepository,
			ChapterRepository chapterRepository) {
		this.courseUnit = new TypedSelect<>(CourseUnit.class);
		this.courseUnit.setCaption("CourseUnit");
		this.courseUnit.setOptions(courseUnitRepository.findAll());
		this.courseUnit.addMValueChangeListener(event -> {
			entry.setCourseUnit(event.getValue());
			this.superChapter.setOptions(chapterRepository.findAllByCourseUnit(entry.getCourseUnit()));
		});
	}

}
