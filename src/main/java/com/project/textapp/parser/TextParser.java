package com.project.textapp.parser;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.TextComponent;

public interface TextParser {
    void parse(String text, TextComponent parent) throws TextProcessingException;
}
