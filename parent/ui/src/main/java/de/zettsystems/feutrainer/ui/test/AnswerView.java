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
import de.zettsystems.feutrainer.domain.test.Answer;
import de.zettsystems.feutrainer.domain.test.AnswerRepository;
import de.zettsystems.feutrainer.domain.test.QuestionRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;
import de.zettsystems.feutrainer.values.user.Role;

/**
 * The Class QuestionView.
 */
@Secured({ Role.Constants.DATA, Role.Constants.ADMIN })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Answer")
@FontAwesomeIcon(FontAwesome.QUESTION)
@SpringView(name = AnswerView.VIEW_NAME)
public class AnswerView extends AbstractBaseView<Answer> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "answer";

	@Autowired
	private AnswerTable answerTable;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Answer> getTable() {
		return this.answerTable;
	}

	@Override
	protected BaseRepository<Answer> getRepository() {
		return this.answerRepository;
	}

	@Override
	protected Answer createNewEntry() {
		return new Answer();
	}

	@Override
	protected AbstractForm<Answer> createForm(Answer entry) {
		return new AnswerEntryForm(entry, this.questionRepository);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Answer Master Data</strong></h3>";
	}

}