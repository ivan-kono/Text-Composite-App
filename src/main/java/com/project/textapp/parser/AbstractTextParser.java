package com.project.textapp.parser;
import com.project.textapp.exception.TextProcessingException;
public abstract class AbstractTextParser implements TextComponentParser {
    private TextComponentParser next;
    @Override public void setNext(TextComponentParser next) { this.next = next; }
    protected void pass(ParseContext context) throws TextProcessingException {
        if (next != null) next.parse(context);
    }
}
