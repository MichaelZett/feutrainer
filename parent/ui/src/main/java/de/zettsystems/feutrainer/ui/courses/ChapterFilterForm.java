package de.zettsystems.feutrainer.ui.courses;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractFilterForm;

public class ChapterFilterForm extends AbstractFilterForm<Chapter> {
	private TypedSelect<CourseUnit> courseUnit;
	private ChapterTable chapterTable;

	public ChapterFilterForm(ChapterTable chapterTable, CourseUnitRepository courseUnitRepository) {
		super(courseUnitRepository);
		this.chapterTable = chapterTable;
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.courseUnit };
	}

	@Override
	protected AbstractBaseTable<Chapter> getTable() {
		return this.chapterTable;
	}

	@Override
	protected void initializeAdditionalComponents(BaseRepository<?>... repositories) {
		CourseUnitRepository courseUnitRepository = (CourseUnitRepository) repositories[0];
		this.courseUnit = new TypedSelect<>(CourseUnit.class);
		this.courseUnit.setCaption("CourseUnit");
		this.courseUnit.setOptions(courseUnitRepository.findAll());
		this.courseUnit.addMValueChangeListener(event -> setTableCourseUnit(event.getValue()));
	}

	private void setTableCourseUnit(CourseUnit value) {
		this.chapterTable.setCourseUnitFilter(value);
	}

	@Override
	protected void resetAdditionalFilters() {
		setTableCourseUnit(null);
	}

}
