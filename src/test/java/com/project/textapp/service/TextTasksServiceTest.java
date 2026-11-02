package com.project.textapp.service;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.parser.TextParserChain;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.service.impl.TextReconstructionServiceImpl;
import com.project.textapp.service.impl.TextTasksServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TextTasksServiceTest {
    private AbstractTextComponent parse(String s) throws Exception { return new TextParserChain().parse(s); }
    @Test void countsSentencesContainingRepeatedWords() throws Exception {
        AbstractTextComponent tree = parse("One fish, two fish. This is fine!");
        assertEquals(1, new TextTasksServiceImpl().countSentencesWithDuplicateWords(tree));
    }
    @Test void sortsSentencesByRequestedLetterCount() throws Exception {
        AbstractTextComponent tree = parse("aaa. bbb. abca.");
        assertEquals(java.util.List.of("bbb.", "abca.", "aaa."),
                new TextTasksServiceImpl().sortSentencesByLetterCount(tree, 'a'));
    }
    @Test void swapsOuterLexemesWithoutChangingSentenceLength() throws Exception {
        AbstractTextComponent tree = parse("Short middle extraordinarily.");
        TextReconstructionServiceImpl reconstruction = new TextReconstructionServiceImpl();
        String before = reconstruction.reconstruct(tree);
        new TextTasksServiceImpl().swapFirstAndLastLexemes(tree);
        String after = reconstruction.reconstruct(tree);
        assertEquals(before.length(), after.length());
        assertEquals("extraordinarily middle Short.", after);
    }
}
