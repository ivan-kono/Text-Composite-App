package com.project.textapp.parser.impl;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextCharacter;
import com.project.textapp.model.TextComponent;
import com.project.textapp.parser.TextParser;

public class SymbolParserImpl implements TextParser{
    @Override public void parse(String source, TextComponent parent) {
        for(int i=0; i < source.length(); i++){
            TextComponent symbol = new TextCharacter(ElementType.SYMBOL, source.charAt(i));
            parent.add(symbol);
        }
    }
}
