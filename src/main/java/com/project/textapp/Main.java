package com.project.textapp;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.service.impl.TextReconstructionServiceImpl;
import com.project.textapp.service.impl.TextStatisticsServiceImpl;
import com.project.textapp.service.impl.TextTasksServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.parser.TextParserChain;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String FILE_PATH = ("input/sample.txt");
    private static final char LETTER_FOR_SEARCH = 'a';
    public static void main(String[] args) {
        try {
            String source = Files.readString(Path.of(FILE_PATH), StandardCharsets.UTF_8);
            AbstractTextComponent document = new TextParserChain().parse(source);
            TextReconstructionServiceImpl reconstruction = new TextReconstructionServiceImpl();
            String restored = reconstruction.reconstruct(document);
            LOGGER.info("Restored text:\\n{}", restored);
            TextStatisticsServiceImpl statistics = new TextStatisticsServiceImpl();
            LOGGER.info("Number of letters: {}", statistics.countLetters(restored));
            LOGGER.info("Number of symbols: {}", statistics.countCharacters(restored));
            TextTasksServiceImpl tasks = new TextTasksServiceImpl();
            LOGGER.info("Number of sentences with repeating words: {}", tasks.countSentencesWithDuplicateWords(document));
            List<String> sorted = tasks.sortSentencesByLetterCount(document, LETTER_FOR_SEARCH);
            LOGGER.info("Sentences with letter '{}', ascending:\\n{}", LETTER_FOR_SEARCH, String.join("\\n", sorted));
            tasks.swapFirstAndLastLexemes(document);
            LOGGER.info("Text after swapping first and last lexemes:\\n{}", reconstruction.reconstruct(document));
        } catch (java.io.IOException | TextProcessingException | RuntimeException e) {
            LOGGER.error("Couldn't process the text", e);
        }
    }
}
