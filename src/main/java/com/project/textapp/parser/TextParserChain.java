package com.project.textapp.parser;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.parser.impl.LexemeParserImpl;
import com.project.textapp.parser.impl.ParagraphParserImpl;
import com.project.textapp.parser.impl.SentenceParserImpl;
import com.project.textapp.parser.impl.WordSymbolParserImpl;

public class TextParserChain {
    private final TextComponentParser first;
    public TextParserChain() {
        ParagraphParserImpl paragraphs = new ParagraphParserImpl();
        SentenceParserImpl sentences = new SentenceParserImpl();
        LexemeParserImpl lexemes = new LexemeParserImpl();
        WordSymbolParserImpl words = new WordSymbolParserImpl();
        paragraphs.setNext(sentences); sentences.setNext(lexemes); lexemes.setNext(words);
        first = paragraphs;
    }
    public AbstractTextComponent parse(String source) throws TextProcessingException {
        if (source == null) throw new TextProcessingException("No source text found");
        ParseContext context = new ParseContext(source);
        try {
            first.parse(context);
        }
        catch (IllegalStateException e)
        {
            throw new TextProcessingException("Text processing error", e);
        }
        return context.getResult();
    }
}
