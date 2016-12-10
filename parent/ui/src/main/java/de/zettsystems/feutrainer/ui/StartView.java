package de.zettsystems.feutrainer.ui;

import javax.annotation.PostConstruct;

import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.viritin.label.RichText;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import de.zettsystems.feutrainer.domain.user.Role;

/**
 * The Class StartView.
 */
@Secured({ Role.Constants.DATA, Role.Constants.ADMIN, Role.Constants.STUDENT })
@SideBarItem(sectionId = Sections.GENERAL, caption = "Start Page")
@FontAwesomeIcon(FontAwesome.INFO)
@SpringView(name = StartView.VIEW_NAME)
public class StartView extends VerticalLayout implements View {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "";

	/**
	 * Inits the.
	 */
	@PostConstruct
	void init() {
		this.addComponent(new RichText().withMarkDownResource("/README.md"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}