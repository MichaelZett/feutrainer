package de.zettsystems.feutrainer.ui.base;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

public interface BaseView extends View {
	@Override
	default void enter(ViewChangeListener.ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}
