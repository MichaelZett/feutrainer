package de.zettsystems.feutrainer.ui.organisation;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

/**
 * The Class InstituteTable.
 */
@SpringComponent
@ViewScope
public class InstituteTable extends AbstractBaseTable<Institute> {

	/**
	 * Instantiates a new institute table.
	 */
	public InstituteTable() {
		super(Institute.class);
	}

	@Autowired
	private InstituteRepository instituteRepository;

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[0];
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[0];
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[0];
	}

	@Override
	protected BaseRepository<Institute> getRepository() {
		return this.instituteRepository;
	}

}
