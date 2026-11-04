package com.project.textapp.reader;
import com.project.textapp.exception.TextProcessingException;
public interface TextReader {
    String readText(String path) throws TextProcessingException;
}
