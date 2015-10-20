package de.zettsystems.feutrainer.ui.organisation;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import de.zettsystems.feutrainer.ui.base.EntryChangedEvent;

@SpringView(name = InstituteView.VIEW_NAME)
public class InstituteView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "institute";

	@Autowired
	private InstituteContainerService instituteContainerService;

	private InstituteForm editForm;

	private EventBus eventBus = new EventBus();

	private Table entityTable;

	private Button deleteButton;
	private Button editButton;

	@PostConstruct
	void init() {
		this.eventBus.register(this);
		this.editForm = new InstituteForm(this.eventBus, this.instituteContainerService);
		this.initLayout();
	}

	@Subscribe
	public void reloadTable(EntryChangedEvent event) {
		this.instituteContainerService.refresh();
		this.entityTable.markAsDirty();
	}

	private void initLayout() {
		this.setMargin(true);
		this.setSpacing(true);
		// vaadin table
		this.entityTable = new Table();
		this.instituteContainerService.setAsContainerDataSource(this.entityTable);
		this.entityTable.setVisibleColumns(InstituteContainerService.PROPERTIES);
		this.entityTable.setColumnHeaders(InstituteContainerService.HEADERS);
		this.entityTable.setSelectable(true);
		this.entityTable.setWidth("100%");
		this.entityTable.setHeight("300px");

		// button bar
		final AbstractLayout buttonBar = this.initButtonBar();
		buttonBar.setWidth("100%");

		// edit Form
		this.editForm.setVisible(false);

		this.addComponent(this.entityTable);
		this.addComponent(buttonBar);
		this.addComponent(this.editForm);

	}

	private AbstractLayout initButtonBar() {
		final HorizontalLayout buttonBar = new HorizontalLayout();

		buttonBar.setSpacing(true);

		final Button addButton = new Button("Add entry");
		this.editButton = new Button("Edit Entry");
		this.deleteButton = new Button("Delete entry");

		buttonBar.addComponent(addButton);
		buttonBar.addComponent(this.editButton);
		buttonBar.addComponent(this.deleteButton);

		buttonBar.setComponentAlignment(addButton, Alignment.MIDDLE_LEFT);
		buttonBar.setComponentAlignment(this.editButton, Alignment.MIDDLE_CENTER);
		buttonBar.setComponentAlignment(this.deleteButton, Alignment.MIDDLE_RIGHT);

		addButton.addClickListener((event) -> this.editForm.setVisible(true));
		this.editButton.addClickListener((event) -> this.editSelectedEntry());
		this.deleteButton.addClickListener((event) -> this.removeSelectedEntry());

		return buttonBar;
	}

	private void editSelectedEntry() {
		final Object selection = this.entityTable.getValue();
		if (selection == null) {
			return;
		}
		if (selection instanceof Long) {
			this.editForm.setData(selection);
			this.editForm.setVisible(true);
		}
	}

	private void removeSelectedEntry() {
		final Object selection = this.entityTable.getValue();
		if (selection == null) {
			return;
		}
		if (selection instanceof Long) {
			final Long selectedIndex = (Long) selection;
			this.instituteContainerService.removeItem(selectedIndex);
		}
		if (selection instanceof Collection) {
			@SuppressWarnings("unchecked")
			final Collection<Long> selectionIndexes = (Collection<Long>) selection;
			for (final Long selectedIndex : selectionIndexes) {
				this.instituteContainerService.removeItem(selectedIndex);
			}
		}
		this.eventBus.post(new EntryChangedEvent());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()
	}
}