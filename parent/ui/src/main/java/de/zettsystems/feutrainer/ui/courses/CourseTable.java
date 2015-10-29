package de.zettsystems.feutrainer.ui.courses;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.courses.CourseRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class CourseTable extends AbstractBaseTable<Course> {
	@Autowired
	private CourseRepository courseRepository;

	public CourseTable() {
		super(Course.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "chairs", "courseUnits" };
	}

	@Override
	protected BaseRepository<Course> getRepository() {
		return this.courseRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[0];
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Chairs", "Course Units" };
	}

}
