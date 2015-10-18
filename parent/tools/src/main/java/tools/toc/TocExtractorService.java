package tools.toc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tools.util.DataExtractionUtilities;

@Component
public class TocExtractorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TocExtractorService.class);

	private static final String INSERT_CHAPTER = "INSERT INTO `chapter` (`id`,`name`,`optimistic_concurrency`,`course_unit_entityKey`,`super_chapter_entityKey`) VALUES ('%s','%s',0,(SELECT entity_key FROM course_unit where id = '%s'),%s);";

	@Autowired
	DataExtractionUtilities dataExtractionUtilities;

	/**
	 * Extract tocs.
	 *
	 * @param sourcePath
	 *            the source path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public void extractTocs(String sourcePath) throws IOException, FileNotFoundException {

		final List<Path> files = this.dataExtractionUtilities.getAllFiles(sourcePath, "toc");
		for (Path path : files) {
			try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
				String filename = FilenameUtils.removeExtension(path.getFileName().toString());
				LOGGER.info("Working on File: {}.", filename);
				Map<String, ChapterData> chapters = new HashMap<>();
				ChapterData chapterData = null;
				for (String line : reader.lines().collect(Collectors.toList())) {
					if (Character.isDigit(line.substring(0, 1).toCharArray()[0])) {
						if (chapterData != null) {
							chapters.put(chapterData.getChapter(), chapterData);
						}
						String chapter = line.substring(0, line.indexOf(" "));
						String name = this.extractName(line);
						chapterData = new ChapterData(filename, chapter, name);
					} else if (line.substring(0, 1).equals(".")) {
						// skip
					} else {
						chapterData.addMissingTextToName(this.extractName(line));
					}
				}

				if (chapterData != null) {
					chapters.put(chapterData.getChapter(), chapterData);
				}
				for (String chapter : chapters.keySet()) {
					if (chapter.length() > 1) {
						ChapterData superChapter = chapters.get(chapter.substring(0, chapter.length() - 2));
						chapters.get(chapter).setSuperChapterId(superChapter.getChapterId());
					}
				}
				for (ChapterData chap : chapters.values()) {
					String superChapter = chap.getSuperChapterId() == null ? "null"
							: String.format("(SELECT entity_key FROM chapter where id = '%s')",
									chap.getSuperChapterId());
					System.out.println(String.format(INSERT_CHAPTER, chap.getChapterId(), chap.getChapterName(),
							chap.getCourseUnitId(), superChapter));
				}
			}
		}

	}

	private String extractName(String substring) {
		return substring.replaceAll("[.]|[0-9]", "").trim();
	}

}
