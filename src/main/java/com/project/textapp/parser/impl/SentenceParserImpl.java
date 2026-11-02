package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.parser.AbstractTextParser;
import com.project.textapp.parser.ParseContext;

public class SentenceParserImpl extends AbstractTextParser {
    private static final Pattern SENTENCE_SEPARATOR = Pattern.compile("(?<=[.!?])\\s+");
    @Override public void parse(ParseContext context) {
        for (AbstractTextComponent paragraph : context.getResult().getChildren()) {
            String original = paragraph.getValue();
            for (String part : SENTENCE_SEPARATOR.split(original)) {
                String sentence = part.strip();
                if (!sentence.isEmpty()) paragraph.addComponent(new TextBranch(ElementType.SENTENCE, sentence));
            }
        }
        try { pass(context); } catch (TextProcessingException e) { throw new IllegalStateException(e); }
    }
}
