package com.project.textapp.parser.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.parser.AbstractTextParser;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.parser.ParseContext;

public class WordSymbolParserImpl extends AbstractTextParser {
    private static final Pattern WORD_OR_SYMBOL = Pattern.compile("\\S");
    @Override public void parse(ParseContext context) {
        for (AbstractTextComponent paragraph : context.getResult().getChildren())
            for (AbstractTextComponent sentence : paragraph.getChildren())
                for (AbstractTextComponent lexeme : sentence.getChildren()) {
                    Matcher matcher = WORD_OR_SYMBOL.matcher(lexeme.getValue());
                    while (matcher.find()) {
                        String part = matcher.group();
                        ElementType elementType = part.codePoints().allMatch(Character::isLetterOrDigit) ? ElementType.WORD : ElementType.SYMBOL;
                        elementType.equals(ElementType.WORD) ? lexeme.addComponent(new TextBranch(elementType, part)) : lexeme.addComponent(new TextLeaf(elementType, part));
                    }
                }
        try { pass(context); } catch (TextProcessingException e) { throw new IllegalStateException(e); }
    }
}
