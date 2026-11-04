package com.project.textapp.service;
import com.project.textapp.model.TextComponent;
import com.project.textapp.service.impl.TextTasksServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TextTasksServiceTest {
    private TextComponent parse(String s) throws Exception { return new TextParserChain().parse(s); }
    @Test void countsSentencesContainingRepeatedWords() throws Exception {
        TextComponent tree = parse("One fish, two fish. This is fine!");
        assertEquals(1, new TextTasksServiceImpl().countSentencesWithDuplicateWords(tree));
    }
    @Test void sortsSentencesByRequestedLetterCount() throws Exception {
        TextComponent tree = parse("aaa. bbb. abca.");
        assertEquals(java.util.List.of("bbb.", "abca.", "aaa."),
                new TextTasksServiceImpl().sortSentencesByLetterCount(tree, 'a'));
    }
    @Test void swapsOuterLexemesWithoutChangingSentenceLength() throws Exception {
        TextComponent tree = parse("Short middle extraordinarily.");
        String before = tree.restore();
        new TextTasksServiceImpl().swapFirstAndLastLexemes(tree);
        String after = tree.restore();
        assertEquals(before.length(), after.length());
        assertEquals("extraordinarily middle Short.", after);
    }
}
