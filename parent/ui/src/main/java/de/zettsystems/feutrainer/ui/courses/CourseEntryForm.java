package de.zettsystems.feutrainer.ui.courses;

import java.util.List;

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
	protected void initializeAdditionalComponents(Course entry, BaseRepository<?>... repository) {
		ChairRepository repo = (ChairRepository) repository[0];
		this.chairs = new MultiSelectTable<Chair>("Chairs").withColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
		this.chairs.setHeight("120px");
		this.chairs.setWidth("200px");
		List<Chair> allChairs = repo.findAll();
		this.chairs.setOptions(allChairs);
		if (!allChairs.isEmpty()) {
			this.chairs = this.chairs.withProperties("id");
		}
	}

	@Override
	protected Component[] getAdditionalFields() {
		return new Component[] { this.chairs };
	}

	@Override
	protected String getWidthForNameTextField() {
		return "500px";
	}

}
