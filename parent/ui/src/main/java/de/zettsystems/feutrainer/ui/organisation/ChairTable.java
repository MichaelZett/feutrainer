package de.zettsystems.feutrainer.ui.organisation;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.organisation.Chair;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class ChairTable extends AbstractBaseTable<Chair> {
	@Autowired
	private ChairRepository chairRepository;

	public ChairTable() {
		super(Chair.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "department" };
	}

	@Override
	protected BaseRepository<Chair> getRepository() {
		return this.chairRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "department" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Department" };
	}

}
