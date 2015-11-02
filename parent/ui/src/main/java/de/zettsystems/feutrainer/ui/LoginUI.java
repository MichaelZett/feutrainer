/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.zettsystems.feutrainer.ui;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.security.VaadinSecurity;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zoellerm
 */
@SpringUI(path = "/login")
@Theme(ValoTheme.THEME_NAME)
public class LoginUI extends UI {

	@Autowired
	VaadinSecurity vaadinSecurity;

	private TextField userName;

	private PasswordField passwordField;

	private CheckBox rememberMe;

	private Button login;

	private Label loginFailedLabel;
	private Label loggedOutLabel;

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("FeU Trainer - Login");

		FormLayout loginForm = new FormLayout();
		loginForm.setSizeUndefined();

		loginForm.addComponent(this.userName = new TextField("Username"));
		loginForm.addComponent(this.passwordField = new PasswordField("Password"));
		loginForm.addComponent(this.rememberMe = new CheckBox("Remember me"));
		loginForm.addComponent(this.login = new Button("Login"));
		this.login.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.login.setDisableOnClick(true);
		this.login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		this.login.addClickListener(event -> login());

		VerticalLayout loginLayout = new VerticalLayout();
		loginLayout.setSpacing(true);
		loginLayout.setSizeUndefined();

		if (request.getParameter("logout") != null) {
			this.loggedOutLabel = new Label("You have been logged out!");
			this.loggedOutLabel.addStyleName(ValoTheme.LABEL_SUCCESS);
			this.loggedOutLabel.setSizeUndefined();
			loginLayout.addComponent(this.loggedOutLabel);
			loginLayout.setComponentAlignment(this.loggedOutLabel, Alignment.BOTTOM_CENTER);
		}

		loginLayout.addComponent(this.loginFailedLabel = new Label());
		loginLayout.setComponentAlignment(this.loginFailedLabel, Alignment.BOTTOM_CENTER);
		this.loginFailedLabel.setSizeUndefined();
		this.loginFailedLabel.addStyleName(ValoTheme.LABEL_FAILURE);
		this.loginFailedLabel.setVisible(false);

		loginLayout.addComponent(loginForm);
		loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

		VerticalLayout rootLayout = new VerticalLayout(loginLayout);
		rootLayout.setSizeFull();
		rootLayout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
		setContent(rootLayout);
		setSizeFull();
	}

	private void login() {
		try {
			this.vaadinSecurity.login(this.userName.getValue(), this.passwordField.getValue(),
					this.rememberMe.getValue());
		} catch (AuthenticationException ex) {
			this.userName.focus();
			this.userName.selectAll();
			this.passwordField.setValue("");
			this.loginFailedLabel.setValue(String.format("Login failed: %s", ex.getMessage()));
			this.loginFailedLabel.setVisible(true);
			if (this.loggedOutLabel != null) {
				this.loggedOutLabel.setVisible(false);
			}
		} catch (Exception ex) {
			Notification.show("An unexpected error occurred", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
			LoggerFactory.getLogger(getClass()).error("Unexpected error while logging in", ex);
		} finally {
			this.login.setEnabled(true);
		}
	}
}
