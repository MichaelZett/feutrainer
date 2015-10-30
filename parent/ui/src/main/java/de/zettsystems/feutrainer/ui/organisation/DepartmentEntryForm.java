package de.zettsystems.feutrainer.ui.organisation;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

/**
 * The Class DepartmentEntryForm.
 */
public class DepartmentEntryForm extends AbstractBaseForm<Department> {

	private TypedSelect<Institute> institute;

	/**
	 * Instantiates a new department entry form.
	 *
	 * @param entry
	 *            the entry
	 * @param instituteRepository
	 *            the institute repository
	 */
	public DepartmentEntryForm(Department entry, InstituteRepository instituteRepository) {
		super(entry, instituteRepository);
	}

	@Override
	protected void initializeAdditionalComponents(Department entry, BaseRepository<?> repository) {
		InstituteRepository instituteRepository = (InstituteRepository) repository;
		initializeInstituteSelect(entry, instituteRepository);
	}

	private void initializeInstituteSelect(Department entry, InstituteRepository instituteRepository) {
		this.institute = new TypedSelect<>(Institute.class);
		this.institute.setCaption("Instiute");
		this.institute.setOptions(instituteRepository.findAll());
		this.institute.addMValueChangeListener(event -> {
			entry.setInstitute(event.getValue());
		});
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.institute };
	}

	@Override
	protected String getWidthForNameTextField() {
		return "500px";
	}

}
