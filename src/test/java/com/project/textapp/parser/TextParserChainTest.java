package com.project.textapp.parser;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.service.impl.TextReconstructionServiceImpl;
import com.project.textapp.service.impl.TextTraversalServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TextParserChainTest {
    @Test void parsesCompositeTreeAndReconstructsNormalizedText() throws Exception {
        String source = "Alpha  beta.\\n\\nGamma\\tdelta!";
        AbstractTextComponent tree = new TextParserChain().parse(source);
        assertEquals(2, tree.getChildren().size());
        assertEquals(2, new TextTraversalServiceImpl().collect(tree, ElementType.SENTENCE).size());
        assertEquals("Alpha beta.\\n\\nGamma delta!", new TextReconstructionServiceImpl().reconstruct(tree));
        assertFalse(new TextTraversalServiceImpl().collect(tree, ElementType.WORD).isEmpty());
    }
}
