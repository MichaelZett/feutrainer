package de.zettsystems.feutrainer.ui.courses;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.converter.StringToCollectionConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;
import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.courses.CourseRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.StringToAbstractBaseEntityConverter;
import de.zettsystems.feutrainer.ui.base.StringToCollectionCountConverter;

@SpringComponent
@ViewScope
public class CourseTable extends AbstractBaseTable<Course> {
	@Autowired
	private CourseRepository courseRepository;

	public CourseTable() {
		super(Course.class);
		setConverter("chairs",
				new StringToCollectionConverter(new StringToAbstractBaseEntityConverter(), AbstractBaseEntity.class));
		setConverter("courseUnits", new StringToCollectionCountConverter());
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
		return new String[] { "Chairs", "Course Units Count" };
	}

}
