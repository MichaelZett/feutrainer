package de.zettsystems.feutrainer.ui.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.test.Question;
import de.zettsystems.feutrainer.domain.test.QuestionRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class QuestionTable extends AbstractBaseTable<Question> {

	@Autowired
	private QuestionRepository questionRepository;

	public QuestionTable() {
		super(Question.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "chapter" };
	}

	@Override
	protected BaseRepository<Question> getRepository() {
		return this.questionRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "chapter" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Chapter" };
	}

}
