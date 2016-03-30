package de.zettsystems.feutrainer.ui.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.zettsystems.feutrainer.domain.user.User;
import de.zettsystems.feutrainer.domain.user.UserRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.BaseView;
import de.zettsystems.feutrainer.values.user.Role;

/**
 * The Class UserView.
 */
@Secured({ Role.Constants.ADMIN })
@SideBarItem(sectionId = Sections.ADMIN, caption = "Users")
@FontAwesomeIcon(FontAwesome.USERS)
@SpringView(name = UserView.VIEW_NAME)
public class UserView extends VerticalLayout implements BaseView {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "users";
	/** The add new. */
	private Button addNew = new MButton(FontAwesome.PLUS, this::add);

	/** The edit. */
	private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);

	/** The delete. */
	private Button delete = new ConfirmButton(FontAwesome.TRASH_O, "Are you sure you want to delete the entry?",
			this::remove);

	/** The filter. */
	private Button filter = new MButton(FontAwesome.FILTER, this::filter);

	/** The filter label. */
	private Label filterLabel = new Label();
	@Autowired
	private UserTable userTable;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	/**
	 * Inits the layout.
	 */
	private void initLayout() {
		this.filter.setVisible(false);
		this.addComponent(new MVerticalLayout(new MHorizontalLayout(new RichText().withSafeHtml(getCaptionHtml()),
				this.addNew, this.edit, this.delete, this.filter, this.filterLabel), userTable).expand(userTable));
		userTable.listEntities();
		userTable.addMValueChangeListener(e -> adjustActionButtonState());

	}

	private User createNewEntry() {
		return new User();
	}

	private String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>User Master Data</strong></h3>";
	}

	/**
	 * Adjust action button state.
	 */
	private void adjustActionButtonState() {
		boolean hasSelection = userTable.getValue() != null;
		this.edit.setEnabled(hasSelection);
		this.delete.setEnabled(hasSelection);
	}

	private void closeWindow() {
		UI.getCurrent().getWindows().stream().forEach(w -> UI.getCurrent().removeWindow(w));
	}

	private void add(ClickEvent clickEvent) {
		edit(createNewEntry());
	}

	private void edit(ClickEvent e) {
		edit(userTable.getValue());
	}

	private void filter(ClickEvent e) {
		UI.getCurrent().addWindow(getFilterWindow());
	}

	private Window getFilterWindow() {
		return null;
	}

	private void remove(ClickEvent e) {
		userRepository.delete(userTable.getValue());
		userTable.setValue(null);
		userTable.listEntities();
	}

	private void edit(final User entry) {
		AbstractForm<User> form = new UserEntryForm(entry);
		form.openInModalPopup();
		form.setSavedHandler(this::saveEntry);
		form.setResetHandler(this::resetEntry);
	}

	private void saveEntry(User entry) {
		entry.setPassword(passwordEncoder.encode(entry.getPassword()));
		userRepository.saveAndFlush(entry);
		userTable.listEntities();
		closeWindow();
	}

	private void resetEntry(User entry) {
		userTable.listEntities();
		closeWindow();
	}
}