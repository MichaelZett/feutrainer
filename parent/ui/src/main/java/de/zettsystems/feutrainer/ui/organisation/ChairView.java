package de.zettsystems.feutrainer.ui.organisation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.spring.annotation.SpringView;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Chair;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.domain.organisation.DepartmentRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class ChairView.
 */
@SpringView(name = ChairView.VIEW_NAME)
public class ChairView extends AbstractBaseView<Chair> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "chair";

	@Autowired
	private ChairTable chairTable;
	@Autowired
	private ChairRepository chairRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * Inits the View.
	 */
	@PostConstruct
	public void init() {
		initLayout();
	}

	@Override
	protected AbstractBaseTable<Chair> getTable() {
		return this.chairTable;
	}

	@Override
	protected BaseRepository<Chair> getRepository() {
		return this.chairRepository;
	}

	@Override
	protected Chair createNewEntry() {
		return new Chair();
	}

	@Override
	protected AbstractForm<Chair> createForm(Chair entry) {
		return new ChairEntryForm(entry, this.departmentRepository);
	}

	@Override
	protected String getCaptionResourcePath() {
		return "/chairCaption.md";
	}

}