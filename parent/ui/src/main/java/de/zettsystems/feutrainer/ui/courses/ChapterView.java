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
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class ChairView.
 */
@Secured({ "ROLE_DATA", "ROLE_ADMIN" })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Chapter")
@FontAwesomeIcon(FontAwesome.BOOK)
@SpringView(name = ChapterView.VIEW_NAME)
public class ChapterView extends AbstractBaseView<Chapter> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "chapter";

	@Autowired
	private ChapterTable chapterTable;
	@Autowired
	private ChapterRepository chapterRepository;
	@Autowired
	private CourseUnitRepository courseUnitRepository;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Chapter> getTable() {
		return this.chapterTable;
	}

	@Override
	protected BaseRepository<Chapter> getRepository() {
		return this.chapterRepository;
	}

	@Override
	protected Chapter createNewEntry() {
		return new Chapter();
	}

	@Override
	protected AbstractForm<Chapter> createForm(Chapter entry) {
		return new ChapterEntryForm(entry, this.chapterRepository, this.courseUnitRepository);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Chapter Master Data</strong></h3>";
	}

}