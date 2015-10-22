package de.zettsystems.feutrainer.ui.organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.SortableLazyList;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.organisation.Institute;
import de.zettsystems.feutrainer.domain.organisation.InstituteRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class InstituteTable extends AbstractBaseTable<Institute> {
	public InstituteTable(Class<Institute> class1) {
		super(class1);
	}

	@Autowired
	private InstituteRepository instituteRepository;

	@Override
	@SuppressWarnings("unchecked")
	public void listEntities() {
		this.setBeans(new SortableLazyList<Institute>(
				(firstRow, asc,
						sortProperty) -> this.instituteRepository.findAllBy(new PageRequest(firstRow / PAGESIZE,
								PAGESIZE, asc ? Sort.Direction.ASC : Sort.Direction.DESC,
								sortProperty == null ? "id" : sortProperty)),
				() -> (int) this.instituteRepository.count(), PAGESIZE));

	}

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

}
