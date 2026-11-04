package com.project.textapp.parser;
import com.project.textapp.exception.TextProcessingException;
import com.project.textapp.model.TextComponent;
import com.project.textapp.parser.impl.LexemeParserImpl;
import com.project.textapp.parser.impl.ParagraphParserImpl;
import com.project.textapp.parser.impl.SentenceParserImpl;
import com.project.textapp.parser.impl.WordParserImpl;

public class TextParserChain {
    private final TextParser first;
    public TextParserChain() {
        TextParser parserChain = new ParagraphParserImpl(
                new SentenceParserImpl(
                        new LexemeParserImpl(
                                new WordParserImpl(
                                        new SymbolParser()))));
        ParagraphParserImpl paragraphs = new ParagraphParserImpl();
        SentenceParserImpl sentences = new SentenceParserImpl();
        LexemeParserImpl lexemes = new LexemeParserImpl();
        WordParserImpl words = new WordParserImpl();
        paragraphs.setNext(sentences);
        sentences.setNext(lexemes);
        lexemes.setNext(words);
        first = paragraphs;
    }
    public TextComponent parse(String source) throws TextProcessingException {
        if (source == null) throw new TextProcessingException("No source text found");
        ParseContext context = new ParseContext(source);
        try {
            first.parse(source, context);
        }
        catch (IllegalStateException e)
        {
            throw new TextProcessingException("Text processing error", e);
        }
        return context.getResult();
    }
}
