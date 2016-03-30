package de.zettsystems.feutrainer.ui.courses;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.CourseRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;
import de.zettsystems.feutrainer.values.user.Role;

/**
 * The Class ChairView.
 */
@Secured({ Role.Constants.DATA, Role.Constants.ADMIN })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Course Units")
@FontAwesomeIcon(FontAwesome.BOOK)
@SpringView(name = CourseUnitView.VIEW_NAME)
public class CourseUnitView extends AbstractBaseView<CourseUnit> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "courseunit";

	@Autowired
	private CourseUnitTable courseUnitTable;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseUnitRepository courseUnitRepository;
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
	protected AbstractBaseTable<CourseUnit> getTable() {
		return this.courseUnitTable;
	}

	@Override
	protected BaseRepository<CourseUnit> getRepository() {
		return this.courseUnitRepository;
	}

	@Override
	protected CourseUnit createNewEntry() {
		return new CourseUnit();
	}

	@Override
	protected AbstractForm<CourseUnit> createForm(CourseUnit entry) {
		return new CourseUnitEntryForm(entry, this.chairRepository, this.courseRepository);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Course Unit Master Data</strong></h3>";
	}

}