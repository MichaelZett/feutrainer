package de.zettsystems.feutrainer.ui.organisation;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.Component;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Chair;
import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.domain.organisation.DepartmentRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class ChairEntryForm extends AbstractBaseForm<Chair> {

	private TypedSelect<Department> department;

	public ChairEntryForm(Chair entry, DepartmentRepository repo) {
		super(entry, repo);
	}

	@Override
	protected void initializeAdditionalComponents(Chair entry, BaseRepository<?> repository) {
		DepartmentRepository departmentRepository = (DepartmentRepository) repository;
		initializeDepartmentSelect(entry, departmentRepository);
	}

	private void initializeDepartmentSelect(Chair entry, DepartmentRepository departmentRepository) {
		this.department = new TypedSelect<>(Department.class);
		this.department.setCaption("Department");
		this.department.setOptions(departmentRepository.findAll());
		this.department.addMValueChangeListener(event -> {
			entry.setDepartment(event.getValue());
		});
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.department };
	}

	@Override
	protected String getWidthForNameTextField() {
		return "750px";

	}

}
