package com.project.textapp.parser;
import com.project.textapp.model.TextComponent;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComposite;
import com.project.textapp.parser.impl.*;
import com.project.textapp.service.impl.TextTraversalServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TextParserChainTest {
    @Test void parsesCompositeTreeAndReconstructsNormalizedText() throws Exception {
        String source = "Alpha  beta.\\n\\nGamma\\tdelta!";
        TextComponent document = new TextComposite(ElementType.DOCUMENT);
        TextParser parserChain = new ParagraphParserImpl(
                new SentenceParserImpl(
                        new LexemeParserImpl(
                                new WordParserImpl(
                                        new SymbolParserImpl()))));
        parserChain.parse(source,document);
        assertEquals(2, document.getChildren().size());
        assertEquals(2, new TextTraversalServiceImpl().collect(document, ElementType.SENTENCE).size());
        assertEquals("Alpha beta.\\n\\nGamma delta!", document.restore());
        assertFalse(new TextTraversalServiceImpl().collect(document, ElementType.WORD).isEmpty());
    }
}
