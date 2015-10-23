package de.zettsystems.feutrainer.ui;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class StartView.
 */
@SpringView(name = StartView.VIEW_NAME)
public class StartView extends VerticalLayout implements View {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "";

	/**
	 * Inits the.
	 */
	@PostConstruct
	void init() {
		this.addComponent(new Label("Welcome to FeU Trainer"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}