package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.vaadin.viritin.fields.MTable;

public abstract class AbstractBaseTable<T> extends MTable<T> {
	protected static final int PAGESIZE = 45;

	public AbstractBaseTable(Class<T> class1) {
		super(class1);
		withProperties(initializeProperties()).withColumnHeaders(initializeHeaderCaptions())
				.setSortableProperties(initializeSortableProperties()).withFullWidth();
	}

	protected abstract void listEntities();

	protected String[] initializeProperties() {
		return Stream
				.concat(Arrays.stream(initializeBasicProperties()), Arrays.stream(initializeAdditionalProperties()))
				.toArray(String[]::new);
	}

	protected abstract String[] initializeAdditionalProperties();

	protected String[] initializeBasicProperties() {
		return new String[] { "id", "name" };
	}

	protected String[] initializeHeaderCaptions() {
		return Stream.concat(Arrays.stream(initializeBasicHeaderCaptions()),
				Arrays.stream(initializeAdditionalHeaderCaptions())).toArray(String[]::new);
	}

	protected abstract String[] initializeAdditionalHeaderCaptions();

	protected String[] initializeBasicHeaderCaptions() {
		return new String[] { "Institute Id", "Institute Name" };
	}

	protected String[] initializeSortableProperties() {
		return Stream.concat(Arrays.stream(initializeBasicSortableProperties()),
				Arrays.stream(initializeAdditionalSortableProperties())).toArray(String[]::new);
	}

	protected abstract String[] initializeAdditionalSortableProperties();

	protected String[] initializeBasicSortableProperties() {
		return new String[] { "id", "name" };
	}

}
