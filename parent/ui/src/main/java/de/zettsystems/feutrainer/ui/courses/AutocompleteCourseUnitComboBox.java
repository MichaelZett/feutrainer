package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;

import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;

public class AutocompleteCourseUnitComboBox extends ComboBox {

	public AutocompleteCourseUnitComboBox(CourseUnitRepository courseUnitRepository) {
		super("Course Unit");
		setItemCaptionMode(ItemCaptionMode.PROPERTY);
		setItemCaptionPropertyId("name");
		setWidth("500px");

		final CourseUnitSuggestingContainer container = new CourseUnitSuggestingContainer(courseUnitRepository);
		setContainerDataSource(container);
		setImmediate(true);
		addValueChangeListener(event -> container.setBean((CourseUnit) event.getProperty().getValue()));
	}

	@Override
	protected Filter buildFilter(String filterString, FilteringMode filteringMode) {
		return new CourseUnitSuggestingContainer.SuggestionFilter(filterString);
	}

}
