package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.SortableLazyList;
import org.vaadin.viritin.fields.MTable;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Institute;

public abstract class AbstractBaseTable<T> extends MTable<T> {
	protected static final int PAGESIZE = 45;

	public AbstractBaseTable(Class<T> class1) {
		super(class1);
		withProperties(initializeProperties()).withColumnHeaders(initializeHeaderCaptions())
				.setSortableProperties(initializeSortableProperties()).withFullWidth();
	}

	@SuppressWarnings("unchecked")
	public void listEntities() {
		this.setBeans(new SortableLazyList<Institute>(
				(firstRow, asc,
						sortProperty) -> getRepository().findAllBy(new PageRequest(firstRow / PAGESIZE, PAGESIZE,
								asc ? Sort.Direction.ASC : Sort.Direction.DESC,
								sortProperty == null ? getDefaultSortProperty() : sortProperty)),
				() -> (int) getRepository().count(), PAGESIZE));

	}

	protected String getDefaultSortProperty() {
		return "id";
	}

	protected String[] initializeProperties() {
		return Stream
				.concat(Arrays.stream(initializeBasicProperties()), Arrays.stream(initializeAdditionalProperties()))
				.toArray(String[]::new);
	}

	protected String[] initializeBasicProperties() {
		return new String[] { "id", "name" };
	}

	protected String[] initializeHeaderCaptions() {
		return Stream.concat(Arrays.stream(initializeBasicHeaderCaptions()),
				Arrays.stream(initializeAdditionalHeaderCaptions())).toArray(String[]::new);
	}

	protected String[] initializeBasicHeaderCaptions() {
		return new String[] { "Institute Id", "Institute Name" };
	}

	protected String[] initializeSortableProperties() {
		return Stream.concat(Arrays.stream(initializeBasicSortableProperties()),
				Arrays.stream(initializeAdditionalSortableProperties())).toArray(String[]::new);
	}

	protected String[] initializeBasicSortableProperties() {
		return new String[] { "id", "name" };
	}

	protected abstract String[] initializeAdditionalProperties();

	protected abstract BaseRepository<T> getRepository();

	protected abstract String[] initializeAdditionalSortableProperties();

	protected abstract String[] initializeAdditionalHeaderCaptions();

}
