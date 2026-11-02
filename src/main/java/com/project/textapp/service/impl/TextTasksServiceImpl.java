package com.project.textapp.service.impl;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.service.TextTasksService;

public class TextTasksServiceImpl implements TextTasksService {
    private static final Pattern WORD = Pattern.compile("[\\p{L}\\p{N}]+");
    private final TextTraversalServiceImpl traversal = new TextTraversalServiceImpl();

    public long countSentencesWithDuplicateWords(TextBranch document) {
        return traversal.collect(document, ElementType.SENTENCE).stream().filter(this::hasDuplicate).count();
    }
    private boolean hasDuplicate(TextBranch sentence) {
        Set<String> seen = new HashSet<>();
        Matcher matcher = WORD.matcher(sentence.getValue().toLowerCase(Locale.ROOT));
        while (matcher.find()) if (!seen.add(matcher.group())) return true;
        return false;
    }
    public List<String> sortSentencesByLetterCount(TextBranch document, char letter) {
        List<TextBranch> sentences = traversal.collect(document, ElementType.SENTENCE);
        return sentences.stream().sorted(Comparator.comparingLong(s -> countLetter(s.getValue(), letter)))
                .map(TextBranch::getValue).toList();
    }
    private long countLetter(String text, char letter) {
        return text.toLowerCase(Locale.ROOT).chars().filter(c -> c == Character.toLowerCase(letter)).count();
    }
    public void swapFirstAndLastLexemes(TextBranch document) {
        for (TextBranch sentence : traversal.collect(document, ElementType.SENTENCE)) {
            List<TextBranch> lexemes = sentence.getChildren();
            if (lexemes.size() > 1) {
                TextBranch first = lexemes.get(0), last = lexemes.get(lexemes.size() - 1);
                String temp = first.getValue();
                first.setValue(last.getValue());
                last.setValue(temp);
            }
        }
    }
}
