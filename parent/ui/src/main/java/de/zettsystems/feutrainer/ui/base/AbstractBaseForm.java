package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

/**
 * The Class AbstractBaseForm.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractBaseForm<T> extends AbstractForm<T> {

	/** The id. */
	TextField id = new MTextField("Id");

	/** The name. */
	TextField name = new MTextField("Name");

	/**
	 * Instantiates a new abstract base form.
	 *
	 * @param entry
	 *            the entry
	 */
	public AbstractBaseForm(T entry) {
		super();
		setSizeUndefined();
		setEntity(entry);
	}

	@Override
	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(getFields()).withWidth(""), getToolbar()).withWidth("");
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	protected Component[] getFields() {
		return Stream.concat(Arrays.stream(getBasicFields()), Arrays.stream(getAdditionalFields()))
				.toArray(Component[]::new);
	}

	/**
	 * Gets the additional fields.
	 *
	 * @return the additional fields
	 */
	protected abstract Component[] getAdditionalFields();

	/**
	 * Gets the basic fields.
	 *
	 * @return the basic fields
	 */
	protected Component[] getBasicFields() {
		return new Component[] { this.id, this.name };
	}

}