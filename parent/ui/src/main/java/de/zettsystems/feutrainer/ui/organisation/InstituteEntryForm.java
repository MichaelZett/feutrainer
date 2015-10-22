package de.zettsystems.feutrainer.ui.organisation;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class InstituteEntryForm extends AbstractBaseForm<Institute> {

	InstituteEntryForm(Institute instituteEntry) {
		super(instituteEntry);
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[0];
	}

}
