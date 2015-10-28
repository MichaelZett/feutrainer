package de.zettsystems.feutrainer.ui.base;

import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

/**
 * The Class AbstractBaseView.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractBaseView<T> extends VerticalLayout implements BaseView {

	/** The add new. */
	protected Button addNew = new MButton(FontAwesome.PLUS, this::add);

	/** The edit. */
	protected Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);

	/** The delete. */
	protected Button delete = new ConfirmButton(FontAwesome.TRASH_O, "Are you sure you want to delete the entry?",
			this::remove);

	/**
	 * Inits the layout.
	 */
	protected void initLayout() {
		this.addComponent(new MVerticalLayout(new RichText().withMarkDownResource(getCaptionResourcePath()),
				new MHorizontalLayout(this.addNew, this.edit, this.delete), getTable()).expand(getTable()));
		getTable().listEntities();
		getTable().addMValueChangeListener(e -> adjustActionButtonState());

	}

	/**
	 * Adjust action button state.
	 */
	protected void adjustActionButtonState() {
		boolean hasSelection = getTable().getValue() != null;
		this.edit.setEnabled(hasSelection);
		this.delete.setEnabled(hasSelection);
	}

	/**
	 * Close window.
	 */
	protected void closeWindow() {
		UI.getCurrent().getWindows().stream().forEach(w -> UI.getCurrent().removeWindow(w));
	}

	/**
	 * Adds the.
	 *
	 * @param clickEvent
	 *            the click event
	 */
	protected void add(ClickEvent clickEvent) {
		edit(createNewEntry());
	}

	/**
	 * Edits the.
	 *
	 * @param e
	 *            the e
	 */
	protected void edit(ClickEvent e) {
		edit(getTable().getValue());
	}

	/**
	 * Removes the.
	 *
	 * @param e
	 *            the e
	 */
	protected void remove(ClickEvent e) {
		getRepository().delete(getTable().getValue());
		getTable().setValue(null);
		getTable().listEntities();
	}

	/**
	 * Edits the.
	 *
	 * @param entry
	 *            the entry
	 */
	protected void edit(final T entry) {
		AbstractForm<T> form = createForm(entry);
		form.openInModalPopup();
		form.setSavedHandler(this::saveEntry);
		form.setResetHandler(this::resetEntry);
	}

	/**
	 * Save entry.
	 *
	 * @param entry
	 *            the entry
	 */
	protected void saveEntry(T entry) {
		getRepository().saveAndFlush(entry);
		getTable().listEntities();
		closeWindow();
	}

	/**
	 * Reset entry.
	 *
	 * @param entry
	 *            the entry
	 */
	protected void resetEntry(T entry) {
		getTable().listEntities();
		closeWindow();
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	protected abstract AbstractBaseTable<T> getTable();

	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	protected abstract BaseRepository<T> getRepository();

	/**
	 * Creates the new entry.
	 *
	 * @return the t
	 */
	protected abstract T createNewEntry();

	/**
	 * Creates the form.
	 *
	 * @param entry
	 *            the entry
	 * @return the abstract form
	 */
	protected abstract AbstractForm<T> createForm(T entry);

	/**
	 * Gets the caption resource path.
	 *
	 * @return the caption resource path
	 */
	protected abstract String getCaptionResourcePath();

}
