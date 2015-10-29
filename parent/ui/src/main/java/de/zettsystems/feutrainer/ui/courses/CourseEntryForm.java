package de.zettsystems.feutrainer.ui.courses;

import org.vaadin.viritin.fields.MultiSelectTable;

import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Course;
import de.zettsystems.feutrainer.domain.organisation.Chair;
import de.zettsystems.feutrainer.domain.organisation.ChairRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseForm;

public class CourseEntryForm extends AbstractBaseForm<Course> {
	MultiSelectTable<Chair> chairs;

	public CourseEntryForm(Course entry, ChairRepository repo) {
		super(entry, repo);
	}

	@Override
	protected void initializeAdditionalComponents(Course entry, BaseRepository<?> repository) {
		ChairRepository repo = (ChairRepository) repository;
		this.chairs = new MultiSelectTable<Chair>("Chairs").withProperties("id")
				.withColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
		this.chairs.setHeight("120px");
		this.chairs.setWidth("200px");
		this.chairs.setOptions(repo.findAll());
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.chairs };
	}

}
