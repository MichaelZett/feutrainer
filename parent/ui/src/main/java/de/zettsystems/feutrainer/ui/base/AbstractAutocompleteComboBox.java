package de.zettsystems.feutrainer.ui.base;

import com.vaadin.ui.ComboBox;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

public abstract class AbstractAutocompleteComboBox<T> extends ComboBox {

	@SuppressWarnings("unchecked")
	public AbstractAutocompleteComboBox(BaseRepository<T> repo, String caption) {
		super(caption);
		setItemCaptionMode(ItemCaptionMode.PROPERTY);
		setItemCaptionPropertyId(getCaptionId());
		setWidth(getElementWidth());
		setImmediate(true);

		BaseSuggestingContainer<T> container = getSuggestionContainer(repo);
		setContainerDataSource(container);
		addValueChangeListener(event -> container.setBean((T) event.getProperty().getValue()));
	}

	protected String getElementWidth() {
		return "500px";
	}

	protected String getCaptionId() {
		return "name";
	}

	protected abstract BaseSuggestingContainer<T> getSuggestionContainer(BaseRepository<T> repo);

}
