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

public abstract class AbstractBaseView<T> extends VerticalLayout implements BaseView {

	protected Button addNew = new MButton(FontAwesome.PLUS, this::add);
	protected Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
	protected Button delete = new ConfirmButton(FontAwesome.TRASH_O, "Are you sure you want to delete the entry?",
			this::remove);

	protected void initLayout() {
		this.addComponent(new MVerticalLayout(new RichText().withMarkDownResource(getCaptionResourcePath()),
				new MHorizontalLayout(this.addNew, this.edit, this.delete), getTable()).expand(getTable()));
		getTable().listEntities();
		getTable().addMValueChangeListener(e -> adjustActionButtonState());

	}

	protected void adjustActionButtonState() {
		boolean hasSelection = getTable().getValue() != null;
		this.edit.setEnabled(hasSelection);
		this.delete.setEnabled(hasSelection);
	}

	protected void closeWindow() {
		UI.getCurrent().getWindows().stream().forEach(w -> UI.getCurrent().removeWindow(w));
	}

	protected void add(ClickEvent clickEvent) {
		edit(createNewEntry());
	}

	protected void edit(ClickEvent e) {
		edit(getTable().getValue());
	}

	protected void remove(ClickEvent e) {
		getRepository().delete(getTable().getValue());
		getTable().setValue(null);
		getTable().listEntities();
	}

	protected void edit(final T entry) {
		AbstractForm<T> form = createForm(entry);
		form.openInModalPopup();
		form.setSavedHandler(this::saveEntry);
		form.setResetHandler(this::resetEntry);
	}

	protected void saveEntry(T entry) {
		getRepository().save(entry);
		getTable().listEntities();
		closeWindow();
	}

	protected void resetEntry(T entry) {
		getTable().listEntities();
		closeWindow();
	}

	protected abstract AbstractBaseTable<T> getTable();

	protected abstract BaseRepository<T> getRepository();

	protected abstract T createNewEntry();

	protected abstract AbstractForm<T> createForm(T entry);

	protected abstract String getCaptionResourcePath();

}
