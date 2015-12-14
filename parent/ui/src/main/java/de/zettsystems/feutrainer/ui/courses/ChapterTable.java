package de.zettsystems.feutrainer.ui.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import de.zettsystems.feutrainer.domain.base.BaseRepository;
import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;
import de.zettsystems.feutrainer.domain.courses.CourseUnit;
import de.zettsystems.feutrainer.ui.base.AbstractBaseTable;

@SpringComponent
@ViewScope
public class ChapterTable extends AbstractBaseTable<Chapter> {

	private CourseUnit courseUnitFilter = null;

	@Autowired
	private ChapterRepository chapterRepository;

	public ChapterTable() {
		super(Chapter.class);
	}

	public void setCourseUnitFilter(CourseUnit courseUnitFilter) {
		this.courseUnitFilter = courseUnitFilter;
	}

	@Override
	protected int retrieveCount() {
		if (this.courseUnitFilter == null) {
			return (int) this.chapterRepository.countByIdLikeIgnoreCaseAndNameLikeIgnoreCase(getIdFilterText(),
					getNameFilterText());
		} else {
			return (int) this.chapterRepository.countByIdLikeIgnoreCaseAndNameLikeIgnoreCaseAndCourseUnit(
					getIdFilterText(), getNameFilterText(), this.courseUnitFilter);
		}
	}

	@Override
	protected List<Chapter> retrieveFilteredBeans(int firstRow, boolean asc, String sortProperty) {
		if (this.courseUnitFilter == null) {
			return this.chapterRepository.findAllByIdLikeIgnoreCaseAndNameLikeIgnoreCase(getIdFilterText(),
					getNameFilterText(), createPageRequest(firstRow, asc, sortProperty));
		} else {

			return this.chapterRepository.findAllByIdLikeIgnoreCaseAndNameLikeIgnoreCaseAndCourseUnit(getIdFilterText(),
					getNameFilterText(), this.courseUnitFilter, createPageRequest(firstRow, asc, sortProperty));
		}
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

	public String getCurrentFilterStatus() {
		if (this.courseUnitFilter == null && isContentEmpty(getIdFilterText()) && isContentEmpty(getNameFilterText())) {
			return "";
		} else {
			StringBuilder buffer = new StringBuilder(" Filter active with: ");
			if (!isContentEmpty(getIdFilterText())) {
				buffer.append("id = " + getIdFilterText());
			}
			if (!isContentEmpty(getNameFilterText())) {
				if (buffer.length() > 0) {
					buffer.append(" AND ");
				}
				buffer.append("name = " + getNameFilterText());
			}
			if (this.courseUnitFilter != null) {
				if (buffer.length() > 0) {
					buffer.append(" AND ");
				}
				buffer.append("course unit = " + this.courseUnitFilter.getName());
			}
			return buffer.toString();
		}
	}

}
