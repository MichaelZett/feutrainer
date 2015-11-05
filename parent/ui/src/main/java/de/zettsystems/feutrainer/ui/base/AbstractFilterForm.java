package de.zettsystems.feutrainer.ui.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.vaadin.viritin.button.PrimaryButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import de.zettsystems.feutrainer.domain.base.BaseRepository;

/**
 * The Class AbstractBaseForm.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractFilterForm<T> extends Window {

	/** The id. */
	protected TextField id = new MTextField("Id");

	/** The name. */
	protected TextField name = new MTextField("Name");

	PrimaryButton useFilter = new PrimaryButton("Use Filter");

	PrimaryButton reset = new PrimaryButton("Reset");

	private CloseHandler closeHandler;

	public AbstractFilterForm(BaseRepository<?>... repositories) {
		super("Set Filters");
		setModal(true);
		initializeBasicComponents();
		initializeAdditionalComponents(repositories);
		this.useFilter.addClickListener(event -> save(event));
		this.reset.addClickListener(event -> reset(event));
		setSizeUndefined();
		setContent(createContent());
		center();
	}

	private void initializeBasicComponents() {
		this.id.setWidth("200px");
		this.id.addValueChangeListener(event -> setTableIdFilter(this.id.getValue()));
		this.name.setWidth("200px");
		this.name.addValueChangeListener(event -> setTableNameFilter(this.name.getValue()));
	}

	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(getFields()).withWidth(""), getToolbar()).withWidth("");
	}

	protected HorizontalLayout getToolbar() {
		return new MHorizontalLayout(this.useFilter, this.reset);
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	protected Component[] getFields() {
		return Stream.concat(Arrays.stream(getBasicFields()), Arrays.stream(getAdditionalFields()))
				.toArray(Component[]::new);
	}

	/**
	 * Gets the basic fields.
	 *
	 * @return the basic fields
	 */
	protected Component[] getBasicFields() {
		return new Component[] { this.id, this.name };
	}

	protected void save(Button.ClickEvent e) {
		refresh();
	}

	protected void refresh() {
		getTable().listEntities();
		this.closeHandler.onClose();
	}

	protected void reset(ClickEvent event) {
		setTableIdFilter("");
		setTableNameFilter("");
		resetAdditionalFilters();
		refresh();
	}

	protected void setTableNameFilter(String newValue) {
		getTable().setNameFilter(newValue);
	}

	protected void setTableIdFilter(String newValue) {
		getTable().setIdFilter(newValue);
	}

	protected abstract AbstractBaseTable<T> getTable();

	protected abstract void resetAdditionalFilters();

	/**
	 * Gets the additional fields.
	 *
	 * @return the additional fields
	 */
	protected abstract Component[] getAdditionalFields();

	/**
	 * Initialize additional components.
	 */
	protected abstract void initializeAdditionalComponents(BaseRepository<?>... repositories);

	public void setCloseHandler(CloseHandler closeHandler) {
		this.closeHandler = closeHandler;
	}

	@FunctionalInterface
	public interface CloseHandler {
		void onClose();
	}

}
