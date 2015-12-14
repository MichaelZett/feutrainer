package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractFilterForm;

public class ChapterFilterForm extends AbstractFilterForm<Chapter> {
	private ComboBox courseUnitSelect;
	private ChapterTable chapterTable;

	public ChapterFilterForm(ChapterTable chapterTable, CourseUnitRepository courseUnitRepository) {
		super(courseUnitRepository);
		this.chapterTable = chapterTable;
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.courseUnitSelect };
	}

	@Override
	protected AbstractBaseTable<Chapter> getTable() {
		return this.chapterTable;
	}

	@Override
	protected void initializeAdditionalComponents(BaseRepository<?>... repositories) {
		CourseUnitRepository courseUnitRepository = (CourseUnitRepository) repositories[0];
		this.courseUnitSelect = new AutocompleteCourseUnitComboBox(courseUnitRepository);
		this.courseUnitSelect.setCaption("Course Unit");
		this.courseUnitSelect
				.addValueChangeListener(event -> setTableCourseUnit((CourseUnit) this.courseUnitSelect.getValue()));
	}

	private void setTableCourseUnit(CourseUnit value) {
		this.chapterTable.setCourseUnitFilter(value);
	}

	@Override
	protected void resetAdditionalFilters() {
		setTableCourseUnit(null);
	}

}
