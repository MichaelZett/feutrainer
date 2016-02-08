package de.zettsystems.feutrainer.ui.test;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.test.Answer;
import de.zettsystems.feutrainer.domain.test.Question;
import de.zettsystems.feutrainer.domain.test.QuestionRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class AnswerEntryForm extends AbstractBaseForm<Answer> {

	private TypedSelect<Question> question;
	private CheckBox correct;

	public AnswerEntryForm(Answer entry, QuestionRepository questionRepo) {
		super(entry, questionRepo);
	}

	@Override
	protected void initializeAdditionalComponents(Answer entry, BaseRepository<?>... repository) {
		QuestionRepository questionRepository = (QuestionRepository) repository[0];
		initializeInstituteSelect(entry, questionRepository);
		this.correct = new CheckBox("Correct");
	}

	private void initializeInstituteSelect(Answer entry, QuestionRepository questionRepository) {
		this.question = new TypedSelect<>(Question.class);
		this.question.setCaption("Question");
		this.question.setOptions(questionRepository.findAll());
		this.question.addMValueChangeListener(event -> {
			entry.setQuestion(event.getValue());
		});
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.question, this.correct };
	}

	@Override
	protected String getWidthForNameTextField() {
		return "1000px";
	}

}
