package de.zettsystems.feutrainer.ui.courses;

import org.vaadin.viritin.fields.LazyComboBox;

import de.zettsystems.feutrainer.domain.courses.Chapter;
import de.zettsystems.feutrainer.domain.courses.ChapterRepository;

@SuppressWarnings("unchecked")
public class LazyChapterComboBox extends LazyComboBox<Chapter> {
	private static final int PAGESIZE = 10;
	private ChapterRepository chapterRepository;

	public LazyChapterComboBox(ChapterRepository chapterRepository) {
		super(Chapter.class, (int firstRow, String filter) -> chapterRepository.findAllByNameLikeIgnoreCase(filter),
				(String filter) -> (int) chapterRepository.countByNameLikeIgnoreCase(filter));
		this.chapterRepository = chapterRepository;
		setCaption("Super Chapter");
	}

}
