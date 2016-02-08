package de.zettsystems.feutrainer.ui.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.test.Question;
import de.zettsystems.feutrainer.domain.test.QuestionRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class QuestionView.
 */
@Secured({ "ROLE_DATA", "ROLE_ADMIN" })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Question")
@FontAwesomeIcon(FontAwesome.QUESTION)
@SpringView(name = QuestionView.VIEW_NAME)
public class QuestionView extends AbstractBaseView<Question> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "question";

	@Autowired
	private QuestionTable questionTable;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ChapterRepository chapterRepository;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Question> getTable() {
		return this.questionTable;
	}

	@Override
	protected BaseRepository<Question> getRepository() {
		return this.questionRepository;
	}

	@Override
	protected Question createNewEntry() {
		return new Question();
	}

	@Override
	protected AbstractForm<Question> createForm(Question entry) {
		return new QuestionEntryForm(entry, this.chapterRepository);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Question Master Data</strong></h3>";
	}

}