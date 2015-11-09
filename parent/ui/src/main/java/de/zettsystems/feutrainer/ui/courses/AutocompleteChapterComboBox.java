package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.ui.ComboBox;

import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;

public class AutocompleteChapterComboBox extends ComboBox {

	public AutocompleteChapterComboBox(ChapterRepository chapterRepository, ComboBox courseUnit) {
		super("Super Chapter");
		setItemCaptionMode(ItemCaptionMode.PROPERTY);
		setItemCaptionPropertyId("name");
		setWidth("500px");

		final ChapterSuggestingContainer container = new ChapterSuggestingContainer(chapterRepository, courseUnit);
		setContainerDataSource(container);
		setImmediate(true);
		addValueChangeListener(event -> container.setChapterBean((Chapter) event.getProperty().getValue()));
	}

}
