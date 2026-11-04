package com.project.textapp.parser.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComposite;
import com.project.textapp.model.TextCharacter;
import com.project.textapp.model.TextComponent;
import com.project.textapp.parser.ParseContext;
import com.project.textapp.parser.TextParser;

public class WordParserImpl extends AbstractTextParser {
    private static final Pattern WORD_OR_SYMBOL = Pattern.compile("([\\p{L}\\p{N}]+)|([^\\p{L}\\p{N}])");
    public WordParserImpl(TextParser next) {
        super(next);
    }
    @Override public void parse(String source, TextComponent parent) {
                    Matcher matcher = WORD_OR_SYMBOL.matcher(source);
                    while (matcher.find()) {
                        String part = matcher.group();
                        if(part.codePoints().allMatch(Character::isLetterOrDigit)){
                            TextComponent word = new TextComposite(ElementType.WORD);
                            parent.add(word);
                            try {
                                pass(part, word);
                            }
                            catch (TextProcessingException e) {
                                throw new IllegalStateException(e);
                            }
                        }
                        else{
                            TextComponent symbol = new TextCharacter(ElementType.SYMBOL, part.charAt(0));
                            parent.add(symbol);
                        }
                    }
    }
}
