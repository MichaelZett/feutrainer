package de.zettsystems.feutrainer.ui.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.test.Answer;
import de.zettsystems.feutrainer.domain.test.AnswerRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class AnswerTable extends AbstractBaseTable<Answer> {

	@Autowired
	private AnswerRepository answerRepository;

	public AnswerTable() {
		super(Answer.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "question", "correct" };
	}

	@Override
	protected BaseRepository<Answer> getRepository() {
		return this.answerRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "question", "correct" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Question", "Correct" };
	}

}
