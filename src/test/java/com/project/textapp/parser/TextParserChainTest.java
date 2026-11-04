package com.project.textapp.parser;
import com.project.textapp.model.TextComponent;
import com.project.textapp.model.ElementType;
import com.project.textapp.service.impl.TextTraversalServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TextParserChainTest {
    @Test void parsesCompositeTreeAndReconstructsNormalizedText() throws Exception {
        String source = "Alpha  beta.\\n\\nGamma\\tdelta!";
        TextComponent tree = new TextParserChain().parse(source);
        assertEquals(2, tree.getChildren().size());
        assertEquals(2, new TextTraversalServiceImpl().collect(tree, ElementType.SENTENCE).size());
        assertEquals("Alpha beta.\\n\\nGamma delta!", tree.restore());
        assertFalse(new TextTraversalServiceImpl().collect(tree, ElementType.WORD).isEmpty());
    }
}
