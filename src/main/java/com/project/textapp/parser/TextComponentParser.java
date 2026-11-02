package com.project.textapp.parser;
import com.project.textapp.exception.TextProcessingException;
public interface TextComponentParser {
    void setNext(TextComponentParser next);
    void parse(ParseContext context) throws TextProcessingException;
}
