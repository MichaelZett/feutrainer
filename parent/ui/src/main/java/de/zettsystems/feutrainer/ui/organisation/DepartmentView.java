package de.zettsystems.feutrainer.ui.organisation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.domain.organisation.DepartmentRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class InstituteView.
 */
@SpringView(name = DepartmentView.VIEW_NAME)
public class DepartmentView extends AbstractBaseView<Department> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "department";

	@Autowired
	private DepartmentTable departmentTable;
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Department> getTable() {
		return this.departmentTable;
	}

	@Override
	protected BaseRepository<Department> getRepository() {
		return this.departmentRepository;
	}

	@Override
	protected Department createNewEntry() {
		return new Department();
	}

	@Override
	protected AbstractForm<Department> createForm(Department entry) {
		return new DepartmentEntryForm(entry);
	}

	@Override
	protected String getCaptionResourcePath() {
		return "/departmentCaption.md";
	}

}