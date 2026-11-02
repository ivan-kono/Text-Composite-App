package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.parser.AbstractTextParser;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.parser.ParseContext;

public class ParagraphParserImpl extends AbstractTextParser {
    private static final Pattern PARAGRAPH_SEPARATOR = Pattern.compile("\\R\\s*\\R+");
    @Override public void parse(ParseContext context) {
        AbstractTextComponent document = new TextBranch(ElementType.DOCUMENT, context.getSource());
        String normalized = context.getSource().replaceAll("[\\t ]+", " ").strip();
        if (!normalized.isEmpty()) {
            for (String part : PARAGRAPH_SEPARATOR.split(normalized)) {
                String paragraphText = part.strip();
                if (!paragraphText.isEmpty()) document.addComponent(new TextBranch(ElementType.PARAGRAPH, paragraphText));
            }
        }
        context.setResult(document);
        try { pass(context); } catch (TextProcessingException e) { throw new IllegalStateException(e); }
    }
}
