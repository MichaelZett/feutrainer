package de.zettsystems.feutrainer.ui.courses;

import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.UnsupportedFilterException;

import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.domain.courses.CourseUnitRepository;

public class CourseUnitSuggestingContainer extends BeanItemContainer<CourseUnit> {
	private CourseUnitRepository repo;

	public CourseUnitSuggestingContainer(CourseUnitRepository repo) throws IllegalArgumentException {
		super(CourseUnit.class);
		this.repo = repo;
	}

	@Override
	protected void addFilter(Filter filter) throws UnsupportedFilterException {
		SuggestionFilter suggestionFilter = (SuggestionFilter) filter;
		filterItems(suggestionFilter.getFilterString());
	}

	private void filterItems(String filterString) {
		removeAllItems();
		List<CourseUnit> entities = this.repo.findAllByNameLikeIgnoreCase("%" + filterString + "%");
		addAll(entities);
	}

	public void setCourseUnitBean(CourseUnit courseUnit) {
		removeAllItems();
		addBean(courseUnit);
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
