package de.zettsystems.feutrainer.ui.courses;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.UnsupportedFilterException;

import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;
import de.zettsystems.feutrainer.ui.base.BaseSuggestingContainer;

public class CourseUnitSuggestingContainer extends BaseSuggestingContainer<CourseUnit> {

	public CourseUnitSuggestingContainer(CourseUnitRepository repo) throws IllegalArgumentException {
		super(repo, CourseUnit.class);
	}

	@Override
	protected void addFilter(Filter filter) throws UnsupportedFilterException {
		SuggestionFilter suggestionFilter = (SuggestionFilter) filter;
		filterItems(suggestionFilter.getFilterString());
	}

	public static class SuggestionFilter implements Container.Filter {

		private String filterString;

		public SuggestionFilter(String filterString) {
			this.filterString = filterString;
		}

		public String getFilterString() {
			return this.filterString;
		}

		@Override
		public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
			return false;
		}

		@Override
		public boolean appliesToProperty(Object propertyId) {
			return false;
		}
	}

}
