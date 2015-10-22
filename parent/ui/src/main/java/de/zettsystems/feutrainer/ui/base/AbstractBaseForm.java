package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public abstract class AbstractBaseForm<T> extends AbstractForm<T> {

	TextField id = new MTextField("Id");
	TextField name = new MTextField("Name");

	public AbstractBaseForm(T entry) {
		super();
		setSizeUndefined();
		setEntity(entry);
	}

	@Override
	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(getFields()).withWidth(""), getToolbar()).withWidth("");
	}

	protected Component[] getFields() {
		return Stream.concat(Arrays.stream(getBasicFields()), Arrays.stream(getAdditionalFields()))
				.toArray(Component[]::new);
	}

	protected abstract Component[] getAdditionalFields();

	protected Component[] getBasicFields() {
		return new Component[] { this.id, this.name };
	}

}
