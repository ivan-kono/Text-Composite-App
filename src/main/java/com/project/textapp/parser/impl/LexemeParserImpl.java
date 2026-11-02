package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.parser.AbstractTextParser;
import com.project.textapp.parser.ParseContext;

public class LexemeParserImpl extends AbstractTextParser {
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    @Override
    public void parse(ParseContext context) {
        for (AbstractTextComponent paragraph : context.getResult().getChildren())
            for (AbstractTextComponent sentence : paragraph.getChildren())
                for (String lexeme : WHITESPACE.split(sentence.getValue().strip()))
                    if (!lexeme.isEmpty()) sentence.getChildren().add(new TextBranch(ElementType.LEXEME, lexeme));
        try {
            pass(context);
        }
        catch (TextProcessingException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
