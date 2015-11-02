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

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author zoellerm
 */
@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "error";
	private Label message;

	public ErrorView() {
		setMargin(true);
		addComponent(this.message = new Label());
		this.message.setSizeUndefined();
		this.message.addStyleName(ValoTheme.LABEL_FAILURE);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		this.message.setValue(String.format("No such view: %s", event.getViewName()));
	}
}
