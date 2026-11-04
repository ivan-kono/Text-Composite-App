package com.project.textapp.parser.impl;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComposite;
import com.project.textapp.model.TextComponent;
import com.project.textapp.parser.TextParser;

public class LexemeParserImpl extends AbstractTextParser {
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    public LexemeParserImpl(TextParser next) {
        super(next);
    }
    @Override
    public void parse(String source, TextComponent parent) {
                for (String lexemeText : WHITESPACE.split(source.strip())){
                    TextComponent lexeme = new TextComposite(ElementType.LEXEME);
                    if (!lexemeText.isEmpty()) {
                       parent.add(lexeme);
                        try {
                            pass(lexemeText, lexeme);
                        }
                        catch (TextProcessingException e)
                        {
                            throw new IllegalStateException(e);
                        }
                    }
                }
    }
}

