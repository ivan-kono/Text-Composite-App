package com.project.textapp;
import java.util.List;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComposite;
import com.project.textapp.parser.TextParser;
import com.project.textapp.parser.impl.*;
import com.project.textapp.service.impl.TextStatisticsServiceImpl;
import com.project.textapp.service.impl.TextTasksServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.reader.impl.TextReaderImpl;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String FILE_PATH = ("input/sample.txt");
    private static final char LETTER_FOR_SEARCH = 'a';
    public static void main(String[] args) {
        try {
            TextReaderImpl reader = new TextReaderImpl();
            String source = reader.readText(FILE_PATH);
            TextParser parserChain = new ParagraphParserImpl(
                    new SentenceParserImpl(
                            new LexemeParserImpl(
                                    new WordParserImpl(
                                            new SymbolParserImpl()))));
            TextComposite document = new TextComposite(ElementType.DOCUMENT);
            parserChain.parse(source, document);
            String restoredText = document.restore();
            restoredText = restoredText.replaceAll("(?<=[,.!?;:])(?!$)", " ");
            LOGGER.info("Restored text:\n{}", restoredText);
            TextStatisticsServiceImpl statistics = new TextStatisticsServiceImpl();
            LOGGER.info("Number of symbols in text: {}", statistics.countCharacters(restoredText));
            TextTasksServiceImpl tasks = new TextTasksServiceImpl();
            LOGGER.info("Number of sentences with repeating words: {}", tasks.countSentencesWithDuplicateWords(document));
            List<String> sorted = tasks.sortSentencesByLetterCount(document, LETTER_FOR_SEARCH);
            LOGGER.info("Sentences with letter '{}', ascending:\\n{}", LETTER_FOR_SEARCH, String.join("\\n", sorted));
            tasks.swapFirstAndLastLexemes(document);
            restoredText = document.restore();
            LOGGER.info("Text after swapping first and last lexemes:\\n{}", restoredText);
        } catch (TextProcessingException | RuntimeException e) {
            LOGGER.error("Couldn't process the text", e);
        }
    }
}
