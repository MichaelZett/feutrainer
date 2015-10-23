package de.zettsystems.feutrainer.ui.organisation;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.domain.organisation.DepartmentRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

/**
 * The Class DepartmentTable.
 */
@SpringComponent
@ViewScope
public class DepartmentTable extends AbstractBaseTable<Department> {
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * Instantiates a new department table.
	 */
	public DepartmentTable() {
		super(Department.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "institute" };
	}

	@Override
	protected BaseRepository<Department> getRepository() {
		return this.departmentRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "institute" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Institute" };
	}

}
