package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComponent;
import com.project.textapp.model.TextComposite;
import com.project.textapp.parser.TextParser;

public class ParagraphParserImpl extends AbstractTextParser {
    private static final Pattern PARAGRAPH_SEPARATOR = Pattern.compile("\\R\\s*\\R+");
    public ParagraphParserImpl(TextParser next) {
        super(next);
    }
    @Override
    public void parse(String source, TextComponent parent) {
        String normalized = source.replaceAll("[\\t ]+", " ").strip();
        if (!normalized.isEmpty()) {
            for (String part : PARAGRAPH_SEPARATOR.split(normalized)) {
                String paragraphText = part.strip();
                if (!paragraphText.isEmpty()) {
                    TextComponent paragraph = new TextComposite(ElementType.PARAGRAPH);
                    parent.add(paragraph);
                    try {
                        pass(paragraphText, paragraph);
                    }
                    catch (TextProcessingException e)
                    {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }

    }
}
