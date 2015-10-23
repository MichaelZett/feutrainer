package de.zettsystems.feutrainer.ui.organisation;

import org.vaadin.teemu.switchui.Switch;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

/**
 * The Class DepartmentEntryForm.
 */
public class DepartmentEntryForm extends AbstractBaseForm<Department> {
	private Switch institute = new Switch("Institute");

	/**
	 * Instantiates a new department entry form.
	 *
	 * @param entry
	 *            the entry
	 */
	public DepartmentEntryForm(Department entry) {
		super(entry);
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.institute };
	}

}
