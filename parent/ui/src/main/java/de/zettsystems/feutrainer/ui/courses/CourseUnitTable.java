package de.zettsystems.feutrainer.ui.courses;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class CourseUnitTable extends AbstractBaseTable<CourseUnit> {
	@Autowired
	private CourseUnitRepository courseUnitRepository;

	public CourseUnitTable() {
		super(CourseUnit.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "chair", "course" };
	}

	@Override
	protected BaseRepository<CourseUnit> getRepository() {
		return this.courseUnitRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "chair", "course" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Chair", "Course" };
	}

}
