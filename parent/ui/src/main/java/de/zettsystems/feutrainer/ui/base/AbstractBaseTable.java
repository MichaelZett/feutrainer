package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.SortableLazyList;
import org.vaadin.viritin.fields.MTable;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Institute;

/**
 * The Class AbstractBaseTable.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractBaseTable<T> extends MTable<T> {

	/** The Constant PAGESIZE. */
	protected static final int PAGESIZE = 45;

	/**
	 * Instantiates a new abstract base table.
	 *
	 * @param class1
	 *            the class1
	 */
	public AbstractBaseTable(Class<T> class1) {
		super(class1);
		withProperties(initializeProperties()).withColumnHeaders(initializeHeaderCaptions())
				.setSortableProperties(initializeSortableProperties()).withFullWidth();
	}

	/**
	 * List entities.
	 */
	@SuppressWarnings("unchecked")
	public void listEntities() {
		this.setBeans(new SortableLazyList<Institute>(
				(firstRow, asc,
						sortProperty) -> getRepository().findAllBy(new PageRequest(firstRow / PAGESIZE, PAGESIZE,
								asc ? Sort.Direction.ASC : Sort.Direction.DESC,
								sortProperty == null ? getDefaultSortProperty() : sortProperty)),
				() -> (int) getRepository().count(), PAGESIZE));

	}

	/**
	 * Gets the default sort property.
	 *
	 * @return the default sort property
	 */
	protected String getDefaultSortProperty() {
		return "id";
	}

	/**
	 * Initialize properties.
	 *
	 * @return the string[]
	 */
	protected String[] initializeProperties() {
		return Stream
				.concat(Arrays.stream(initializeBasicProperties()), Arrays.stream(initializeAdditionalProperties()))
				.toArray(String[]::new);
	}

	/**
	 * Initialize basic properties.
	 *
	 * @return the string[]
	 */
	protected String[] initializeBasicProperties() {
		return new String[] { "id", "name" };
	}

	/**
	 * Initialize header captions.
	 *
	 * @return the string[]
	 */
	protected String[] initializeHeaderCaptions() {
		return Stream.concat(Arrays.stream(initializeBasicHeaderCaptions()),
				Arrays.stream(initializeAdditionalHeaderCaptions())).toArray(String[]::new);
	}

	/**
	 * Initialize basic header captions.
	 *
	 * @return the string[]
	 */
	protected String[] initializeBasicHeaderCaptions() {
		return new String[] { "Institute Id", "Institute Name" };
	}

	/**
	 * Initialize sortable properties.
	 *
	 * @return the string[]
	 */
	protected String[] initializeSortableProperties() {
		return Stream.concat(Arrays.stream(initializeBasicSortableProperties()),
				Arrays.stream(initializeAdditionalSortableProperties())).toArray(String[]::new);
	}

	/**
	 * Initialize basic sortable properties.
	 *
	 * @return the string[]
	 */
	protected String[] initializeBasicSortableProperties() {
		return new String[] { "id", "name" };
	}

	/**
	 * Initialize additional properties.
	 *
	 * @return the string[]
	 */
	protected abstract String[] initializeAdditionalProperties();

	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	protected abstract BaseRepository<T> getRepository();

	/**
	 * Initialize additional sortable properties.
	 *
	 * @return the string[]
	 */
	protected abstract String[] initializeAdditionalSortableProperties();

	/**
	 * Initialize additional header captions.
	 *
	 * @return the string[]
	 */
	protected abstract String[] initializeAdditionalHeaderCaptions();

}
