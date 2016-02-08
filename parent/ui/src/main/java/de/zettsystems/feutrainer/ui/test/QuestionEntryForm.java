package de.zettsystems.feutrainer.ui.test;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.test.Question;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class QuestionEntryForm extends AbstractBaseForm<Question> {

	private TypedSelect<Chapter> chapter;

	public QuestionEntryForm(Question entry, ChapterRepository chapterRepo) {
		super(entry, chapterRepo);
	}

	@Override
	protected void initializeAdditionalComponents(Question entry, BaseRepository<?>... repository) {
		ChapterRepository chapterRepository = (ChapterRepository) repository[0];
		initializeInstituteSelect(entry, chapterRepository);
	}

	private void initializeInstituteSelect(Question entry, ChapterRepository chapterRepository) {
		this.chapter = new TypedSelect<>(Chapter.class);
		this.chapter.setCaption("Chapter");
		this.chapter.setOptions(chapterRepository.findAll());
		this.chapter.addMValueChangeListener(event -> {
			entry.setChapter(event.getValue());
		});
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.chapter };
	}

	@Override
	protected String getWidthForNameTextField() {
		return "1500px";
	}

}
