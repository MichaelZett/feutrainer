package de.zettsystems.feutrainer.ui.courses;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class ChapterTable extends AbstractBaseTable<Chapter> {
	@Autowired
	private ChapterRepository chapterRepository;

	public ChapterTable() {
		super(Chapter.class);
	}

	@Override
	protected String[] initializeAdditionalProperties() {
		return new String[] { "superChapter", "courseUnit" };
	}

	@Override
	protected BaseRepository<Chapter> getRepository() {
		return this.chapterRepository;
	}

	@Override
	protected String[] initializeAdditionalSortableProperties() {
		return new String[] { "superChapter", "courseUnit" };
	}

	@Override
	protected String[] initializeAdditionalHeaderCaptions() {
		return new String[] { "Super Chapter", "Course Unit" };
	}

}
