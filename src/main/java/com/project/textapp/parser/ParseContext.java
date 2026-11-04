package com.project.textapp.parser;
import com.project.textapp.model.TextComposite;

public class ParseContext {
    private final String source;
    private TextComposite result;
    public ParseContext(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    public TextComposite getResult() {
        return result;
    }
    public void setResult(TextComposite result) {
        this.result = result;
    }
}
