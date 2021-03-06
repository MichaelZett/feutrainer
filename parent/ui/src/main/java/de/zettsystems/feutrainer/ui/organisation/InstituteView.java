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
import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;
import de.zettsystems.feutrainer.domain.user.Role;
import de.zettsystems.feutrainer.ui.Sections;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;
import de.zettsystems.feutrainer.ui.base.AbstractBaseView;

/**
 * The Class InstituteView.
 */
@Secured({ Role.Constants.DATA, Role.Constants.ADMIN })
@SideBarItem(sectionId = Sections.MASTER_DATA, caption = "Institutes")
@FontAwesomeIcon(FontAwesome.SITEMAP)
@SpringView(name = InstituteView.VIEW_NAME)
public class InstituteView extends AbstractBaseView<Institute> {

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "institute";

	@Autowired
	private InstituteTable instituteTable;
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
	protected AbstractBaseTable<Institute> getTable() {
		return this.instituteTable;
	}

	@Override
	protected BaseRepository<Institute> getRepository() {
		return this.instituteRepository;
	}

	@Override
	protected Institute createNewEntry() {
		return new Institute();
	}

	@Override
	protected AbstractForm<Institute> createForm(Institute entry) {
		return new InstituteEntryForm(entry);
	}

	@Override
	protected String getCaptionHtml() {
		return "<h3 class=\"master-data-caption\"><strong>Institute Master Data</strong></h3>";
	}

}