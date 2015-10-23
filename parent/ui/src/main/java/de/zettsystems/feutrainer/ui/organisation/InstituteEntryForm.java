package de.zettsystems.feutrainer.ui.organisation;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

/**
 * The Class InstituteEntryForm.
 */
public class InstituteEntryForm extends AbstractBaseForm<Institute> {

	/**
	 * Instantiates a new institute entry form.
	 *
	 * @param instituteEntry
	 *            the institute entry
	 */
	InstituteEntryForm(Institute instituteEntry) {
		super(instituteEntry, null);
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[0];
	}

}
