package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.ui.ComboBox;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.ui.base.AbstractAutocompleteComboBox;
import de.zettsystems.feutrainer.ui.base.BaseSuggestingContainer;

public class AutocompleteChapterComboBox extends AbstractAutocompleteComboBox<Chapter> {

	private ComboBox courseUnit;

	public AutocompleteChapterComboBox(ChapterRepository chapterRepository, ComboBox courseUnit) {
		super(chapterRepository, "Super Chapter");

	}

	@Override
	protected BaseSuggestingContainer<Chapter> getSuggestionContainer(BaseRepository<Chapter> repo) {
		return new ChapterSuggestingContainer((ChapterRepository) repo, this.courseUnit);
	}

}
