package com.project.textapp.service;

import com.project.textapp.model.TextComponent;

import java.util.*;

public interface TextTasksService {
    public long countSentencesWithDuplicateWords(TextComponent document);
    public List<String> sortSentencesByLetterCount(TextComponent document, char letter);
    public void swapFirstAndLastLexemes(TextComponent document);
}

