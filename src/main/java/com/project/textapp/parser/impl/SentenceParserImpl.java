package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComponent;
import com.project.textapp.model.TextComposite;
import com.project.textapp.parser.TextParser;

public class SentenceParserImpl extends AbstractTextParser {
    private static final Pattern SENTENCE_SEPARATOR = Pattern.compile("(?<=[.!?])\\s+");
    public SentenceParserImpl(TextParser next) {
        super(next);
    }
    @Override
    public void parse(String source, TextComponent parent) {
            for (String part : SENTENCE_SEPARATOR.split(source)) {
                String sentenceText = part.strip();
                TextComponent sentence = new TextComposite(ElementType.SENTENCE);
                if (!sentenceText.isEmpty()) {
                    parent.add(sentence);
                    try {
                        pass(sentenceText, sentence);
                    }
                    catch (TextProcessingException e)
                    {
                        throw new IllegalStateException(e);
                    }
                }
            }
    }
}
