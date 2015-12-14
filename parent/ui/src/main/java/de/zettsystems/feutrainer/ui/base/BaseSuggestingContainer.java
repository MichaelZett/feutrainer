package de.zettsystems.feutrainer.ui.base;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

public class BaseSuggestingContainer<T> extends BeanItemContainer<T> {
	private BaseRepository<T> repo;

	public BaseSuggestingContainer(BaseRepository<T> repo, Class<? super T> type) throws IllegalArgumentException {
		super(type);
		this.repo = repo;
	}

	protected void filterItems(String filterString) {
		removeAllItems();
		List<T> entities = this.repo.findAllByNameLikeIgnoreCase("%" + filterString + "%");
		addAll(entities);
	}

	public void setBean(T courseUnit) {
		removeAllItems();
		addBean(courseUnit);
	}

}
