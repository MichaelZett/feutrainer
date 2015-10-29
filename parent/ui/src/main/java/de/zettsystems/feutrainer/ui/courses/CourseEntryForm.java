package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class CourseEntryForm extends AbstractBaseForm<Course> {

	public CourseEntryForm(Course entry, ChairRepository repo) {
		super(entry, repo);
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[0];
	}

}
