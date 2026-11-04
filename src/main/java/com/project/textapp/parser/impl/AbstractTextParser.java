package com.project.textapp.parser.impl;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.TextComponent;
import com.project.textapp.parser.TextParser;

public abstract class AbstractTextParser implements TextParser {
    private final TextParser next;
    protected AbstractTextParser(TextParser next){
        this.next = next;
    }
    protected void pass(String text, TextComponent parent) throws TextProcessingException {
        if (next != null) next.parse(text, parent);
    }
}
