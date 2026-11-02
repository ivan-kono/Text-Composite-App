package com.project.textapp.parser;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.model.AbstractTextComponent;
public class ParseContext {
    private final String source;
    private AbstractTextComponent result;
    public ParseContext(String source) {
        this.source = source;
    }
    public String getSource() { return source; }
    public AbstractTextComponent getResult() { return result; }
    public void setResult(AbstractTextComponent result) { this.result = result; }
}
