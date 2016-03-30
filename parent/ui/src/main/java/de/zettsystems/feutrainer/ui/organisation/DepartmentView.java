package de.zettsystems.feutrainer.ui.organisation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Department;
import de.zettsystems.feutrainer.domain.organisation.DepartmentRepository;
import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;
import de.zettsystems.feutrainer.values.user.Role;

/**
 * The Class InstituteView.
 */
@Secured({ Role.Constants.DATA, Role.Constants.ADMIN })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Departments")
@FontAwesomeIcon(FontAwesome.SITEMAP)
@SpringView(name = DepartmentView.VIEW_NAME)
public class DepartmentView extends AbstractBaseView<Department> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "department";

	@Autowired
	private DepartmentTable departmentTable;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private InstituteRepository instituteRepository;

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
		return new DepartmentEntryForm(entry, this.instituteRepository);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Department Master Data</strong></h3>";
	}
}