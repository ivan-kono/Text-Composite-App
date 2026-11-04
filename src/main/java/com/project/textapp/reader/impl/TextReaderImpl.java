package com.project.textapp.reader.impl;

import com.project.textapp.reader.TextReader;
import com.project.textapp.exception.TextProcessingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextReaderImpl implements TextReader {
    private static final Logger LOGGER = LogManager.getLogger(TextReaderImpl.class);
    private static final String DEFAULT_FILE_PATH = ("input/sample.txt");
    @Override
    public String readText(String path) throws TextProcessingException{
        String fileName = path == null || Files.notExists(Path.of(path)) ? DEFAULT_FILE_PATH : path;
        try {
            String text = Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
            LOGGER.info("Successfully read text from the file with path {}", fileName);
            return text;
        } catch (IOException e) {
            throw new TextProcessingException("Couldn't read the file: " + fileName, e);
        }

    }

}
