package tools;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import tools.ea.EaExtractorService;
import tools.toc.TocExtractorService;

/**
 * The Class DataExtractorService. ALTER TABLE #table#
 * AUTO_INCREMENT=#calculateValue#;
 */
@Component
public class DataExtractorController implements ApplicationRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataExtractorController.class);

	@Autowired
	EaExtractorService eaExtractorService;
	@Autowired
	TocExtractorService tocExtractorService;

	/**
	 * @see org.springframework.boot.ApplicationRunner#run(ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Optional<List<String>> sourcePathOptional = Optional.ofNullable(args.getOptionValues("sourcePath"));
		String sourcePath = sourcePathOptional.isPresent() ? sourcePathOptional.get().get(0)
				: "C:/Development/workspace/parent/tools/src/main/resources";
		LOGGER.info("Start extracting eas...");
		this.eaExtractorService.extractEas(sourcePath);
		LOGGER.info("Start extracting eas...");
		this.tocExtractorService.extractTocs(sourcePath);
	}

}
