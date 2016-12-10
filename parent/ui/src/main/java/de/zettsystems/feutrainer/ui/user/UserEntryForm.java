package de.zettsystems.feutrainer.ui.user;

import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import de.zettsystems.feutrainer.domain.user.Role;
import de.zettsystems.feutrainer.domain.user.User;

public class UserEntryForm extends AbstractForm<User> {
	private TextField username = new MTextField("Username");
	private PasswordField password = new MPasswordField("Password");
	private TypedSelect<Role> role = new TypedSelect<>(Role.class);
	private CheckBox enabled = new CheckBox("Enabled");

	public UserEntryForm(User entry) {
		super();
		this.username.setWidth("200px");
		this.password.setWidth("200px");
		this.role.setWidth("200px");
		this.role.setCaption("Chapter");
		this.role.setOptions(Role.values());
		this.role.addMValueChangeListener(event -> entry.setRole(event.getValue()));
		this.enabled.addValueChangeListener(event -> entry.setEnabled((boolean) event.getProperty().getValue()));
		this.password.addValueChangeListener(event -> {
			if (!entry.getPassword().equals(event.getProperty().getValue())) {
				entry.setPasswordChanged(true);
			}
		});
		setSizeUndefined();
		setEntity(entry);
		setEagerValidation(true);
	}

	@Override
	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(getFields()).withWidth(""), getToolbar()).withWidth("");
	}

	private Component[] getFields() {
		return new Component[] { this.username, this.password, this.role, this.enabled };
	}
}
