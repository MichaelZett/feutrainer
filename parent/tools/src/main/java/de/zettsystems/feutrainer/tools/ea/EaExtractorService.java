package de.zettsystems.feutrainer.tools.ea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.zettsystems.feutrainer.tools.util.DataExtractionUtilities;

@Component
public class EaExtractorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EaExtractorService.class);
	private static final String INSERT_QUESTION = "INSERT INTO `question` (`id`,`name`,`optimistic_concurrency`,`additional_text`,`image_file_name`,`chapter_entityKey`) "
			+ "	VALUES ('%s','%s',0,NULL,NULL,2);";
	private static final String INSERT_ANSWER = "INSERT INTO `answer` (`id`,`name`,`optimistic_concurrency`,`additional_text`,`correct`,`image_file_name`,`question_entityKey`) "
			+ "VALUES ('%s','%s',0,NULL,0,NULL,(SELECT entity_key FROM question q where q.id='%s'));";

	private Pattern stringAfterKE = Pattern.compile("(?<=KE).*");

	@Autowired
	DataExtractionUtilities dataExtractionUtilities;

	public void extractEas(String sourcePath) throws IOException, FileNotFoundException {
		final List<Path> files = this.dataExtractionUtilities.getAllFiles(sourcePath, "ea");
		for (Path path : files) {
			Set<QuestionData> questions = new HashSet<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
				String filename = FilenameUtils.removeExtension(path.getFileName().toString());
				LOGGER.info("Working on File: {}.", filename);
				int ke = 1;
				QuestionData q = null;
				StringBuffer questionText = null;
				for (String line : reader.lines().collect(Collectors.toList())) {
					if (line.contains("Teil") && line.contains("KE") && line.contains("RP")) {
						// get the number of the KE
						Matcher matcher = this.stringAfterKE.matcher(line);
						if (matcher.find()) {
							ke = Character.getNumericValue(matcher.group(0).charAt(1));
							LOGGER.info("Set chapter to {}.", ke);
						}
					} else if (line.contains("Aufgabe") && line.contains("RP")) {
						this.finishCurrentQuestion(questions, q, questionText);
						int aufgabe = Integer.parseInt(line.substring(8, 10).trim());
						q = new QuestionData(filename, ke, aufgabe);
						LOGGER.info("Created new question for aufgabe: {}.", aufgabe);
						questionText = new StringBuffer();
					} else if (!(line.startsWith("A ") || line.startsWith("B "))) {
						questionText.append(" " + line);
						LOGGER.debug("Appended following text to question: {}.", line);
					} else {
						q.addAnswer(line.substring(0, 1).trim(), line.substring(2));
						LOGGER.info("Added answer option {} as '{}'.", line.substring(0, 1).trim(), line.substring(2));
					}
				}
				this.finishCurrentQuestion(questions, q, questionText);
			}
			this.createSqlStatements(questions);
		}
	}

	private void finishCurrentQuestion(Set<QuestionData> questions, QuestionData q, StringBuffer questionText) {
		if (q != null) {
			q.setQuestion(questionText.toString());
			LOGGER.info("Setting question text as '{}'", questionText.toString());
			questions.add(q);
		}
	}

	private void createSqlStatements(Set<QuestionData> questions) {
		for (QuestionData q : questions) {
			String questionId = q.getEaId() + "_" + q.getAufgabe();
			System.out.println(String.format(INSERT_QUESTION, questionId, q.getQuestionText()));
		}
		for (QuestionData q : questions) {
			String questionId = q.getEaId() + "_" + q.getAufgabe();
			for (Entry<String, String> answer : q.getAnswers().entrySet()) {
				System.out.println(
						String.format(INSERT_ANSWER, questionId + answer.getKey(), answer.getValue(), questionId));
			}
		}

	}
}
