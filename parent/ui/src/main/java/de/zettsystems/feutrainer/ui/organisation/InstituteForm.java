package de.zettsystems.feutrainer.ui.organisation;

import com.google.common.eventbus.EventBus;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.ui.base.EntryChangedEvent;

public class InstituteForm extends FormLayout {
	private InstituteContainerService jpaContainerService;

	private EventBus eventBus;
	boolean editMode = false;

	private Long instituteKey;
	private TextField id = new TextField("Id:");
	private TextField name = new TextField("Name:");

	public InstituteForm(EventBus eventBus, InstituteContainerService instituteContainerFactory) {
		this.eventBus = eventBus;
		this.jpaContainerService = instituteContainerFactory;
		this.initForm();
	}

	@Override
	public void setData(Object instituteKey) {
		this.instituteKey = (Long) instituteKey;
		this.id.setValue(this.jpaContainerService.getEntity(this.instituteKey).getId());
		this.name.setValue(this.jpaContainerService.getEntity(this.instituteKey).getName());
	}

	private void initForm() {
		this.addComponent(this.id);
		this.addComponent(this.name);

		final Button commit = new Button("Commit");
		final Button cancel = new Button("Cancel");

		cancel.addClickListener(event -> this.clearAndHide());
		commit.addClickListener(event -> {
			this.commitForm();
			this.eventBus.post(new EntryChangedEvent());
			this.clearAndHide();
		});

		final HorizontalLayout buttonBar = new HorizontalLayout();

		buttonBar.addComponent(commit);
		buttonBar.addComponent(cancel);

		this.addComponent(buttonBar);
	}

	private void commitForm() {
		if (this.instituteKey != null) {
			this.jpaContainerService.editEntity(this.instituteKey, this.id.getValue(), this.name.getValue());
		} else {
			this.jpaContainerService.addEntity(new Institute(this.id.getValue(), this.name.getValue()));
		}
	}

	private void clearAndHide() {
		this.id.setValue("");
		this.name.setValue("");
		this.instituteKey = null;
		this.setVisible(false);
	}

}