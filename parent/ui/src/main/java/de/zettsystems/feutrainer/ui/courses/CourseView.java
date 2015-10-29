package de.zettsystems.feutrainer.ui.courses;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.courses.CourseRepository;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class ChairView.
 */
@SpringView(name = CourseView.VIEW_NAME)
public class CourseView extends AbstractBaseView<Course> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "course";

	@Autowired
	private CourseTable courseTable;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ChairRepository chairRepository;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Course> getTable() {
		return this.courseTable;
	}

	@Override
	protected BaseRepository<Course> getRepository() {
		return this.courseRepository;
	}

	@Override
	protected Course createNewEntry() {
		return new Course();
	}

	@Override
	protected AbstractForm<Course> createForm(Course entry) {
		return new CourseEntryForm(entry, this.chairRepository);
	}

	@Override
	protected String getCaptionResourcePath() {
		return "/courseCaption.md";
	}

}