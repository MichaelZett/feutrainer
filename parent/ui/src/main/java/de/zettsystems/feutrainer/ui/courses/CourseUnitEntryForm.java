package de.zettsystems.feutrainer.ui.courses;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.courses.CourseRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.organisation.Chair;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class CourseUnitEntryForm extends AbstractBaseForm<CourseUnit> {

	private TypedSelect<Chair> chair;
	private TypedSelect<Course> course;

	public CourseUnitEntryForm(CourseUnit entry, ChairRepository chairRepository, CourseRepository courseRepository) {
		super(entry, chairRepository, courseRepository);
	}

	@Override
	protected String getWidthForNameTextField() {
		return "500px";
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.course, this.chair };
	}

	@Override
	protected void initializeAdditionalComponents(CourseUnit entry, BaseRepository<?>... repository) {
		ChairRepository chairRepository = (ChairRepository) repository[0];
		initializeChairSelect(entry, chairRepository);
		CourseRepository courseRepository = (CourseRepository) repository[1];
		initializeCourseSelect(entry, courseRepository, chairRepository);
	}

	private void initializeCourseSelect(CourseUnit entry, CourseRepository courseRepository,
			ChairRepository chairRepository) {
		this.course = new TypedSelect<>(Course.class);
		this.course.setCaption("Course");
		this.course.setOptions(courseRepository.findAll());
		this.course.addMValueChangeListener(event -> {
			entry.setCourse(event.getValue());
			this.chair.setOptions(chairRepository.findAllChairsForCourse(entry.getCourse()));
		});
	}

	private void initializeChairSelect(CourseUnit entry, ChairRepository chairRepository) {
		this.chair = new TypedSelect<>(Chair.class);
		this.chair.setCaption("Chair");
		this.chair.setOptions(chairRepository.findAllChairsForCourse(entry.getCourse()));
		this.chair.addMValueChangeListener(event -> {
			entry.setChair(event.getValue());
		});
	}

}
