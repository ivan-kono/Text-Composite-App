package com.project.textapp.service.impl;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.textapp.model.TextComponent;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComposite;
import com.project.textapp.service.TextTasksService;

public class TextTasksServiceImpl implements TextTasksService {
    private static final Pattern WORD = Pattern.compile("[\\p{L}\\p{N}]+");
    private final TextTraversalServiceImpl traversal = new TextTraversalServiceImpl();

    public long countSentencesWithDuplicateWords(TextComponent document) {
        return traversal.collect(document, ElementType.SENTENCE).stream().filter(this::hasDuplicate).count();
    }
    private boolean hasDuplicate(TextComponent sentence) {
        Set<String> seen = new HashSet<>();
        Matcher matcher = WORD.matcher(sentence.restore().toLowerCase(Locale.ROOT));
        while (matcher.find()) if (!seen.add(matcher.group())) return true;
        return false;
    }
    public List<String> sortSentencesByLetterCount(TextComponent document, char letter) {
        List<TextComponent> sentences = traversal.collect(document, ElementType.SENTENCE);
        return sentences.stream().sorted(Comparator.comparingLong(s -> countLetter(s.restore(), letter)))
                .map(TextComponent::restore).toList();
    }
    private long countLetter(String text, char letter) {
        return text.toLowerCase(Locale.ROOT).chars().filter(c -> c == Character.toLowerCase(letter)).count();
    }
    public void swapFirstAndLastLexemes(TextComponent document) {
        for (TextComponent sentence : traversal.collect(document, ElementType.SENTENCE)) {
            List<TextComponent> lexemes = sentence.getChildren();
            if (lexemes.size() > 1) {
                TextComponent first = lexemes.getFirst(), last = lexemes.getLast();
                sentence.getChildren().set(0,last);
                sentence.getChildren().set(sentence.getChildren().size()-1, first);
            }
        }
    }
}
