package de.zettsystems.feutrainer.ui.courses;

import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import com.vaadin.ui.ComboBox;

import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.ui.base.BaseSuggestingContainer;

public class ChapterSuggestingContainer extends BaseSuggestingContainer<Chapter> {
	private ChapterRepository repo;
	private ComboBox courseUnit;

	public ChapterSuggestingContainer(ChapterRepository repo, ComboBox courseUnit) throws IllegalArgumentException {
		super(repo, Chapter.class);
		this.repo = repo;
		this.courseUnit = courseUnit;
	}

	@Override
	protected void addFilter(Filter filter) throws UnsupportedFilterException {
		SuggestionFilter suggestionFilter = (SuggestionFilter) filter;
		filterItems(suggestionFilter.getFilterString());
	}

	@Override
	protected void filterItems(String filterString) {
		removeAllItems();
		List<Chapter> entities = this.repo.findAllByCourseUnitAndNameLikeIgnoreCase(
				(CourseUnit) this.courseUnit.getValue(), "%" + filterString + "%");
		addAll(entities);
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
