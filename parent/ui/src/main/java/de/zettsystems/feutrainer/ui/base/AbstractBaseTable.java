package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.SortableLazyList;
import org.vaadin.viritin.fields.MTable;

import com.google.common.base.Strings;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

/**
 * The Class AbstractBaseTable.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractBaseTable<T> extends MTable<T> {

	private String idFilter = "";
	private String nameFilter = "";
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

	protected String getNameFilterText() {
		return addWildcards(this.nameFilter);
	}

	public void setNameFilter(String newValue) {
		this.nameFilter = newValue;
	}

	protected String getIdFilterText() {
		return addWildcards(this.idFilter);
	}

	protected void setIdFilter(String newValue) {
		this.idFilter = newValue;
	}

	/**
	 * List entities.
	 */
	@SuppressWarnings("unchecked")
	public void listEntities() {
		setBeans(new SortableLazyList<T>(
				(firstRow, asc, sortProperty) -> retrieveFilteredBeans(firstRow, asc, sortProperty),
				() -> retrieveCount(), PAGESIZE));
		setValue(null);
	}

	protected int retrieveCount() {
		return (int) getRepository().count();
	}

	protected List<T> retrieveFilteredBeans(int firstRow, boolean asc, String sortProperty) {
		return getRepository().findAllBy(createPageRequest(firstRow, asc, sortProperty));
	}

	protected PageRequest createPageRequest(int firstRow, boolean asc, String sortProperty) {
		return new PageRequest(firstRow / PAGESIZE, PAGESIZE, asc ? Sort.Direction.ASC : Sort.Direction.DESC,
				sortProperty == null ? getDefaultSortProperty() : sortProperty);
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
		return new String[] { "Id", "Name" };
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

	protected boolean isContentEmpty(String valueToCheck) {
		return Strings.isNullOrEmpty(valueToCheck) && valueToCheck.length() <= 2;
	}

	protected String addWildcards(String plainText) {
		return "%" + plainText + "%";
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
